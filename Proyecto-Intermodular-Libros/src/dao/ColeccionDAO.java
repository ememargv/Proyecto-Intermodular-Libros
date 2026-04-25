package dao;

import conexion.ConexionBD;
import java.sql.*;

public class ColeccionDAO {

    public boolean agregarLibroAColeccion(int idUsuario, int idLibro, String nombreEstado, int puntuacion) {
        int idEstado = obtenerIdEstadoPorNombre(nombreEstado);
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

    // Nuevo método para actualizar puntuación y estado de un libro existente
    public boolean actualizarPuntuacionYEstado(int idUsuario, String tituloLibro, int nuevaNota, String nuevoEstado) {
        int idEstado = obtenerIdEstadoPorNombre(nuevoEstado);
        String sql = "UPDATE coleccion SET puntuacion = ?, id_estado = ? " +
                "WHERE id_usuario = ? AND id_libro = (SELECT id FROM libros WHERE titulo = ? LIMIT 1)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nuevaNota);
            ps.setInt(2, idEstado);
            ps.setInt(3, idUsuario);
            ps.setString(4, tituloLibro);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
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
        } catch (SQLException e) { return false; }
    }

    public int obtenerIdEstadoPorNombre(String nombre) {
        String sql = "SELECT id FROM estados WHERE nombre_estado = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) { }
        return 1;
    }
}