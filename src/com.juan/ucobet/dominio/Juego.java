package com.juan.ucobet.dominio;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Juego {

    // Atributos
    private List<Usuario> usuarios = new ArrayList<>();
    private long ingresos;
    private long deudas;
    private List<String> numerosVetados = new ArrayList<String>();
    private int numeroGanador;
    private LocalDateTime fecha;
    private LocalDateTime fechaCierreReal;
    private double porcentajeMultiplicador;
    private long recompensaUnaCifra;
    private long recompensaDosCifras;
    private long recompensaTresCifras;
    private long recompensaCuatroCifras;

    // Metodos

    // registrarUsuario
    public void registrarUsuario(Usuario usuario){
        this.usuarios.add(usuario);
    }

    // realizarSorteo
    // Función responsable de comparar las boletas existentes con los números ganadores y devolver una lista de usuarios
    public List<Usuario> realizarSorteo(){

        System.out.println("Sorteo realizado!");

        return this.usuarios.stream().filter(u -> u.getBoletas().stream().anyMatch(b -> {
                                                                                        if (String.valueOf(b.getNumero()).equals(String.valueOf(this.numeroGanador))){
                                                                                            return true;
                                                                                        } else if (String.valueOf(b.getNumero()).substring(1).equals(String.valueOf(this.numeroGanador).substring(1))) {
                                                                                            return true;
                                                                                        } else if (String.valueOf(b.getNumero()).substring(2).equals(String.valueOf(this.numeroGanador).substring(2))) {
                                                                                            return true;
                                                                                        } else if (String.valueOf(b.getNumero()).substring(3).equals(String.valueOf(this.numeroGanador).substring(3))) {
                                                                                            return true;
                                                                                        }
                                                                                        return false;
                                                                                        }))
                                                                                        .collect(Collectors.toList());
    }

    // pagarUsuarios
    public void pagarGanadoresUnaCifra(List<Usuario> ganadores){

        ganadores.forEach(u -> u.getBoletas().stream().anyMatch(b -> {
                                                                    if (String.valueOf(b.getNumero()).equals(String.valueOf(this.numeroGanador))){
                                                                        this.deudas += (long) (recompensaCuatroCifras * (b.getPuja() + (b.getPuja() * porcentajeMultiplicador)));
                                                                        return true;
                                                                    } else if (String.valueOf(b.getNumero()).substring(1).equals(String.valueOf(this.numeroGanador).substring(1))){
                                                                        this.deudas += (long) (recompensaTresCifras * (b.getPuja() + (b.getPuja() * porcentajeMultiplicador)));
                                                                    } else if (String.valueOf(b.getNumero()).substring(2).equals(String.valueOf(this.numeroGanador).substring(2))){
                                                                        this.deudas += (long) (recompensaDosCifras * (b.getPuja() + (b.getPuja() * porcentajeMultiplicador)));
                                                                    } else if (String.valueOf(b.getNumero()).substring(3).equals(String.valueOf(this.numeroGanador).substring(3))){
                                                                        this.deudas += (long) (recompensaUnaCifra * (b.getPuja() + (b.getPuja() * porcentajeMultiplicador)));
                                                                    }
                                                                    return false;
                                                                    }));

        System.out.println(this.deudas);

    }

    // mostrarBalance
    // Función encargada de mostrar las ganancias/perdidas generadas luego de realizar el sorteo
    public void mostrarBalance() {

        this.usuarios.forEach(u -> u.getBoletas().forEach(b -> this.ingresos += b.getPuja()));

        long balance = this.ingresos - this.deudas;

        System.out.println(balance);

    }

    // mostrarLista
    // Función encargada de mostrar una lista de elementos (Se puede reutilizar para imprimir la información de otros metodos)
    public void mostrarLista(){

        this.usuarios.forEach(u -> System.out.println(u.getNombre()));

    }

    public void mostrarBoletas(Usuario usuario){

        usuario.getBoletas().forEach(b -> System.out.println(b.toString()));

    }

    public void numerosAVetar(int numeroAVetar) {
        //agrega los numeros a la lista numerosVetados
        numerosVetados.add(String.valueOf(numeroAVetar));
    }
    public boolean comparadorNumerosVetados(int numero){
        List<Character> listaCaracteres = new ArrayList<>();

        for (char c : String.valueOf(numero).toCharArray()) {
            listaCaracteres.add(c);
        }
        for (String numStr : numerosVetados) {
            char numChar = numStr.charAt(0);
            if (listaCaracteres.contains(numChar)) {
                return true;
            }
        }

        return false;
    }



    // Getters

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public long getRecompensaUnaCifra() {
        return recompensaUnaCifra;
    }

    public long getRecompensaDosCifras() {
        return recompensaDosCifras;
    }

    public long getRecompensaTresCifras() {
        return recompensaTresCifras;
    }

    public long getRecompensaCuatroCifras() {
        return recompensaCuatroCifras;
    }

    //get para obtener la lista de numeros Vetados
    public String getNumerosVetados() {
        String datosArray = "";
        for (String elemento: numerosVetados) {
            datosArray += elemento + " ";
        }
        return datosArray;
    }

    // Setters

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        // Calcular la fecha de cierre real restando 5 minutos
        this.fechaCierreReal = fecha.minusMinutes(5);
    }


    public void setNumeroGanador(int numeroGanador) {
        this.numeroGanador = numeroGanador;
    }

    public void setPorcentajeMultiplicador(double porcentajeMultiplicador) {
        this.porcentajeMultiplicador = porcentajeMultiplicador;
    }

    public void setRecompensaUnaCifra(long recompensaUnaCifra) {
        this.recompensaUnaCifra = recompensaUnaCifra;
    }

    public void setRecompensaDosCifras(long recompensaDosCifras) {
        this.recompensaDosCifras = recompensaDosCifras;
    }

    public void setRecompensaTresCifras(long recompensaTresCifras) {
        this.recompensaTresCifras = recompensaTresCifras;
    }

    public void setRecompensaCuatroCifras(long recompensaCuatroCifras) {
        this.recompensaCuatroCifras = recompensaCuatroCifras;
    }

    // Metodo para iniciar el monitoreo del cierre del sorteo
    public void iniciarMonitoreoCierre() {
        new Thread(() -> {
            while (LocalDateTime.now().isBefore(fechaCierreReal)) {
                try {
                    Thread.sleep(1000); // 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cerrarSorteo();
        }).start();
    }

    public void iniciarMonitoreoDeSorteo() {
        new Thread(() -> {
            while (true) {
                LocalDateTime ahora = LocalDateTime.now();

                if (ahora.isAfter(fecha)) {

                    pagarGanadoresUnaCifra(realizarSorteo());
                    break; // Salir del bucle después de realizar el sorteo
                }

                // Calcular el tiempo restante hasta la fecha del sorteo
                Duration duration = Duration.between(ahora, fecha);
                long millisUntilSorteo = duration.toMillis();

                // Dormir el hilo por el tiempo restante o una cantidad fija
                try {
                    Thread.sleep(Math.min(millisUntilSorteo, 1000)); // 1 segundo o el tiempo restante
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // Metodo para cerrar el sorteo
    public void cerrarSorteo() {
        System.out.println("El sorteo ha sido cerrado.");
        // Aquí puedes agregar la lógica para cerrar el sorteo y notificar a los usuarios
    }


}
