/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.modelo;

/**
 *
 * @author jesus
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Stack;
import javax.imageio.ImageIO;

public class Inventario {
    private Stack<Pizza> inventarioPizzas = new Stack<>();
    private int tamañoInventario;
    private BufferedImage[] hojaDeAnimacion;
    private int[] mapa = {8, 7, 6, 5, 4, 3, 2, 1, 0};

    public Inventario(int tamañoInventario) {
        if (tamañoInventario <= 8) {
            this.tamañoInventario = tamañoInventario;
        } else {
            this.tamañoInventario = 8;
        }
    }

    public void renderizarInventario(Graphics g, float xEntidad) {
        if (inventarioPizzas.size() > 0) {
            g.drawImage(hojaDeAnimacion[mapa[inventarioPizzas.size()]], (int) xEntidad, 300, null);
        }
    }

    public void cargarImagenInventario() {
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/PizzaHealth.png"));
            hojaDeAnimacion = new BufferedImage[9];
            for (int x = 0; x < hojaDeAnimacion.length; x++) {
                hojaDeAnimacion[x] = img.getSubimage(x * 107, 0, 107, 121);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stack<Pizza> getInventarioPizzas() {
        return inventarioPizzas;
    }

    public void push(Pizza p) {
        inventarioPizzas.push(p);
    }

    public Pizza peek() {
        return inventarioPizzas.peek();
    }

    public boolean hayEspacioParaPizza() {
        return inventarioPizzas.size() < tamañoInventario;
    }

    public boolean isEmpty() {
        return inventarioPizzas.isEmpty();
    }
}
