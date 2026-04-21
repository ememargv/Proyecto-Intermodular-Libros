package dao;

import conexion.ConexionBD;
import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean registrarUsuario(Usuario usuario) {

        String sql = "INSERT INTO usuarios (nombre_usuario, password, correo_electronico) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getCorreoElectronico());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            // Esto imprimirá en la consola de IntelliJ el error real si algo falla
            System.err.println("Error al registrar usuario en la BD: " + e.getMessage());
            return false;
        }
    }
}