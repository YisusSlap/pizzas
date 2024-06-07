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

public class TacosDAO {
    // Singleton
    private static TacosDAO instance;
    private Connection connection;

    private TacosDAO() {
        // Configura la conexión a la base de datos
        try {
            String url = "jdbc:mysql://localhost:3306/TacosDataBase";
            String user = "taquero";
            String password = "12345";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized TacosDAO getInstance() {
        if (instance == null) {
            instance = new TacosDAO();
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
    public void crearRegistro(int tacosProducidos, int tacosConsumidos) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Tacos (tacos_producidos, tacos_consumidos) VALUES (?, ?)");
            statement.setInt(1, tacosProducidos);
            statement.setInt(2, tacosConsumidos);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para leer un registro específico por su número de sesión
    public void leerRegistro(int idSesion) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tacos WHERE idSesion = ?");
            statement.setInt(1, idSesion);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int tacosProducidos = result.getInt("tacos_producidos");
                int tacosConsumidos = result.getInt("tacos_consumidos");
                System.out.println("ID: " + idSesion + ", Pizzas Producidas: " + tacosProducidos + ", Pizzas Consumidas: " + tacosConsumidos);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un registro específico por su número de sesión
    public void actualizarRegistro(int tacosProducidos, int tacosConsumidos, int idSesion) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Tacos SET tacos_producidos = ?, tacos_consumidos = ? WHERE idSesion = ?");
            statement.setInt(1, tacosProducidos);
            statement.setInt(2, tacosConsumidos);
            statement.setInt(3, idSesion);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un registro específico por su número de sesión
    public void eliminarRegistro(int idSesion) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Tacos WHERE idSesion = ?");
            statement.setInt(1, idSesion);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}