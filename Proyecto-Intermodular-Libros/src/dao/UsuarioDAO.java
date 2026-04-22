package dao;

import conexion.ConexionBD;
import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    //para registrar
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
            System.err.println("Error al registrar usuario en la BD: " + e.getMessage());
            return false;
        }
    }

    //validar el login
    public Usuario verificarLogin(String username, String password) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND password = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Si encontramos al usuario, devolvemos sus datos en un objeto
                    Usuario user = new Usuario();
                    user.setId(rs.getInt("id"));
                    user.setNombreUsuario(rs.getString("nombre_usuario"));
                    user.setCorreoElectronico(rs.getString("correo_electronico"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar login en la BD: " + e.getMessage());
        }
        return null; // Si no hay coincidencias o hay error, devolvemos null
    }
}