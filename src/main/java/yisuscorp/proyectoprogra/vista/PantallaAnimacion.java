/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.vista;

import yisuscorp.proyectoprogra.controlador.EntradasDeTeclado;
import yisuscorp.proyectoprogra.modelo.Cocinero;
import yisuscorp.proyectoprogra.modelo.Pizza;
import yisuscorp.proyectoprogra.modelo.Comprador;
import yisuscorp.proyectoprogra.modelo.Inventario;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class PantallaAnimacion extends JPanel implements Runnable {
    private BufferedImage fondo;
    private static final int ANCHURA_VENTANA = 1000;
    private static final int ALTURA_VENTANA = 532;
    private static Comprador comprador;
    private static Inventario invPizza = new Inventario(8);
    private static Cocinero cocinero;

    public PantallaAnimacion() {
        cargarFondo();
        definirTamañoPanel();
        comprador = new Comprador(50, 375, 32, 41, 3, 8, 1, 4f);
        cocinero = new Cocinero(600, 360, 65, 50, 3, 11);
        invPizza.cargarImagenInventario();
        
        setFocusable(true);
        addKeyListener(new EntradasDeTeclado(this));
    }

    private void cargarFondo() {
        try (InputStream inputStream = getClass().getResourceAsStream("/fondocafe.png")) {
            fondo = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Comprador getComprador() {
        return comprador;
    }
    
    public static Inventario getInvPizza(){
        return invPizza;
    }

    private void definirTamañoPanel() {
        Dimension size = new Dimension(ANCHURA_VENTANA, ALTURA_VENTANA);
        setPreferredSize(size);
    }

    public void logicaPantalla() {
        comprador.logicaComprador();
        cocinero.logicaCocinero();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja el fondo
        g.drawImage(fondo, 0, 0, ANCHURA_VENTANA, ALTURA_VENTANA, this);
        // Dibuja otros elementos, como el comprador
        comprador.renderizarComprador(g);
        cocinero.renderizarCocinero(g);
        // Dibuja el inventario
        invPizza.renderizarInventario(g, 600); // Usa comprador.getX() para obtener la coordenada x del Comprador
    }

    @Override
    public void run() {
        while (true) {
            logicaPantalla();
            repaint();
            try {
                Thread.sleep(1000 / 60); // Aproximadamente 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
