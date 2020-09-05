package com.javou;

import javax.swing.*;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

public class Ferramenta {
    public String getDadosUsuario(String pergunta, String title) {

        // cria uma entrada de dados
        String resposta = JOptionPane.showInputDialog(null, pergunta,title, JOptionPane.INFORMATION_MESSAGE);

        // caso o retorno seja null isso que dizer que o usuário fechou a genela
        if (resposta == null) {
            menu();
        } else if (resposta.equals("")) { // caso o usuário não digite nada o método é invocado novamente
            resposta = getDadosUsuario(pergunta, title);
        }

        return resposta;
    }

    public double getDadosUsuarioDouble(String pergunta, String title) {
        double resposta = 0.0;

        try {
            resposta = Double.parseDouble(getDadosUsuario(pergunta, title));
        } catch (NumberFormatException e) {
            exibirMensagemErro("Ops: " + e.getMessage() + "\nInforme apenas caracteres numéricos");
            getDadosUsuarioDouble(pergunta, title);
        }

        if (resposta < 0) {
            exibirMensagemErro("Ops valor inválido\n Não é permitido valores negativos");
            getDadosUsuarioDouble(pergunta, title);
        }

        return resposta;
    }

    public int getDadosUsuarioInteger(String pergunta, String title) {
        int resposta = 0;

        try {
            resposta = Integer.parseInt(getDadosUsuario(pergunta, title));
        } catch (NumberFormatException e) {
            exibirMensagemErro("Ops: " + e.getMessage() + "\nInforme apenas caracteres numéricos");
            getDadosUsuarioInteger(pergunta, title);
        }

        if (resposta < 0) {
            exibirMensagemErro("Ops valor inválido\n Não é permitido valores negativos");
            getDadosUsuarioInteger(pergunta, title);
        }

        return resposta;
    }

    public char getSexo(String pergunta, String title) {
        char resposta = getDadosUsuario(pergunta,title).toUpperCase().charAt(0);

        System.out.println(resposta);

        if (resposta != 'F' && resposta != 'M') {
            exibirMensagemErro("Opção inválida!\nValores permidos 'F' ou 'M'");
            getDadosUsuario(pergunta, title);
        }

        return resposta;
    }

    public int getIdade(String pergunta, String title) {
        int idade = getDadosUsuarioInteger(pergunta, title);

        if (idade < 18) {
            exibirMensagemErro("Ops, infelismente no momento não podemos abrir contas para menores de 18 anos");
            getIdade(pergunta, title);
        }

        return idade;
    }

    public static void exibirMensagemSucesso(String mensagem, String title) {
        JOptionPane.showMessageDialog(null, mensagem, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Ops, alog deu errado", JOptionPane.ERROR_MESSAGE);
    }

    public static void exibrLista(String dados) {

        JOptionPane.showMessageDialog(null, dados, "CLIENTES ATIVOS", JOptionPane.PLAIN_MESSAGE);

        menu();
    }

    public static String divisoria() {
        String div = "";

        for (int i = 0; i < 20; i++) {
            div += "- ";
        }

        return div;
    }

    public static void menu() {

        String[] opcaoMenu = {
                "Cadastrar Cliente",
                "Exibir Clientes",
                "Realizar Saque",
                "Realizar Depósito",
                "Realizar Transferência"
        };

        String opcaoSelecionada = (String) JOptionPane.showInputDialog(
            null,
            "Escolha uma opção",
            "Menu",
            JOptionPane.INFORMATION_MESSAGE,
            null, opcaoMenu, opcaoMenu[0]
        );

        if (opcaoSelecionada == null) {
            System.out.println("\nSaindo do sistema...\n");
            System.exit(1);
        }

        Banco javou01 = new Banco();

        switch (opcaoSelecionada) {
            case "Cadastrar Cliente":
                javou01.cadastrarCliente();
                menu();
                break;

            case "Exibir Clientes":
                javou01.exibirClientes();
                menu();
                break;

            case "Realizar Saque":
                javou01.saque();
                menu();
                break;

            case "Realizar Depósito":
                javou01.deposito();
                menu();
                break;

            case "Realizar Transferência":
                javou01.transferencia();
                menu();
                break;
        }
    }

    public static String menuDropDown(String title, String[] opcoes) {

        String opcaoSelecionada = (String) JOptionPane.showInputDialog(
                null,
                "Escolha uma opção",
                title,
                JOptionPane.INFORMATION_MESSAGE,
                null, opcoes, opcoes[0]
        );

        if (opcaoSelecionada == null) {
            menu();
        }

        return opcaoSelecionada;
    }
}
