package com.javou;

public class Contato {
    private String email;
    private String fixo;
    private String celular;

    public Contato(){}

    public Contato(String email, String fixo, String celular) {
        this.email = email;
        this.fixo = fixo;
        this.celular = celular;
    }

    public Contato(String email, String celular) {
        this.email = email;
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFixo(String fixo) {
        this.fixo = fixo;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public String getFixo() {
        return fixo;
    }

    public String getCelular() {
        return celular;
    }
}
