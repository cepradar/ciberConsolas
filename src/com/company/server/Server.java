package com.company.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class Server implements Runnable {
    private Map<String, HiloCliente> conectados;
    private Thread hilo;

    /*
        private boolean bloquear(String[] arrOfStr) {

            return true;
        }

        private boolean tiempo(String[] arrOfStr) {
            return true;
        }

        private boolean orden(String[] arrOfStr) {
            switch (arrOfStr[0]) {
                case "bloquear":
                    return bloquear(arrOfStr);
                case "tiempo":
                    return tiempo(arrOfStr);
            }
            return true;
        }
    */
    protected Server() {
        conectados = new TreeMap<String, HiloCliente>();
        hilo = new Thread(this);
        hilo.start();
    }



    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            Socket cliente;

            while (true) {
                System.out.println("servidor escuchando");
                cliente = serverSocket.accept();
                DataInputStream input = new DataInputStream(cliente.getInputStream());
                DataOutputStream output = new DataOutputStream(cliente.getOutputStream());
                String mensaje = input.readUTF();
                System.out.println(mensaje);
                System.out.println(cliente.getInetAddress().toString());
                String[] arrOfStr = mensaje.split(":");

                switch (arrOfStr[0]) {

                    case "conectar":
                        conectados.put(arrOfStr[1], new HiloCliente(cliente));
                        System.out.println("cliente conetado"+arrOfStr[1]+arrOfStr[1]);
                        output.writeUTF("conectado al servidor");

                        break;

                }



                if (mensaje.equalsIgnoreCase("fin")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void detener() {
        System.out.println("detener servidor");
        hilo.stop();
    }

    protected boolean enviar(String mensaje, String nombre) {
        conectados.get(nombre).enviar(mensaje);
        return true;
    }
}
