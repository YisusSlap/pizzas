/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.vista;

/**
 *
 * @author jesus
 */
import yisuscorp.proyectoprogra.controlador.PizzaDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.util.Random;

public class Ventana extends JFrame {
    public Ventana(PantallaAnimacion pantalla) {
        setTitle("Proyecto de Programación");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(pantalla);
        setSize(1000, 532); // Establecer tamaño de la ventana
        setResizable(false); // Deshabilitar redimensionamiento
        setLocationRelativeTo(null);
        setVisible(true);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Guarda los datos antes de cerrar la ventana
                guardarDatos();
            }
        });
    }
    
    private void guardarDatos() {
        // Genera un número de sesión aleatorio entre 100 y 999
        Random random = new Random();
        int numeroSesion = random.nextInt(900) + 100;
        
        // Guarda la cantidad de pizzas producidas y consumidas, junto con el número de sesión
        PizzaDAO pizzaDAO = PizzaDAO.getInstance();
        pizzaDAO.crearRegistro(PantallaAnimacion.getCocinero().getPizzasElaboradas(), PantallaAnimacion.getComprador().getPizzasConsumidas(), numeroSesion);
        pizzaDAO.cerrarConexion();
    }
}