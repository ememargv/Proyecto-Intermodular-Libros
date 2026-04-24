package dao;

import conexion.ConexionBD;
import java.sql.*;

public class ColeccionDAO {

    /**
     * Une al usuario con el libro en la tabla intermedia, incluyendo su puntuación.
     */
    public boolean agregarLibroAColeccion(int idUsuario, int idLibro, String nombreEstado, int puntuacion) {
        int idEstado = obtenerIdEstadoPorNombre(nombreEstado);

        // Añadida la columna puntuacion al INSERT
        String sql = "INSERT INTO coleccion (id_usuario, id_libro, id_estado, puntuacion) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, idLibro);
            ps.setInt(3, idEstado);
            ps.setInt(4, puntuacion);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al añadir a colección: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarLibroDeColeccion(int idUsuario, String tituloLibro) {
        String sql = "DELETE FROM coleccion WHERE id_usuario = ? AND id_libro = (SELECT id FROM libros WHERE titulo = ? LIMIT 1)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setString(2, tituloLibro);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar de la colección: " + e.getMessage());
            return false;
        }
    }

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
        return 1;
    }
}