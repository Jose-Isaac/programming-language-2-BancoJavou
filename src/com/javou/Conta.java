package com.javou;

public class Conta {
   private int numAgencia;
   private int numConta;
   private double saldo;

    public void setNumAgencia(int numAgencia) {
        this.numAgencia = numAgencia;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public void setSaldo(double saldo) {
       // Garnate que o saldo nunca fique negativo
       if (saldo >= 0) {
           this.saldo = saldo;
       } else {
           System.out.println("Saldo mínimo insuficiente");
        }
    }

    public int getNumAgencia() {
        return numAgencia;
    }

    public int getNumConta() {
        return numConta;
    }

    public double getSaldo() {
        return saldo;
    }


}
