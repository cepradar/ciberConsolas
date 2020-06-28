package com.company.utilidades;

import com.company.gui.VentanaTabla;

import static com.company.gui.VentanaTabla.*;
import static com.company.utilidades.Utilidades.EDAD;

public class Temporizador extends Thread { //una clase que hereda de la clase Thread
    public Temporizador() {// Contructor porque la clase es heredada
        super();
    }

    public void run() {
        int fila = VentanaTabla.getFila();
        int tiempoTotal = minutos;
        int nuMin = 0; //El Contador de minutos
        int nuSeg = 60; //El Contador de de segundos
        int nuHora =0; //El Contador de Horas
        while(tiempoTotal>60){
            nuHora++;
            tiempoTotal-=60;
        }
        nuMin = tiempoTotal-1;

        try {//si ocurre un error al dormir el proceso(sleep(999))
            for (; ; ) { //inicio del for infinito
                if(!listaEstaciones.get(fila).getEstado())  break; //stop a la ejecucion del cronometro en caso de que se cancele el servicio prematuramente
                if (nuSeg != 0) {//si no es el ultimo segundo
                    nuSeg--; //disminuyo el numero de segundos
                } else {
                    if (nuMin != 0) {//si no es el ultimo minuto
                        nuSeg = 60;//pongo en cincuenta y nueve los segundos
                        nuMin--;//disminuyo el numero de minutos
                    } else {//disminuyo el numero de horas
                        nuHora--;
                        nuMin = 59;//pongo en cincuenta y nueve los minutos
                        nuSeg = 60;//pongo en cincuenta y nueve los segundos
                    }
                }
                //System.out.println(fila+ PROFESION);
                tablaPersonas.setValueAt(nuHora + ":" + nuMin + ":" + nuSeg, fila,EDAD);
                System.out.println(nuHora + ":" + nuMin + ":" + nuSeg);//Muestro en pantalla el temporizador
                sleep(999);//Duermo el hilo durante 999 milisegundos(casi un segundo, quintandole el tiempo de proceso)
                if(nuHora==0 && nuMin==0 && nuSeg==0) break;
            }//Fin del for infinito
        } catch (Exception ex) {
            System.out.println(ex.getMessage());//Imprima el error
        }
    }
}