package com.javou;

import javax.swing.*;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

public class Ferramenta {
    public String getDadosUsuario(String pergunta) {

        // cria uma entrada de dados
        String resposta = JOptionPane.showInputDialog(null, pergunta,"CADASTRO DE CLIENTE", JOptionPane.INFORMATION_MESSAGE);

        // caso o retorno seja null isso que dizer que o usuário fechou a genela
        if (resposta == null) {
            menu();
        } else if (resposta.equals("")) { // caso o usuário não digite nada o método é invocado novamente
            resposta = getDadosUsuario(pergunta);
        }

        return resposta;
    }

    public double getDadosUsuarioDouble(String pergunta) {
        double resposta = 0.0;

        try {
            resposta = Double.parseDouble(getDadosUsuario(pergunta));
        } catch (NumberFormatException e) {
            exibirMensagemErro("Ops: " + e.getMessage() + "\nInforme apenas caracteres numéricos");
            getDadosUsuarioDouble(pergunta);
        }

        if (resposta < 0) {
            exibirMensagemErro("Ops valor inválido\n Não é permitido valores negativos");
            getDadosUsuarioDouble(pergunta);
        }

        return resposta;
    }

    public int getDadosUsuarioInteger(String pergunta) {
        int resposta = 0;

        try {
            resposta = Integer.parseInt(getDadosUsuario(pergunta));
        } catch (NumberFormatException e) {
            exibirMensagemErro("Ops: " + e.getMessage() + "\nInforme apenas caracteres numéricos");
            getDadosUsuarioInteger(pergunta);
        }

        if (resposta < 0) {
            exibirMensagemErro("Ops valor inválido\n Não é permitido valores negativos");
            getDadosUsuarioInteger(pergunta);
        }

        return resposta;
    }

    public char getSexo(String pergunta) {
        char resposta = getDadosUsuario(pergunta).toUpperCase().charAt(0);

        System.out.println(resposta);

        if (resposta != 'F' && resposta != 'M') {
            exibirMensagemErro("Opção inválida!\nValores permidos 'F' ou 'M'");
            getDadosUsuario(pergunta);
        }

        return resposta;
    }

    public int getIdade(String pergunta) {
        int idade = getDadosUsuarioInteger(pergunta);

        if (idade < 18) {
            exibirMensagemErro("Ops, infelismente no momento não podemos abrir contas para menores de 18 anos");
            getIdade(pergunta);
        }

        return idade;
    }

    public static void exibirMensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Cadastro concluido", JOptionPane.INFORMATION_MESSAGE);
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

        // cria um JFrame
        JFrame frame = new JFrame("Banco JAVOU");

        String[] opcaoMenu = {"Cadastrar Cliente", "Exibir Clientes"};

        String opcaoSelecionada = (String) JOptionPane.showInputDialog(
            frame,
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
        }
    }
}
