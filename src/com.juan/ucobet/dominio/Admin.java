package com.juan.ucobet.dominio;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Admin extends Usuario {

    // Atributos
    private String pass;

    // Constructor
    public Admin(String nombre, long identificacion, String correo, long celular, String pass) {

        super(nombre, identificacion, correo, celular);
        this.pass = pass;

    }

    // Metodos

    // escogerNumeroGanador
    // Función encargada de establecer los numero ganadores de los sorteos
    public void escogerNumerosGanadores(Juego juego, int numero){

        juego.setNumeroGanador(numero);

    }

    // escogerFecha
    // Función encargada de establecer la hora a la que se realiza el sorteo
    public void escogerFecha(Juego juego, String fecha){

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

        try {

            LocalDateTime dateTime = LocalDateTime.parse(fecha, formato);
            System.out.println(STR."Fecha y hora ingresadas: \{dateTime.format(formato)}");

            juego.setFecha(dateTime);

        } catch (DateTimeParseException e){

            System.out.println("Formato fecha y hora incorrecto");

        }

    }

    // escogerMultiplicador
    // Función encargada de establecer el porcentaje por el cual las recompensas seran multiplicadas
    public void escogerMultiplicador(Juego juego, double porcentaje){

        juego.setPorcentajeMultiplicador(porcentaje);

    }

    // escogerRecompensa
    public void escogerRecompensa(Juego juego, long recompensaUnaCifra, long recompensaDosCifras, long recompensaTresCifras, long recompensaCuatroCifras){

        juego.setRecompensaUnaCifra(recompensaUnaCifra);
        juego.setRecompensaDosCifras(recompensaDosCifras);
        juego.setRecompensaTresCifras(recompensaTresCifras);
        juego.setRecompensaCuatroCifras(recompensaCuatroCifras);

    }
}
