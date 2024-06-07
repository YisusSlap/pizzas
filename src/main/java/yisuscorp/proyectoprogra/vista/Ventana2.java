/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jesus
 */
public class Ventana2 extends JFrame {
    public Ventana2(){
        setTitle("Gestión de Registros");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Panel lateral con los botones
        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(new GridLayout(3, 1));
        JButton btnEditar = new JButton("Editar registro");
        JButton btnEliminar = new JButton("Eliminar registro");
        JButton btnExportar = new JButton("Exportar");
        panelLateral.add(btnEditar);
        panelLateral.add(btnEliminar);
        panelLateral.add(btnExportar);

        // Panel central con la tabla
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        JTable tabla = new JTable();
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"ID", "Producción", "Consumo"}, 0);
        tabla.setModel(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        panelCentral.add(scrollPane, BorderLayout.CENTER);

        // Agregar paneles a la ventana
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelLateral, BorderLayout.WEST);
        getContentPane().add(panelCentral, BorderLayout.CENTER);

        setVisible(true);
    }
}
