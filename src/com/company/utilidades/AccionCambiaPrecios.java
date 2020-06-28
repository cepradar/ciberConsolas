package com.company.utilidades;

import com.company.gui.VentanaPrecios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class AccionCambiaPrecios extends AbstractAction {
    VentanaPrecios ventana;

    @Override
    public void actionPerformed(ActionEvent e) {
        ventana = new VentanaPrecios("Lista de precios");
        System.out.println("cambia precio");
    }

    @Override
    public void putValue(String key, Object newValue) {
        super.putValue(key, newValue);
    }
}
