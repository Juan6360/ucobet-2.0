package com.juan.ucobet.dominio;

public class Boleta {

    // Atributos
    public int numero;
    public long puja;

    // Constructor
    public Boleta(int numero, long puja) {
        this.numero = numero;
        this.puja = puja;
    }

    // Metodos

    public String toString() {
        return "numero escogido= " + numero + ", puja apostada= " + puja;
    }

    // Getter
    public int getNumero() {
        return numero;
    }

    public long getPuja() {
        return puja;
    }
}
