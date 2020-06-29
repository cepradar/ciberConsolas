package com.company.server;

import java.io.*;
import java.net.Socket;

public class HiloCliente implements Runnable {
    protected Socket socket;

    public DataOutputStream out;

    public HiloCliente(Socket clientSocket) {
        this.socket = clientSocket;
        Thread hilo = new Thread(this);
        hilo.start();
    }


    public void enviar(String mensaje) {
        try {
            System.out.println("enviando mensaje");
            System.out.println(mensaje);
            out.writeUTF(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        DataInputStream input;
        try {
            input = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        String mensaje;
        while (true) {
            try {
                mensaje = input.readUTF();
                System.out.println("cliente dice ok" + mensaje);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

    }
}
