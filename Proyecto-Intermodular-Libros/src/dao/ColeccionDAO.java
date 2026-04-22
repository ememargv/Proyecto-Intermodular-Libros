package dao;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ColeccionDAO {

    public boolean agregarLibroAColeccion(int idUsuario, int idLibro, int idEstado) {
        String sql = "INSERT INTO colección (id_Usuario, id_Libro, id_Estado) VALUES (?, ?, ?)";

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
}