package com.company.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente implements Runnable {
    public String nombre;
    private Thread hilo;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.hilo = new Thread(this);
        this.hilo.start();
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 9090);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

            output.writeUTF("conectar:" + nombre);
            String mensaje;
            while (true) {
                mensaje = input.readUTF();
                System.out.println(mensaje);
                output.writeUTF("ok");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
