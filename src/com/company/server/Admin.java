package com.company.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Admin {
    private Server server;

    /*
     * para realizar ordenes llamas al metodo de la orden y le pasas los argumentos pedidos
     * b
     * */


    public boolean bloquear_cliente(String nombre) {
        /*
         * String nombre -> nombre del cliente al que se desea bloquer
         * */

        return server.enviar("bloquear", nombre);

    }

    public boolean asignar_tiempo(String nombre, String tiempo) {
        /*
         * String nombre -> nombre del cliente al que se le desea asignar tiempo
         * String tiempo -> tiempo a asignar para el cliente con nombre(nombre)
         * */
        return server.enviar("asignar:" + tiempo, nombre);
    }

    public Admin() {
        server = new Server();
    }

    public void detener() {
        this.server.detener();
    }
}
