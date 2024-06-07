/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.controlador;

/**
 *
 * @author jesus
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import yisuscorp.proyectoprogra.vista.Ventana2;

public class BotnEliminar implements ActionListener {
    Ventana2 v;

    public BotnEliminar(Ventana2 v) {
        this.v = v;
    }

    public void actionPerformed(ActionEvent e) {
        JTextField idField = new JTextField(3);

        JPanel panel = new JPanel();
        panel.add(new JLabel("ID:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Eliminar Registro",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int idSesion = Integer.parseInt(idField.getText());
                TacosDAO.getInstance().eliminarRegistro(idSesion);
                v.cargarRegistros();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
