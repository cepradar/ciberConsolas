package com.company.vo;

import com.company.utilidades.Cronometro;
import com.company.utilidades.Temporizador;

import java.util.Calendar;

import static com.company.gui.VentanaTabla.fila;
import static com.company.gui.VentanaTabla.tablaPersonas;
import static com.company.utilidades.Utilidades.ESTADO;

public class EstacionVo {


    private String ord;
    private boolean estado;
    private String inicio;
    public Cronometro cronometro;
    public Temporizador temporizador;
    private int edad;
    private String tipo;
    private double precio;
    private double nota3;
    private double promedio;

    public EstacionVo() {

    }

    public EstacionVo(String ord, boolean estado, int edad,
                      double precio, String tipo, double nota3, double promedio) {
        super();
        this.ord = ord;
        this.estado = estado;
        this.inicio = "EMPTY";
        this.edad = edad;
        this.precio = precio;
        this.tipo = tipo;
        this.nota3 = nota3;
        this.promedio = promedio;
    }


    public String getOrd() {
        return ord;
    }

    public void setOrd(String ord) {
        this.ord = ord;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        int aux = fila;
        this.estado = estado;
        if(this.estado) {
            tablaPersonas.setValueAt("true",aux,ESTADO);
        }else tablaPersonas.setValueAt("false",aux,ESTADO);
    }

    public String getInicio() {
        Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos;
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);

        this.inicio = hora + " : " + minutos + " : " + segundos;
        return this.inicio;
    }//regresa la hora en la que se inicio la estacion

    public void start() {
        cronometro = new Cronometro();
        temporizador = new Temporizador();
        cronometro.start();
        temporizador.start();
    }//ejecuta el hilo con el cronometro

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }


}