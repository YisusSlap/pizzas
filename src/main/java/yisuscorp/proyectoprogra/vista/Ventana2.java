/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import yisuscorp.proyectoprogra.controlador.BotnEdit;
import yisuscorp.proyectoprogra.controlador.BotnEliminar;
import yisuscorp.proyectoprogra.controlador.BotnExportar;
import yisuscorp.proyectoprogra.controlador.TacosDAO;
import yisuscorp.proyectoprogra.modelo.Registro;

/**
 *
 * @author jesus
 */
public class Ventana2 extends JFrame {
    public static DefaultTableModel modeloTabla;
    public Ventana2(){
        setTitle("Gestión de Registros");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Panel lateral con los botones
        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(new GridLayout(3, 1));
        JButton btnEditar = new JButton("Editar registro");
        btnEditar.addActionListener(new BotnEdit(this));
        JButton btnEliminar = new JButton("Eliminar registro");
        btnEliminar.addActionListener(new BotnEliminar(this));
        JButton btnExportar = new JButton("Exportar");
        btnExportar.addActionListener(new BotnExportar());
        panelLateral.add(btnEditar);
        panelLateral.add(btnEliminar);
        panelLateral.add(btnExportar);

        // Panel central con la tabla
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        JTable tabla = new JTable();
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Producción", "Consumo"}, 0);
        tabla.setModel(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        
        cargarRegistros();

        // Agregar paneles a la ventana
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelLateral, BorderLayout.WEST);
        getContentPane().add(panelCentral, BorderLayout.CENTER);

        setVisible(true);
    }
    
    //Metodo para cargar registros
    public void cargarRegistros() {
        modeloTabla.setRowCount(0);
        List<Registro> registros = TacosDAO.getInstance().leerRegistros();
        for (Registro registro : registros) {
            modeloTabla.addRow(new Object[]{registro.getIdSesion(), registro.getTacosProducidos(), registro.getTacosConsumidos()});
        }
    }
}
