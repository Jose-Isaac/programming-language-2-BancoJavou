package com.javou;

import javax.swing.*;

/**
 * Possui alguns métodos que ajudam na exibição e selocitação de dados para o usuário
 */
public class FerramentaGrafica {

    /**
     * Cria um JOptionPane que um campo input para entrada de dados
     *
     * @param pergunta uma String que descreve o que o usuário deve forneçer
     * @param setor uma String que descreve que setor ( serviço ) está chamando esse método
     * @return um String contendo o que o usuário forneceu
     */
    public String getDadosUsuario(String pergunta, String setor) {

        // cria uma entrada de dados
        String resposta = JOptionPane.showInputDialog(null, pergunta,setor, JOptionPane.INFORMATION_MESSAGE);

        // caso o retorno seja null isso que dizer que o usuário fechou a genela
        if (resposta == null) {
            // exibe novamente o menu principal
            menu();
        } else if (resposta.equals("")) { // caso o usuário não digite nada o método é invocado novamente
            resposta = getDadosUsuario(pergunta, setor);
        }

        // os dados que o usuário digitou
        return resposta;
    }

    /**
     * Chama o método getDadosUsuario() e faz a validação e conversão do dado para um double
     *
     * @param pergunta uma String que descreve o que o usuário deve forneçer
     * @param setor uma String que descreve que setor ( serviço ) está chamando esse método
     * @return um valor double
     */
    public double getDadosUsuarioDouble(String pergunta, String setor) {

        // Armazena a resposta do usuário
        double resposta = 0.0;

        // Tratamento de exeção caso o usuário não fornaça caracteres numéricos
        try {

            // Cria uma entrada de dados
            // Converte o retorno do getDadosUsuario() para um double
            resposta = Double.parseDouble(getDadosUsuario(pergunta, setor));
        } catch (NumberFormatException e) {

            // Exibe uma mensagem de erro para o usuário
            exibirMensagemErro("Ops: " + e.getMessage() + "\nInforme apenas caracteres numéricos!");

            // Invoca novamente o método conservando os parâmetros
            this.getDadosUsuarioDouble(pergunta, setor);
        }

        // Valida se o valor que o usuário forneceu é positivo
        if (resposta < 0) {

            // Exibe uma mensagem de erro para o usuário
            exibirMensagemErro("Ops valor inválido\n Não é permitido valores negativos");

            // Invoca novamente o método conservando os parâmetros
            getDadosUsuarioDouble(pergunta, setor);
        }

        // os dados que o usuário digitou
        return resposta;
    }

    /**
     * Chama o método getDadosUsuario() e faz a validação e conversão do dado para um Integer
     *
     * @param pergunta uma String que descreve o que o usuário deve forneçer
     * @param setor uma String que descreve que setor ( serviço ) está chamando esse método
     * @return um valor Integer
     */
    public int getDadosUsuarioInteger(String pergunta, String setor) {

        // Armazena a resposta do usuário
        int resposta = 0;

        // Tratamento de exeção caso o usuário não fornaça caracteres numéricos
        try {

            // cria uma entrada de dados
            // Converte o retorno do getDadosUsuario() para um Integer
            resposta = Integer.parseInt(getDadosUsuario(pergunta, setor));
        } catch (NumberFormatException e) {

            // Exibe uma mensagem de erro para o usuário
            exibirMensagemErro("Ops: " + e.getMessage() + "\nInforme apenas caracteres numéricos");

            // Invoca novamente o método conservando os parâmetros
            getDadosUsuarioInteger(pergunta, setor);
        }

        // Valida se o valor que o usuário forneceu é positivo
        if (resposta < 0) {

            // Exibe uma mensagem de erro para o usuário
            exibirMensagemErro("Ops valor inválido\n Não é permitido valores negativos");

            // Invoca novamente o método conservando os parâmetros
            getDadosUsuarioInteger(pergunta, setor);
        }

        // os dados que o usuário digitou
        return resposta;
    }

    /**
     * Chama o método getDadosUsuario() e faz a validação e conversão do dado para um Char
     *
     * @param pergunta uma String que descreve o que o usuário deve forneçer
     * @param setor uma String que descreve que setor ( serviço ) está chamando esse método
     * @return um valor Char contendo 'F' ou 'M' para Feminino ou Masculino respectivamente
     */
    public char getSexo(String pergunta, String setor) {

        // cria uma entrada de dados
        // Converte o retorno de getDadosUsuario() para Uppercase
        // Obtém o primeiro elemento da String
        char resposta = getDadosUsuario(pergunta,setor).toUpperCase().charAt(0);

        // Valida se um usuário forneceu uma entrada válida
        if (resposta != 'F' && resposta != 'M') {

            // Exibe uma mensagem de erro para o usuário
            exibirMensagemErro("Opção inválida!\nValores permidos 'F' ou 'M'");

            // Invoca novamente o método conservando os parâmetros
            getDadosUsuario(pergunta, setor);
        }

        // Retorna um char com valor 'F' ou 'M'
        return resposta;
    }

