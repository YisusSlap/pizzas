/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.controlador;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import yisuscorp.proyectoprogra.vista.Ventana2;

/**
 *
 * @author jesus
 */
public class BotnEdit implements ActionListener {
    Ventana2 v;
    public BotnEdit(Ventana2 v){
        this.v=v;
    }
    public void actionPerformed(ActionEvent e) {
        JTextField idField = new JTextField();
        JTextField produccionField = new JTextField();
        JTextField consumoField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Producción:"));
        panel.add(produccionField);
        panel.add(new JLabel("Consumo:"));
        panel.add(consumoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Editar Registro",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int idSesion = Integer.parseInt(idField.getText());
                int produccion = Integer.parseInt(produccionField.getText());
                int consumo = Integer.parseInt(consumoField.getText());

                TacosDAO.getInstance().actualizarRegistro(produccion, consumo, idSesion);
                v.cargarRegistros();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }          
}
