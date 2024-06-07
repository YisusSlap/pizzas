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
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;
import yisuscorp.proyectoprogra.controlador.TacosDAO;
import yisuscorp.proyectoprogra.vista.Ventana2;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BotnExportar implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream("registros.pdf"));
            document.open();
            
            // Agregar título
            document.add(new com.itextpdf.text.Paragraph("Registros de Tacos"));
            document.add(new com.itextpdf.text.Paragraph("\n"));
            
            // Crear la tabla
            PdfPTable pdfTable = new PdfPTable(Ventana2.modeloTabla.getColumnCount());
            // Agregar encabezados de columna
            for (int i = 0; i < Ventana2.modeloTabla.getColumnCount(); i++) {
                pdfTable.addCell(Ventana2.modeloTabla.getColumnName(i));
            }
            // Agregar contenido de la tabla
            for (int row = 0; row < Ventana2.modeloTabla.getRowCount(); row++) {
                for (int column = 0; column < Ventana2.modeloTabla.getColumnCount(); column++) {
                    pdfTable.addCell(Ventana2.modeloTabla.getValueAt(row, column).toString());
                }
            }
            document.add(pdfTable);
            document.close();
            
            JOptionPane.showMessageDialog(null, "El archivo PDF se ha creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al exportar a PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
