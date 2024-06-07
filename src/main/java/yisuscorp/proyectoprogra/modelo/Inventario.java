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
    private Stack<Taco> inventarioTaco = new Stack<>();
    private int tamañoInventario;
    private BufferedImage[] hojaDeAnimacion;

    public Inventario(int tamañoInventario) {
        if (tamañoInventario <= 8) {
            this.tamañoInventario = tamañoInventario;
        } else {
            this.tamañoInventario = 8;
        }
        cargarImagenInventario();
    }

    public void renderizarInventario(Graphics g, float xEntidad) {
        //System.out.println("Renderizando inventario con tamaño: " + inventarioPizzas.size()); // Debug
        if (inventarioTaco.size() > 0) {
            int index = 8 - inventarioTaco.size();
            int nuevoAnchoPizza = 70; // Ancho deseado para la pizza
            int nuevoAltoPizza = 80;  // Alto deseado para la pizza
            g.drawImage(hojaDeAnimacion[index], (int) xEntidad, 320, nuevoAnchoPizza, nuevoAltoPizza, null);
        }
    }

    public void cargarImagenInventario() {
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/Tacos1.png"));
            hojaDeAnimacion = new BufferedImage[9];
            int nuevoAnchoTaco = 40; // Nuevo ancho deseado para la pizza
            int nuevoAltoTaco = 50; // Nuevo alto deseado para la pizza
            for (int x = 0; x < hojaDeAnimacion.length; x++) {
                hojaDeAnimacion[x] = img.getSubimage(x * 107, 0, 107, img.getHeight());
                // Imprime las dimensiones y el contenido de la imagen para depuración
                //System.out.println("Imagen " + x + ": Ancho=" + hojaDeAnimacion[x].getWidth() + ", Alto=" + hojaDeAnimacion[x].getHeight());
            }
            //System.out.println("Imagenes de inventario cargadas correctamente"); // Debug
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stack<Taco> getInventarioTacos() {
        return inventarioTaco;
    }

    public void push(Taco p) {
        inventarioTaco.push(p);
    }

    public Taco pop() {
        return inventarioTaco.pop();
    }
    public Taco peek (){
        return inventarioTaco.peek();
    }

    public boolean hayEspacioParaPizza() {
        return inventarioTaco.size() < tamañoInventario;
    }

    public boolean isEmpty() {
        return inventarioTaco.isEmpty();
    }
    
    
}

