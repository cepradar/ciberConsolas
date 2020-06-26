package com.company.utilidades;

import com.company.gui.VentanaTabla;

import static com.company.gui.VentanaTabla.*;
import static com.company.utilidades.Utilidades.PROFESION;

public class Cronometro extends Thread { //una clase que hereda de la clase Thread
    public Cronometro() {// Contructor porque la clase es heredada
        super();
    }

    public void run() {
        int fila = VentanaTabla.getFila();
        int tiempoTotal = minutos;
        int nuMin = 0; //El Contador de minutos
        int nuSeg = 0; //El Contador de de segundos
        int nuHora = 0; //El Contador de Horas
        try {//si ocurre un error al dormir el proceso(sleep(999))
            for (; ; ) { //inicio del for infinito
                if(!listaEstaciones.get(fila).getEstado())  break; //stop a la ejecucion del cronometro en caso de que se cancele el servicio prematuramente
                if (nuSeg != 59) {//si no es el ultimo segundo
                    nuSeg++; //incremento el numero de segundos
                } else {
                    if (nuMin != 59) {//si no es el ultimo minuto
                        nuSeg = 0;//pongo en cero los segundos
                        nuMin++;//incremento el numero de minutos
                    } else {//incremento el numero de horas
                        nuHora++;
                        nuMin = 0;//pongo en cero los minutos
                        nuSeg = 0;//pongo en cero los segundos
                    }
                }
                //System.out.println(fila+ PROFESION);
                tablaPersonas.setValueAt(nuHora + ":" + nuMin + ":" + nuSeg, fila,PROFESION);
                System.out.println(nuHora + ":" + nuMin + ":" + nuSeg);//Muestro en pantalla el cronometro
                sleep(999);//Duermo el hilo durante 999 milisegundos(casi un segundo, quintandole el tiempo de proceso)
                if((60*nuHora)+nuMin >= tiempoTotal) break;
            }//Fin del for infinito
        } catch (Exception ex) {
            System.out.println(ex.getMessage());//Imprima el error
        }
    }
}