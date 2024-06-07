/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.controlador;

/**
 *
 * @author jesus
 */

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import yisuscorp.proyectoprogra.modelo.Comprador;
import yisuscorp.proyectoprogra.vista.PantallaAnimacion;

public class EntradasDeTeclado implements KeyListener {
    private PantallaAnimacion pantallaDeJuego;

    public EntradasDeTeclado(PantallaAnimacion pantallaDeJuego) {
        this.pantallaDeJuego = pantallaDeJuego;
        Comprador comprador = PantallaAnimacion.getComprador();
        comprador.estadoDeEntradas.put(KeyEvent.VK_A, false);
        comprador.estadoDeEntradas.put(KeyEvent.VK_D, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No se necesita implementar
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        Comprador comprador = PantallaAnimacion.getComprador();
        if (comprador.estadoDeEntradas.containsKey(keyCode)) {
            comprador.estadoDeEntradas.replace(keyCode, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        Comprador comprador = PantallaAnimacion.getComprador();
        if (comprador.estadoDeEntradas.containsKey(keyCode)) {
            comprador.estadoDeEntradas.replace(keyCode, false);
        }
    }
}