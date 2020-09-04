package com.javou;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class GuiTabela extends JTable {

    private JTable tabela;

    public GuiTabela(String[] colunas, String[][] dados) {
        setLayout(new FlowLayout()); // tipo do layout
        setSize(new Dimension(600, 200)); // tamanho do formulário

        tabela = new JTable(dados, colunas); // instanciando a JTable
        tabela.setPreferredScrollableViewportSize(new Dimension(500, 100)); // barra de rolagem
        tabela.setFillsViewportHeight(true);

        // adicionando a tabela a barra de rolagem
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Adicionando ao JFrame "Formulário" a JTable "Tabela"
        add(scrollPane);
    }
}
