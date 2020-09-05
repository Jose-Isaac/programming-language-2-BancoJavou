package com.javou;

import java.util.ArrayList;

import static com.javou.FerramentaGrafica.*;

public class Banco {
    static  ArrayList<Cliente> clientes = new ArrayList<>();
    private static int proximaConta;

    // Auxilia na exibição e obtenção de dados com o usuário
    FerramentaGrafica f1 = new FerramentaGrafica();


    /**
     * Quando criado pela primeira vez
     * 1) inicializa o proximo número da conta
     * 2) Insere alguns dados no Array de clientes ( -D )
     */
    public Banco() {
        if (proximaConta <= 0) {
            ++proximaConta;
            popularBanco();
        }
    }

    // Método intenno da classe que retorna o próximo número disponel para cadastro
    private int getProximaConta() {
        return proximaConta++;
    }

    // Insere um novo cliente no Array de clientes do Banco
    public void cadastrarCliente() {

        String setor = "CADASTRO DE CLIENTE:";

        // cria um novo cliente
        Cliente novoCliente = new Cliente();

        // Dados pessoais do cliente

        String nome = f1.getDadosUsuario("Nome completo do(a) cliente:", setor);
        int idade = f1.getIdade("Idade do(a) Sr(a) " + nome + ":", setor);
        char sexo = f1.getSexo("Sexo do(a) Sr(a) :" + nome +" ( F ou M)", setor);
        String cpf = f1.getDadosUsuario("CPF do(a) Sr(a) " + nome + ":", setor);

        novoCliente.setNome(nome);
        novoCliente.setIdade(idade);
        novoCliente.setSexo(sexo);
        novoCliente.setCPF(cpf);

        // Dados da conta do cliente
        int agencia = f1.getDadosUsuarioInteger("Digite o número da agência  do(a) Sr(a) " + nome + ":", setor);
        double saldo = f1.getDadosUsuarioDouble("Digite o saldo inicial da conta  do(a) Sr(a) " + nome, setor);

        // o número da conte é gerado pelo sistema para mante a consistência dos dados
        novoCliente.setConta(this.getProximaConta(), agencia, saldo);

        // Dados de endereço do cliente
        String rua = f1.getDadosUsuario("Rua:", setor);
        int numeroCasa = f1.getDadosUsuarioInteger("Número da casa:", setor);
        String bairro = f1.getDadosUsuario("Bairro:", setor);
        String cidade = f1.getDadosUsuario("Cidade:", setor);

        novoCliente.setEndereco(rua, numeroCasa, bairro, cidade);

        // Dados de contato do cliente
        String email = f1.getDadosUsuario("Email:", setor);
        String fixo = f1.getDadosUsuario("Número fixo, mais DDD:", setor);
        String celular = f1.getDadosUsuario("Número do celular, mais DDD:", setor);

        novoCliente.setContato(email, fixo, celular);

        // Adicionando o novo cliente a lista de clientes do banco
        clientes.add(novoCliente);

        // Informando ao usuário
        exibirMensagemSucesso(novoCliente.toString(), setor);
    }

    // Exibe os clientes cadastrados em tela
    public void exibirClientes() {

        StringBuilder clientesString = new StringBuilder();

        String[] opcoes = {"Exibir Todos", "Buscar Cliente"}; // Opções do menu

        // Cria um menu drop down em tela
        String opcaoSelecionada = menuDropDown("EXIBIR CLIENTES:",opcoes);

        switch (opcaoSelecionada) {

            // Exibe todos os clientes
            case "Exibir Todos" -> {

                // Percorrendo a lista de cliente
                for (Cliente cliente : clientes) {

                    // Obtém uma string formatada com as informações do cliente
                    clientesString.append(cliente.toString());

                    // Adiviona uma divisoria ( um sequência de n '-')
                    clientesString.append("\n").append(divisoria()).append("\n");
                }

                // Exibe em tela a string formata com as informações dos clientes
                FerramentaGrafica.exibrLista(clientesString.toString());
            }

            // Busca um cliente em especifico
            case "Buscar Cliente" -> {

                // Obtém o nome do cliente
                String nome = f1.getDadosUsuario("Informe o nome do cliente:", "BUSCAR DE CLIENTE:");

                // Percorrendo a lista de cliente
                for (Cliente cliente : clientes) {

                    // Verifica se existe o cliente forncecido
                    if (cliente.getNome().intern().equals(nome.intern())) {

                        // Exibe em tela as informações do cliente
                        exibirMensagemSucesso(cliente.toString(), cliente.getNome());
                    } else {

                        // Exibe uma mensagem de erro para o usuário
                        exibirMensagemErro("Cliente não encontrado!");

                        // Chama novamente o método
                        exibirClientes();
                    }
                }
            }
        }
    }

    // Realiza a operação de saque
    public void saque() {
        String setor = "SAQUE:";

        // Buscando os dados do cliente
        Cliente cliente = getCliente(setor);

        // VErificando se existe o cliente
        if (cliente != null) {

            // Exibe em tela um compo de entrada de dados, solicitando o valor de saque
            double valorSaque = f1.getDadosUsuarioDouble("Informe o valor do saque:", setor);

            // Verificando se o saque foi realizado com sucesso
            if (saque(cliente, valorSaque)) {

                // Informa ao usuário que a operação foi realizada com sucesso
                exibirMensagemSucesso("Saque realizado com sucesso!\n\n Saldo atual de RS " + cliente.getConta().getSaldo(), setor);
            }
            else {

                // Caso o saque não seja realizado com sucesso
                // Exibe novamente a tela de saque
                saque();
            }
        } else {

            // Informa ao usuário que ocorreu um erro
            exibirMensagemErro("Conta não encontrada!");

            // Caso não seja possivel encontra a conta
            // Exibe novamente a tela de saque()
            saque();
        }

    }

