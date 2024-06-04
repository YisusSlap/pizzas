/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.vista;

/**
 *
 * @author jesus
 */
import javax.swing.JFrame;

public class Ventana extends JFrame {
    public Ventana(PantallaAnimacion pantalla) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(pantalla);
        setSize(1000, 532); // Establecer tamaño de la ventana
        setResizable(false); // Deshabilitar redimensionamiento
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
