package com.javou;

import java.util.ArrayList;

import static com.javou.Ferramenta.*;

public class Banco {
    static  ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private static int proximaConta;

    Ferramenta f1 = new Ferramenta();


    public Banco() {
        if (this.proximaConta <= 0) {
            ++this.proximaConta;
            popularBanco();
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
                + novoCliente.toString(),
                setor
                );
    }

    public void exibirClientes() {

        String clientesString = "";



        for (Cliente cliente : clientes) {
            clientesString += cliente.toString();
            clientesString += "\n" + divisoria() + "\n";
        }

        // Chama o método responsavel por exibir em tela os clientes ativos
        Ferramenta.exibrLista(clientesString);
    }

    public void saque() {
        String setor = "SAQUE:";
        int conta = f1.getDadosUsuarioInteger("Informe o número da sua conta:", setor);

        Cliente cliente = getCliente(conta);

        if (cliente != null) {
            int valorSaque = f1.getDadosUsuarioInteger("Informe o valor do saque:", setor);

            if (cliente.getConta().getSaldo() >= valorSaque) {
                cliente.getConta().setSaldo(cliente.getConta().getSaldo() - valorSaque);
            } else {
                exibirMensagemErro("Saldo insuficiente!");
                saque();
            }
        } else {
            exibirMensagemErro("Conta não encontrado!");
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

    private void popularBanco() {
        String[] nomes = {"José", "Isaac", "Newton"};
        String cpf = "000-000-000-00";
        int[] agencias = {1, 2, 1};
        double[] saldos = {200, 300, 1000};

        System.out.println(nomes.length);
        for (int i = 0; i < nomes.length ; i++) {
            Cliente novoCliente = new Cliente();
            novoCliente.setNome(nomes[i]);
            novoCliente.setCPF(cpf);
            novoCliente.setConta(getProximaConta(), agencias[i], saldos[i]);

            clientes.add(novoCliente);
        }

    }
}
