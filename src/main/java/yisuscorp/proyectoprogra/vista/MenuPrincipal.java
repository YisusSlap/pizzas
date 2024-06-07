/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import yisuscorp.proyectoprogra.controlador.BotonAnimacion;

/**
 *
 * @author jesus
 */
public class MenuPrincipal extends JFrame {
    BotonAnimacion btn;

    public MenuPrincipal() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Crear botones
        JButton btnVentanaPrincipal = new JButton("Abrir Ventana Principal");
        JButton btnPantallaTresBotones = new JButton("Abrir Pantalla con 3 Botones");

        // Configurar diseño del panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(btnVentanaPrincipal);
        panel.add(btnPantallaTresBotones);

        // Agregar panel al marco
        add(panel);

        // Agregar acción al botón "Abrir Ventana Principal"
        btnVentanaPrincipal.addActionListener(btn = new BotonAnimacion(this));

        // Agregar acción al botón "Abrir Pantalla con 3 Botones"
        btnPantallaTresBotones.addActionListener( btn = new BotonAnimacion(this));

        setVisible(true);
    }
}
