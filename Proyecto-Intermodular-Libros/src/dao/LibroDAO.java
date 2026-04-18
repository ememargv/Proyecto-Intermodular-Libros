package dao;

import conexion.ConexionBD;
import modelo.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    // Método para insertar un libro nuevo
    public boolean insertarLibro(Libro libro) {
        String sql = "INSERT INTO libros (titulo, ruta_portada, id_autor, id_genero) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getRutaPortada());
            ps.setInt(3, libro.getIdAutor());
            ps.setInt(4, libro.getIdGenero());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al insertar libro: " + e.getMessage());
            return false;
        }
    }

    // Método para listar todos los libros (útil para la pantalla principal)
    public void listarLibrosConsola() {
        String sql = "SELECT * FROM libros";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " - Título: " + rs.getString("titulo"));
            }
        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }
}