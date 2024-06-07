/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.vista;

import yisuscorp.proyectoprogra.modelo.Comprador;
import yisuscorp.proyectoprogra.modelo.Cocinero;
import yisuscorp.proyectoprogra.modelo.Inventario;
import yisuscorp.proyectoprogra.controlador.EntradasDeTeclado;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JPanel;

public class PantallaAnimacion extends JPanel implements Runnable {
    private static Comprador comprador;
    private static Cocinero cocinero;
    private BufferedImage fondo;
    private static Inventario invTaco = new Inventario(8);

    public PantallaAnimacion() {
        comprador = new Comprador(50, 340, 32, 41, 3, 8, 4f);
        cocinero = new Cocinero(600, 340, 65, 50, 3, 11);
        try {
            fondo = ImageIO.read(getClass().getResourceAsStream("/fondocafe.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(comprador).start();
        new Thread(cocinero).start();
        setFocusable(true);
        addKeyListener(new EntradasDeTeclado(this));
    }

    public static Comprador getComprador() {
        return comprador;
    }

    public static Cocinero getCocinero() {
        return cocinero;
    }
    
    public static Inventario getInvTacos(){
        return invTaco;
    } 
    

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        comprador.renderizarComprador(g);
        cocinero.renderizarCocinero(g);
         invTaco.renderizarInventario(g, 600);
    }
}