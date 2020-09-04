package com.javou;

import java.util.ArrayList;

import static com.javou.Ferramenta.*;

public class Banco {
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private static int proximaConta;

    Ferramenta f1 = new Ferramenta();


    public Banco() {
        if (this.proximaConta >= 1) {
            this.proximaConta++;
        }
    }

    private int getProximaConta() {
        int numConta = this.proximaConta++;
        return numConta;
    }

    public void cadastrarCliente() {

        String setor = "CADASTRO DE CLIENTE:";

        // cria um novo cliente
        Cliente novoCliente = new Cliente();

        // Dados pessoais do cliente

        String nome = f1.getDadosUsuario("Digite o nome completo do(a) cliente:", setor);
        int idade = f1.getIdade("Digite a idade do(a) Sr(a) " + nome + ":", setor);
        char sexo = f1.getSexo("Digite o sexo do(a) Sr(a) :" + nome +" ( F ou M)", setor);
        String cpf = f1.getDadosUsuario("Digite o CPF do(a) Sr(a) " + nome + ":", setor);

        novoCliente.setNome(nome);
        novoCliente.setIdade(idade);
        novoCliente.setSexo(sexo);
        novoCliente.setCPF(cpf);

        // Dados da conta do cliente
        int agencia = f1.getDadosUsuarioInteger("Digite o número da agência  do(a) Sr(a) " + nome + ":", setor);
        double saldo = f1.getDadosUsuarioDouble("Digite o saldo inicial da conta  do(a) Sr(a) " + nome + ": (mínimo: R$: 200.00)", setor);

        // o número da conte é gerado pelo sistema para mante a consistência dos dados
        novoCliente.setConta(this.getProximaConta(), agencia, saldo);

        // Dados do endereço do cliente
        String rua = f1.getDadosUsuario("Digite rua da moradia do(a) Sr(a) " + nome + ":", setor);
        int numeroCasa = f1.getDadosUsuarioInteger("Digite o número da caso do(a) Sr(a) " + nome + ": (sn= 0", setor);
        String bairro = f1.getDadosUsuario("Digite o bairro da moradia do(a) Sr(a) " + nome + ":", setor);
        String cidade = f1.getDadosUsuario("Digite a cidade em que reside o(a) Sr(a) " + nome + ":", setor);

        novoCliente.setEndereco(rua, numeroCasa, bairro, cidade);

        // Dados de contato do cliente
        String email = f1.getDadosUsuario("Digite o email do(a) Sr(a) " + nome + ":", setor);
        String fixo = f1.getDadosUsuario("Digite o número fixo do(a) Sr(a) " + nome + ":", setor);
        String celular = f1.getDadosUsuario("Digte o número do celular do(a) Sr(a) " + nome + ":", setor);

        novoCliente.setContato(email, fixo, celular);

        // Adicionando o novo cliente a lista de clientes do banco
        clientes.add(novoCliente);

        // Informando ao usuário
        exibirMensagemSucesso(
                "Cliente cadastrado com sucesso\n\n "
                + "Dados de acesso do cliente\n"
                + divisoria()
                + "Nome: "
                + nome
                + "\nConta: "
                + novoCliente.getConta()
                + "Agência: "
                + agencia,
                setor
                );
    }

    public void exibirClientes() {

        // Campos que serão exibidos
        final String[] coluna = {"Nome: ", "CPF: ", "Conta: ", "Agência: "};

        // armazena os dados que serão exibidos
        String dados =
                coluna[0] // Nome
                +"Isaac\n"
                + coluna[1] // CPF
                + "000-000-000-00\n"
                + coluna[2] // Conta
                + "0001\n"
                + coluna[3] // Agência
                + "0203\n"
                + Ferramenta.divisoria() + "\n";

        // pepara apenas os dados necessários dos cliente para serem exibidos
        for (Cliente cliente : clientes) {
            dados +=
                coluna[0] // Nome
                + cliente.getNome() + "\n"
                + coluna[1] // CPF
                + cliente.getCPF() + "\n"
                + coluna[2] // Conta
                + cliente.getConta().getNumConta() + "\n"
                + coluna[3] // Agência
                + cliente.getConta().getNumAgencia() + "\n"
                + Ferramenta.divisoria()
                + "\n";
        }

        // Chama o método responsavel por exibir em tela os dados
        Ferramenta.exibrLista(dados);
    }

    public void saque() {
        String setor = "SAQUE:";
        int conta = f1.getDadosUsuarioInteger("Informe o número da sua conta:", setor);

        Cliente cliente = getCliente(conta);

        if (cliente != null) {
            
        } else {
            exibirMensagemErro("Cliente não encontrado!");
            this.saque();
        }

    }

    private Cliente getCliente(int conta) {

        Cliente clienteTemp = null;

        for (Cliente cliente : clientes) {
            if (cliente.getConta().getNumConta() == conta) {
                clienteTemp = cliente;
            }
        }

        return clienteTemp;
    }
}
