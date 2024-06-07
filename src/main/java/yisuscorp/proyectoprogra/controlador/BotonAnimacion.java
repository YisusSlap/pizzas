/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import yisuscorp.proyectoprogra.vista.PantallaAnimacion;
import yisuscorp.proyectoprogra.vista.Ventana;
import yisuscorp.proyectoprogra.vista.MenuPrincipal;

/**
 *
 * @author jesus
 */
public class BotonAnimacion implements ActionListener {
    private MenuPrincipal menuPrincipal;

    public BotonAnimacion(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }

    public void actionPerformed(ActionEvent e) {
        PantallaAnimacion pantalla = new PantallaAnimacion();
        Ventana ventana = new Ventana(pantalla);
        Thread hiloPantalla = new Thread(pantalla);
        hiloPantalla.start();
        menuPrincipal.dispose(); // Oculta el MenuPrincipal al presionar el botón
    }
}
