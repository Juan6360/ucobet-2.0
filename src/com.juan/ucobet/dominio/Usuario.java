package com.juan.ucobet.dominio;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    // Atributos
    private String nombre;
    private long identificacion;
    private String correo;
    private long celular;
    private List<Boleta> boletas = new ArrayList<>();

    // Constructor
    public Usuario(String nombre, long identificacion, String correo, long celular) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;

    }

    public void crearBoleta(int numero, int puja){

        Boleta boleta = new Boleta(numero, puja);
        this.boletas.add(boleta);

    }

    // Getters

    public long getCelular() {
        return celular;
    }

    public String getCorreo() {
        return correo;
    }

    public long getIdentificacion() {
        return identificacion;
    }

    public List<Boleta> getBoletas() {
        return boletas;
    }

    public String getNombre() {
        return nombre;
    }

}
