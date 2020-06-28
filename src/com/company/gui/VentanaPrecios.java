package com.company.gui;

import com.company.vo.EstacionVo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VentanaPrecios extends JFrame implements ActionListener {

    private JLabel pc = new JLabel("PC");
    private JLabel xbox = new JLabel("XBOX");
    private JLabel ps3 = new JLabel("PS3");


    public VentanaPrecios(String title) throws HeadlessException {
        super(title);
        setBounds(60, 60, 340, 290);
        setLayout(null);

        Double aux=null;
        pc.setBounds(30,30,100,20);
        while (aux==null){
            for(EstacionVo estacion: VentanaTabla.lista){
                if(estacion.getTipo().compareToIgnoreCase("PC")==0){
                    aux=estacion.getPrecio();
                    break;
                }
            }
        }
        JTextField precioPcActual = new JTextField(Double.toString(aux));
        precioPcActual.setBounds(140, 30, 100, 20);
        add(precioPcActual);
        add(pc);

        xbox.setBounds(30,60,100,20);
        aux=null;
        while (aux==null){
            for(EstacionVo estacion: VentanaTabla.lista){
                System.out.println(estacion.getTipo());
                if(estacion.getTipo().compareToIgnoreCase("XBOX")==0){
                    aux=estacion.getPrecio();

                    break;
                }
            }
        }
        JTextField precioXboxActual = new JTextField(Double.toString(aux));
        precioXboxActual.setBounds(140, 60, 100, 20);
        add(precioXboxActual);
        this.add(xbox);

        ps3.setBounds(30,90,100,20);
        aux=null;
        while (aux==null){
            for(EstacionVo estacion: VentanaTabla.lista){
                if(estacion.getTipo().compareToIgnoreCase("Ps3")==0){
                    aux=estacion.getPrecio();
                    break;
                }
            }
        }
        JTextField precioPs3Actual = new JTextField(Double.toString(aux));
        precioPs3Actual.setBounds(140, 90, 100, 20);
        add(precioPs3Actual);
        this.add(ps3);

        JButton aceptar = new JButton("Aceptar");
        aceptar.setBounds(195,200,80,30);
        aceptar.addActionListener((ActionListener) this);
        add(aceptar);



        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String seleccionClick = e.getActionCommand();
    }
}
