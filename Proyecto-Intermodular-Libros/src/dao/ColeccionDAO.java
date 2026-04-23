package dao;

import conexion.ConexionBD;
import java.sql.*;

public class ColeccionDAO {

     //Une al usuario con el libro en la tabla intermedia.
    public boolean agregarLibroAColeccion(int idUsuario, int idLibro, String nombreEstado) {
        // Buscamos primero el ID del estado seleccionado en el ComboBox
        int idEstado = obtenerIdEstadoPorNombre(nombreEstado);

        String sql = "INSERT INTO coleccion (id_usuario, id_libro, id_estado) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, idLibro);
            ps.setInt(3, idEstado);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al añadir a colección: " + e.getMessage());
            return false;
        }
    }

     //Busca el ID de un estado en la BD.

    public int obtenerIdEstadoPorNombre(String nombre) {
        String sql = "SELECT id FROM estados WHERE nombre_estado = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) {
            System.err.println("Error al buscar ID estado: " + e.getMessage());
        }
        return 1; // Devuelve 1 (suponiendo que es "Pendiente") por defecto si falla
    }
}