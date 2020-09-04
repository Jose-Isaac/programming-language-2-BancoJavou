package com.javou;

public class Conta {
   private int numAgencia;
   private int numConta;
   private double saldo;

   // Constructor da class
   public Conta(){}

   // Segundo constructor da class
   public Conta(int numAgencia, int numConta, double saldo) {
       this.numAgencia = numAgencia;
       this.numConta = numConta;
       this.saldo = saldo;
   }

    public void setNumAgencia(int numAgencia) {
        this.numAgencia = numAgencia;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public void setSaldo(double saldo) {
       if (saldo >= 200.00) {
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
