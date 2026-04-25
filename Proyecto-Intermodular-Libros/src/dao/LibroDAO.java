package dao;

import conexion.ConexionBD;
import modelo.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class LibroDAO {

    public ObservableList<Libro> obtenerLibrosParaTabla(int idUsuario) {
        ObservableList<Libro> lista = FXCollections.observableArrayList();

        //actualizado con join a estado
        String sql = "SELECT l.titulo, a.nombre AS nombreAutor, g.nombre_genero AS nombreGenero, " +
                "c.puntuacion, e.nombre_estado " +
                "FROM coleccion c " +
                "JOIN libros l ON c.id_libro = l.id " +
                "JOIN autores a ON l.id_autor = a.id " +
                "JOIN generos g ON l.id_genero = g.id " +
                "JOIN estados e ON c.id_estado = e.id " +
                "WHERE c.id_usuario = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Libro(
                        rs.getString("titulo"),
                        rs.getString("nombreAutor"),
                        rs.getString("nombreGenero"),
                        rs.getInt("puntuacion"),
                        rs.getString("nombre_estado") // Nuevo
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener libros del usuario: " + e.getMessage());
        }
        return lista;
    }

    public int insertarLibroYObtenerId(Libro libro) {
        String sql = "INSERT INTO libros (titulo, ruta_portada, id_autor, id_genero) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getRutaPortada());
            ps.setInt(3, libro.getIdAutor());
            ps.setInt(4, libro.getIdGenero());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { System.err.println("Error al insertar libro: " + e.getMessage()); }
        return -1;
    }

    public ObservableList<String> obtenerNombresAutores() {
        ObservableList<String> autores = FXCollections.observableArrayList();
        String sql = "SELECT nombre FROM autores";
        try (Connection con = ConexionBD.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) { autores.add(rs.getString("nombre")); }
        } catch (SQLException e) { }
        return autores;
    }

    public ObservableList<String> obtenerNombresGeneros() {
        ObservableList<String> generos = FXCollections.observableArrayList();
        String sql = "SELECT nombre_genero FROM generos";
        try (Connection con = ConexionBD.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) { generos.add(rs.getString("nombre_genero")); }
        } catch (SQLException e) { }
        return generos;
    }

    public int obtenerIdAutorPorNombre(String nombre) {
        String sql = "SELECT id FROM autores WHERE nombre = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) { }
        return -1;
    }

    public int obtenerIdGeneroPorNombre(String nombre) {
        String sql = "SELECT id FROM generos WHERE nombre_genero = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) { }
        return -1;
    }
}