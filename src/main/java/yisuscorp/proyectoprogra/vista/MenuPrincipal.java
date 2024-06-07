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
import yisuscorp.proyectoprogra.controlador.BotonCRUD;

/**
 *
 * @author jesus
 */
public class MenuPrincipal extends JFrame {
    BotonAnimacion btn;
    BotonCRUD crud;

    public MenuPrincipal() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Crear botones
        JButton btnSimulador = new JButton("Simulador");
        JButton btnInformacion = new JButton("Informacion");

        // Configurar diseño del panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(btnSimulador);
        panel.add(btnInformacion);

        // Agregar panel al marco
        add(panel);

        // Agregar acción al botón "Abrir Ventana Principal"
        btnSimulador.addActionListener(btn = new BotonAnimacion(this));

        // Agregar acción al botón "Abrir Pantalla con 3 Botones"
        btnInformacion.addActionListener( crud = new BotonCRUD(this));

        setVisible(true);
    }
}
