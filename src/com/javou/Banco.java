package com.javou;

import java.util.ArrayList;

import static com.javou.Ferramenta.*;

public class Banco {
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private static int proximaConta;

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

        // cria um novo cliente
        Cliente novoCliente = new Cliente();
        
        Ferramenta f1 = new Ferramenta();

        // Dados pessoais do cliente

        String nome = f1.getDadosUsuario("Digite o nome completo do(a) cliente:");
        int idade = f1.getIdade("Digite a idade do(a) Sr(a) " + nome + ":");
        char sexo = f1.getSexo("Digite o sexo do(a) Sr(a) :" + nome +" ( F ou M)");
        String cpf = f1.getDadosUsuario("Digite o CPF do(a) Sr(a) " + nome + ":");

        novoCliente.setNome(nome);
        novoCliente.setIdade(idade);
        novoCliente.setSexo(sexo);
        novoCliente.setCPF(cpf);

        // Dados da conta do cliente
        int agencia = f1.getDadosUsuarioInteger("Digite o número da agência  do(a) Sr(a) " + nome + ":");
        double saldo = f1.getDadosUsuarioDouble("Digite o saldo inicial da conta  do(a) Sr(a) " + nome + ": (mínimo: R$: 200.00)");

        // o número da conte é gerado pelo sistema para mante a consistência dos dados
        novoCliente.setConta(this.getProximaConta(), agencia, saldo);

        // Dados do endereço do cliente
        String rua = f1.getDadosUsuario("Digite rua da moradia do(a) Sr(a) " + nome + ":");
        int numeroCasa = f1.getDadosUsuarioInteger("Digite o número da caso do(a) Sr(a) " + nome + ": (sn= 0");
        String bairro = f1.getDadosUsuario("Digite o bairro da moradia do(a) Sr(a) " + nome + ":");
        String cidade = f1.getDadosUsuario("Digite a cidade em que reside o(a) Sr(a) " + nome + ":");

        novoCliente.setEndereco(rua, numeroCasa, bairro, cidade);

        // Dados de contato do cliente
        String email = f1.getDadosUsuario("Digite o email do(a) Sr(a) " + nome + ":");
        String fixo = f1.getDadosUsuario("Digite o número fixo do(a) Sr(a) " + nome + ":");
        String celular = f1.getDadosUsuario("Digte o número do celular do(a) Sr(a) " + nome + ":");

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
                + agencia
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
}
