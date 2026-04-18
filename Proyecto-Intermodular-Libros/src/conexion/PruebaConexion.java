package conexion;

import java.sql.Connection;

public class PruebaConexion {
    public static void main(String[] args) {
        System.out.println("Comprobando conexion con MySQL...");

        Connection con = ConexionBD.conectar();

        if (con != null) {
            System.out.println("OK: Conexion establecida con exito.");
        } else {
            System.out.println("ERROR: No se pudo conectar a la base de datos.");
        }
    }
}