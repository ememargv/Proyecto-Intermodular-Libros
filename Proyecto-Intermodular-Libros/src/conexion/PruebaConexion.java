package conexion;

import java.sql.Connection;

public class PruebaConexion {
    public static void main(String[] args) {
        Connection c = ConexionBD.conectar();
        if (c != null) {
            System.out.println("Conexion establecida con exito.");
        } else {
            System.out.println("Error en la conexion.");
        }
    }
}