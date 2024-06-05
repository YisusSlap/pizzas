/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.controlador;

/**
 *
 * @author jesus
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PizzaDAO {
    // Singleton
    private static PizzaDAO instance;
    private Connection connection;

    private PizzaDAO() {
        // Configura la conexión a la base de datos
        try {
            String url = "jdbc:mysql://localhost:3306/PizzaDatabase";
            String user = "root";
            String password = "03Julio1979";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized PizzaDAO getInstance() {
        if (instance == null) {
            instance = new PizzaDAO();
        }
        return instance;
    }

    // Método para cerrar la conexión a la base de datos
    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Método para crear un nuevo registro
    public void crearRegistro(int pizzasProducidas, int pizzasConsumidas, int numeroSesion) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Pizzas (pizzas_producidas, pizzas_consumidas, numero_sesion) VALUES (?, ?, ?)");
            statement.setInt(1, pizzasProducidas);
            statement.setInt(2, pizzasConsumidas);
            statement.setInt(3, numeroSesion);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para leer un registro específico por su número de sesión
    public void leerRegistro(int numeroSesion) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Pizzas WHERE numero_sesion = ?");
            statement.setInt(1, numeroSesion);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int pizzasProducidas = result.getInt("pizzas_producidas");
                int pizzasConsumidas = result.getInt("pizzas_consumidas");
                System.out.println("ID: " + id + ", Pizzas Producidas: " + pizzasProducidas + ", Pizzas Consumidas: " + pizzasConsumidas);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un registro específico por su número de sesión
    public void actualizarRegistro(int pizzasProducidas, int pizzasConsumidas, int numeroSesion) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Pizzas SET pizzas_producidas = ?, pizzas_consumidas = ? WHERE numero_sesion = ?");
            statement.setInt(1, pizzasProducidas);
            statement.setInt(2, pizzasConsumidas);
            statement.setInt(3, numeroSesion);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un registro específico por su número de sesión
    public void eliminarRegistro(int numeroSesion) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Pizzas WHERE numero_sesion = ?");
            statement.setInt(1, numeroSesion);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}