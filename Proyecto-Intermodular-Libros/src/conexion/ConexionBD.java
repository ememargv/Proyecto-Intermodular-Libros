package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca_dam";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    // Instancia única de la conexión
    private static Connection conexion = null;
    // Constructor privado
    private ConexionBD() {}
    public static Connection conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("LOG: Conexión establecida (Singleton)");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
        return conexion;
    }
}