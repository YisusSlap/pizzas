/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import yisuscorp.proyectoprogra.vista.MenuPrincipal;
import yisuscorp.proyectoprogra.vista.Ventana2;

/**
 *
 * @author jesus
 */
public class BotonCRUD implements ActionListener{
    private MenuPrincipal menuPrincipal;

    public BotonCRUD(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }

    public void actionPerformed(ActionEvent e) {
        Ventana2 ventana2 = new Ventana2();
        menuPrincipal.dispose(); // Oculta el MenuPrincipal al presionar el botón
    }
}
