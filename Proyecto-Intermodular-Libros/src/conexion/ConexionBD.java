package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca_dam";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexion establecida con exito.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return conexion;
    }
}