    /**
     * Chama o método getDadosUsuarioInteger() e faz a validação dos dados fornecidos
     *
     * @param pergunta uma String que descreve o que o usuário deve forneçer
     * @param setor uma String que descreve que setor ( serviço ) está chamando esse método
     * @return um valor Integer >= 18
     */
    public int getIdade(String pergunta, String setor) {

        // Cria uma entrada de dados
        int idade = getDadosUsuarioInteger(pergunta, setor);

        // VAlida se o usuário já é maior de idade
        if (idade < 18) {

            // Exibe uma mensagem de erro para o usuário
            exibirMensagemErro("Ops, infelismente no momento não podemos abrir contas para menores de 18 anos");

            // Invoca novamente o método conservando os parâmetros
            getIdade(pergunta, setor);
        }

        // A idade do usuário valídada
        return idade;
    }

    /**
     * Cria um JOptionPane que exibe informações para o usuário
     *
     * @param mensagem uma String que informa uma terafa concluida com sucesso
     * @param setor uma String que descreve que setor ( serviço ) está chamando esse método
     */
    public static void exibirMensagemSucesso(String mensagem, String setor) {
        JOptionPane.showMessageDialog(null, mensagem, setor, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Cria um JOptionPane que exibe informações para o usuário
     *
     * @param mensagem uma String que descreve o erro que ocorreu
     */
    public static void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Ops, alog deu errado", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Cria um JOptionPane que exibe um String formata com os dos clientes
     *
     * @param dados uma String formatada contendo os dados dos clientes que serão exibidos em tela
     */
    public static void exibrLista(String dados) {

        JOptionPane.showMessageDialog(null, dados, "CLIENTES ATIVOS", JOptionPane.PLAIN_MESSAGE);

        // Após a exibição dos dados retorna para o menu
        menu();
    }

    /**
     *
     * @return um String contento n '-'
     */
    public static String divisoria() {

        return "- ".repeat(30);
    }

    /**
     *  Cria uma menu em tela no estilo drop down
     */
    public static void menu() {

        // opcões do menu
        String[] opcoesMenu = {
                "Cadastrar Cliente",
                "Exibir Clientes",
                "Realizar Saque",
                "Realizar Depósito",
                "Realizar Transferência"
        };

        // Cria o JOptionPane do tipo informativo
        String opcaoSelecionada = (String) JOptionPane.showInputDialog(
            null,
            "Escolha uma opção",
            "Menu",
            JOptionPane.INFORMATION_MESSAGE,
            null, opcoesMenu, opcoesMenu[0]
        );

        // Verifica se o usuário clicou em 'cancelar'
        // Caso ele tenha clicado o programa é encerrado
        if (opcaoSelecionada == null) {
            System.out.println("\nSaindo do sistema...\n");
            System.exit(1);
        }

        // Cria um novo objeto ( agência ) Banco
        Banco BancoJavou = new Banco();

        switch (opcaoSelecionada) {
            case "Cadastrar Cliente" -> {
                BancoJavou.cadastrarCliente();
                menu();
            }
            case "Exibir Clientes" -> {
                BancoJavou.exibirClientes();
                menu();
            }
            case "Realizar Saque" -> {
                BancoJavou.saque();
                menu();
            }
            case "Realizar Depósito" -> {
                BancoJavou.deposito();
                menu();
            }
            case "Realizar Transferência" -> {
                BancoJavou.transferencia();
                menu();
            }
        }
    }

    /**
     * Cria uma menu no estilo drop down em tela
     *
     * @param setor uma String que descreve que setor ( serviço ) está chamando esse método
     * @param opcoes uma Array de String contendo as opções que serão exibidas no menu
     * @return uma String que representa a opção selecrionada pelo usuário
     */
    public static String menuDropDown(String setor, String[] opcoes) {

        // Cria um JOptionPane do tipo informativo
        String opcaoSelecionada = (String) JOptionPane.showInputDialog(
                null,
                "Escolha uma opção",
                setor,
                JOptionPane.INFORMATION_MESSAGE,
                null, opcoes, opcoes[0]
        );

        // Verifica se o usuário clicou em cancelar
        if (opcaoSelecionada == null) {
            menu(); // exibe novamente o menu
        }

        // Obção selecionada pelo usuário
        return opcaoSelecionada;
    }
}
