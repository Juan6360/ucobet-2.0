package com.juan.ucobet.app;


import com.juan.ucobet.dominio.Admin;
import com.juan.ucobet.dominio.Juego;
import com.juan.ucobet.dominio.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.StringTemplate.STR;

public class AppUcoBet {

    //The task which you want to execute
    private static class MyTimeTask extends TimerTask
    {

        public void run()
        {
            System.out.println("Ejecutado!");
        }
    }

    public static void main(String[] args) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

        Juego juego = new Juego();
        Scanner in = new Scanner(System.in);
        int opc = 0;


        // Inicio de la app

        while(opc != 3){

            // Menu principal

            System.out.println("Bienvenido a UCO BET, seleccione una opción:");
            System.out.println("1.- Usuario");
            System.out.println("2.- Administrador");
            System.out.println("3.- Salir de la app");
            opc = in.nextInt();

            // Switch del menu principal

            switch (opc) {

                // Aplicacion usuario
                case 1:

                    // Variable que almacena la decisión de usuario en el submenu de usuario
                    int opc_usuario = 0;

                    // Lógica para ingresar usuario

                    in.nextLine(); // Limpiar el buffer

                    System.out.println("Ingrese su nombre: ");
                    String nombre = in.nextLine();

                    System.out.println("Ingrese su Cedula: ");
                    int identificacion = Integer.parseInt(in.nextLine());

                    System.out.println("Ingrese su correo: ");
                    String correo = in.nextLine();

                    System.out.println("Ingrese su celular: ");
                    long celular = Long.parseLong(in.nextLine());

                    // Creacion del usuario
                    Usuario nuevoUsuario = new Usuario(nombre, identificacion, correo, celular);
                    juego.registrarUsuario(nuevoUsuario);

                    // Ciclo del submenu de usuario
                    while(opc_usuario != 3){

                        System.out.println("1.- Jugar sorteo.");
                        System.out.println("2.- Ver historial de juego.");
                        System.out.println("3.- Cerrar sesión");
                        opc_usuario = in.nextInt();

                        // Switch del submenu de usuario
                        switch (opc_usuario){

                            // Jugar sorteo
                            case 1:

                                // Variable que almacena la opcion del menu de juego
                                int opc_boleta;

                                // Menu del juego

                                System.out.println(STR."Recompensas: \nFecha: \{juego.getFecha().format(formato)}");
                                System.out.println(STR."Sorteo una cifra: \{juego.getRecompensaUnaCifra()}");
                                System.out.println(STR."Sorteo dos cifras: \{juego.getRecompensaDosCifras()}");
                                System.out.println(STR."Sorteo tres cifras: \{juego.getRecompensaTresCifras()}");
                                System.out.println(STR."Sorteo cuatro cifras: \{juego.getRecompensaCuatroCifras()}");
                                System.out.println("1. Comprar boleta.");
                                System.out.println("2. Volver.");
                                opc_boleta = in.nextInt();

                                // Switch para el juego
                                switch (opc_boleta){

                                    // Comprar boleta
                                    case 1:

                                        in.nextLine(); // Limpiar el buffer
                                        int numero = 0;
                                        boolean condicion ;
                                        do{
                                            System.out.println("Los numeros vetados son: "+ juego.getNumerosVetados());

                                            // Logica para la creacion de boletas
                                            System.out.println("Ingrese numero a jugar: ");
                                            numero = Integer.parseInt(in.nextLine());

                                            condicion = juego.comparadorNumerosVetados(numero);


                                        }while(condicion);

                                        System.out.println("Ingrese el valor de la puja: ");
                                        int puja = Integer.parseInt(in.nextLine());

                                        System.out.println("Su boleta a sido creada.");

                                        // Creacion de nueva boleta
                                        nuevoUsuario.crearBoleta(numero, puja);

                                        break;

                                    // Volver
                                    case 2:
                                        break;
                                }
                                break;

                            // Ver historial
                            case 2:

                                juego.mostrarBoletas(nuevoUsuario);

                                in.nextLine(); // Limpiar el buffer

                                System.out.println("\n1. Volver.");
                                int opc_historial = Integer.parseInt(in.nextLine());

                                if (opc_historial == 1){
                                    break;
                                }

                            case 3:

                                System.out.println("Saliendo...");

                                break;
                        }

                    }
                    break;

                // Aplicacion Admin
                case 2:

                    // Variable encargada de almacenar la eleccion del menu de admin
                    int opc_admin = 0;

                    // Lógica para ingresar admin

                    in.nextLine(); // Limpiar el buffer

                    System.out.println("Ingrese su nombre: ");
                    String nombre_admin = in.nextLine();

                    System.out.println("Ingrese su Cedula: ");
                    int identificacion_admin = Integer.parseInt(in.nextLine());

                    System.out.println("Ingrese su correo: ");
                    String correo_admin = in.nextLine();

                    System.out.println("Ingrese su celular: ");
                    long celular_admin = Long.parseLong(in.nextLine());

                    System.out.println("Ingrese su password: "); //Españolll!!!!! (((Clave)))
                    String pass = in.nextLine();

                    // Creacion del admin
                    Admin nuevoAdmin = new Admin(nombre_admin, identificacion_admin, correo_admin, celular_admin, pass);

                    // Ciclo submenu admin
                    while (opc_admin != 5){

                        // Menu sorteo

                        System.out.println("1. Crear un sorteo.");
                        System.out.println("2. Vetar un numero.");
                        System.out.println("3. Balance.");
                        System.out.println("4. usuarios registrados.");
                        System.out.println("5. cerrar sesion.");


                        opc_admin = in.nextInt();

                        // Switch submenu admin
                        switch (opc_admin){

                            // Crear un sorteo
                            case 1:

                                // Logica para la creacion de un sorteo

                                in.nextLine(); // Limpiar el buffer

                                System.out.println("Ingrese el dia en que juega (dd-MM-yy HH:mm): ");
                                String fecha = in.nextLine();

                                // Invocacion de la funcion escogerFecha en Admin
                                nuevoAdmin.escogerFecha(juego, fecha);

                                System.out.println("Ingrese el multiplicador (0.0): ");
                                double mult = Double.parseDouble(in.nextLine());

                                nuevoAdmin.escogerMultiplicador(juego, mult);

                                System.out.println("Ingrese el numero ganador: ");
                                int numeroGanador = Integer.parseInt(in.nextLine());

                                nuevoAdmin.escogerNumerosGanadores(juego, numeroGanador);

                                System.out.println("Recompensa por obtener una cifra correcta: ");
                                long recompensaUnaCifra = Long.parseLong(in.nextLine());

                                System.out.println("Recompensa por obtener dos cifras correctas: ");
                                long recompensaDosCifras = Long.parseLong(in.nextLine());

                                System.out.println("Recompensa por obtener tres cifras correctas: ");
                                long recompensaTresCifras = Long.parseLong(in.nextLine());

                                System.out.println("Recompensa por obtener cuatro cifras correctas: ");
                                long recompensaCuatroCifras = Long.parseLong(in.nextLine());

                                // Invocacion metodo para establecer recompensa

                                nuevoAdmin.escogerRecompensa(juego, recompensaUnaCifra, recompensaDosCifras, recompensaTresCifras, recompensaCuatroCifras);
                                juego.iniciarMonitoreoCierre();
                                juego.iniciarMonitoreoDeSorteo();

                                break;

                            // Vetar numeros
                            case 2:
                                // Pregunta cuantos son los numeros que se van a vetar en el sorteo actual
                                System.out.println("¿cuantos numeros va a vetar del sorteo?");
                                int cantidadNumVetar = in.nextInt();

                                //for que ingresa los numeros que se vetaran
                                for (int i = 0; i < cantidadNumVetar ; i++) {
                                    System.out.println("Ingresa el numero que vas a vetar");
                                    int varNumVetar = in.nextInt();

                                    juego.numerosAVetar(varNumVetar);
                                }

                                System.out.println(STR."Numeros vetados: \{juego.getNumerosVetados()}");

                                break;

                            // Mostrar balance
                            case 3:
                                System.out.println("Balance actual: ");
                                juego.mostrarBalance();
                                break;

                            // Mostrar usuarios registrados
                            case 4:
                                System.out.println("Usuarios registrados:");
                                for (Usuario usuario : juego.getUsuarios()) {
                                    System.out.println(STR."Nombre: \{usuario.getNombre()}");
                                    System.out.println(STR."Cédula: \{usuario.getIdentificacion()}");
                                    System.out.println(STR."Correo: \{usuario.getCorreo()}");
                                    System.out.println(STR."Celular: \{usuario.getCelular()}");

                                }
                                break;

                            // Cerrar sesión
                            case 5:
                                break;
                        }
                    }
            }
        }
    }
}