    /**
     *
     * Método interno da classe responsavel por fazer o processo interno de saque
     *
     * @param cliente a referêcia de um objeto Cliente
     * @param valorSaque o valor a se decrementado em conta
     * @return um valor boolean
     */
    private boolean saque(Cliente cliente, double valorSaque) {

        // Verificando de existe saldo disponível
        if (cliente.getConta().getSaldo() >= valorSaque) {

            // Calculando saldo atual
            double saldoAtual = cliente.getConta().getSaldo() - valorSaque;

            // Atualizando o saldo do cliente
            cliente.getConta().setSaldo(saldoAtual);

            return true;
        } else {

            // Informa ao usuário que ocorreu um erro
            exibirMensagemErro("Saldo insuficiente!");
            return false;
        }
    }

    /**
     * Método interno da classe responsavel por fazer o processo interno de depósito
     *
     * @param cliente a referêcia de um objeto Cliente
     * @param valorDeposito o valor a se incrementado em conta
     */
    private void deposito(Cliente cliente, double valorDeposito) {

        // Atualizando o saldo do cliente
        cliente.getConta().setSaldo(valorDeposito);
    }

    // Realiza a operação de depósito
    public void deposito() {
        String setor = "DEPOSITO:";

        // Buscando os dados do cliente
        Cliente cliente = getCliente(setor);

        // Verificando se o conta do cliente foi encontrada
        if (cliente != null) {

            // Exibe em tela um compo de entrada de dados, solicitando o valor de depósito
            double valorDeposito = f1.getDadosUsuarioDouble("Informe o valor a ser depositado em conta:", setor);

            // Calculando o saldo atual
            double saldoAtual = cliente.getConta().getSaldo() + valorDeposito;

            // Atualizado o saldo do cliente
            deposito(cliente, saldoAtual);

            // Informa ao usuário que a operação foi realizada com sucesso
            exibirMensagemSucesso("Depósito realizado com sucesso!\n\n Saldo atual de RS " + cliente.getConta().getSaldo(), setor);
        } else {

            // Informa ao usuário que ocorreu um erro
            exibirMensagemErro("Conta não encontrada!");

            // Caso não seja possivel encontra a conta
            // Exibe novamente a tela de deposito()
            deposito();
        }
    }

    /**
     *  Realiza o processo de transferência de saldo entre contas do banco
     */
    public void transferencia() {
        String setor = "TRANSFERÊNCIA";

        // Obtem os dados do remetente
        Cliente remetente = getCliente(setor +" - REMETENTE:");

        // Verificando se foi encontrada a conta do remetente
        if (remetente != null) {

            // Obtém os dados do destinatário
            Cliente destinatario = getCliente(setor + " - DESTINATÁRIO");

            // Verificando se foi encontrada a conta do destinatário
            if (destinatario != null) {

                // Obtendo o valor de ser transferido
                double valorTransferencia = f1.getDadosUsuarioDouble("Informe o valor a ser transferido:", setor);

                // Validando se o remetente tem saldo disponível
                // Descrementando saldo do remetente
                if(saque(remetente, valorTransferencia)) {

                    // Incrementando saldo do destinatátio
                    deposito(destinatario, valorTransferencia);

                    // Informa ao usuário que a operação foi realizada com sucesso
                    exibirMensagemSucesso(
                            "Transferência realizada com sucesso!\n\n "
                            + "Valor transferido: RS " + valorTransferencia
                            + "\n" + divisoria()
                            + "\n\nSaldo atual em conta: R$ " + remetente.getConta().getSaldo(),
                            setor + "COM SUCESSO!");
                } else {

                    // Informa ao usuário que ocorreu um erro
                    exibirMensagemErro("Saldo em conta insuficiente");

                    // Caso não seja possivel realizar a transferência
                    // Exibe novamente e tela de transferencia()
                    transferencia();
                }
            }
        } else {

            // Informa ao usuário que ocorreu um erro
            exibirMensagemErro("Conta não encontrada!");

            // Caso não seja possivel encontra a conta
            // Exibe novamente a tela de transferencia()
            transferencia();
        }
    }

    /**
     *
     * @param setor ( servições ) que solicitou esse método
     * @return a referência de uma objeto cliente
     */
    private Cliente getCliente(String setor) {

        Cliente clienteTemp = null;

        // Obtendo os dados da conta
        int agencia = f1.getDadosUsuarioInteger("Informe o número da sua agência:", setor);
        int conta = f1.getDadosUsuarioInteger("Informe o número da sua conta:", setor);

        // Percorrendo a lista de clientes
        for (Cliente cliente : clientes) {

            // Verficando se existe a agência fornecida e a conta para a agencia fornecida
            if (cliente.getConta().getNumAgencia() == agencia && cliente.getConta().getNumConta() == conta) {
                clienteTemp = cliente;
            }
        }

        return clienteTemp;
    }

    /**
     * Insere alguns dados ao iniciar a aplicalção
     */
    private void popularBanco() {
        // Array com alguns dados
        String[] nomes = {"José", "Isaac", "Newton"};
        String cpf = "000-000-000-00";
        int[] agencias = {1, 2, 1};
        double[] saldos = {200, 300, 1000};

        // Criando um novo cliente e adicionando a lista de clientes
        for (int i = 0; i < nomes.length ; i++) {
            Cliente novoCliente = new Cliente();
            novoCliente.setNome(nomes[i]);
            novoCliente.setCPF(cpf);
            novoCliente.setConta(getProximaConta(), agencias[i], saldos[i]);

            // Adicionada um novo cliente a lista
            clientes.add(novoCliente);
        }
    }
}
