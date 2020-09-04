package com.javou;

public class Cliente {
    private String nome;
    private int idade;
    private char sexo;
    private String cpf;
    private Conta conta = new Conta();
    private Endereco endereco = new Endereco();
    private Contato contato = new Contato();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(int numConta, int numAgencia, double saldo) {
        this.conta.setNumConta(numConta);
        this.conta.setNumAgencia(numAgencia);
        this.conta.setSaldo(saldo);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(String rua, int numero, String bairro, String cidade) {
        this.endereco.setRua(rua);
        this.endereco.setNumero(numero);
        this.endereco.setBairro(bairro);
        this.endereco.setCidade(cidade);
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(String emial, String fixo, String celular) {
        this.contato.setEmail(emial);
        this.contato.setFixo(fixo);
        this.contato.setCelular(celular);
    }
}
