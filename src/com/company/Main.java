package com.company;

import com.company.gui.VentanaTabla;
import com.company.server.Admin;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //con este admin manipulas llamando a sus metodos y pasandoles los respectivos atributos
        //requeridos para cada orden
        Admin admin=new Admin();
        VentanaTabla miVentana=new VentanaTabla();
        miVentana.setVisible(true);


    }
}
