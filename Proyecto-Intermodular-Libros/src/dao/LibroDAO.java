package dao;

import conexion.ConexionBD;
import modelo.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {


    public List<Libro> obtenerLibrosParaTabla() {
        List<Libro> lista = new ArrayList<>();
        // Consulta que une las tablas para obtener nombres en lugar de IDs
        String sql = "SELECT l.titulo, a.nombre AS autor, g.nombre AS genero " +
                "FROM libros l " +
                "JOIN autores a ON l.id_autor = a.id " +
                "JOIN generos g ON l.id_genero = g.id";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("genero")
                );
                lista.add(libro);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener libros para tabla: " + e.getMessage());
        }
        return lista;
    }

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
            System.err.println("Error al insertar libro: " + e.getMessage());
            return false;
        }
    }

    // Método de prueba
    public void listarLibrosConsola() {
        String sql = "SELECT * FROM libros";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " - Título: " + rs.getString("titulo"));
            }
        } catch (Exception e) {
            System.err.println("Error al listar en consola: " + e.getMessage());
        }
    }
}