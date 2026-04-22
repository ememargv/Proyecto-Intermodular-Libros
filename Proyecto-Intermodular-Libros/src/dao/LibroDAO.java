package dao;

import conexion.ConexionBD;
import modelo.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class LibroDAO {

    //Obtiene los libros con JOIN para la tabla
    public ObservableList<Libro> obtenerLibrosParaTabla() {
        ObservableList<Libro> lista = FXCollections.observableArrayList();

        String sql = "SELECT l.titulo, a.nombre AS nombreAutor, g.nombre_genero AS nombreGenero " +
                "FROM libros l " +
                "JOIN autores a ON l.id_autor = a.id " +
                "JOIN generos g ON l.id_genero = g.id";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getString("titulo"),
                        rs.getString("nombreAutor"),
                        rs.getString("nombreGenero")
                );
                lista.add(libro);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener libros para la tabla: " + e.getMessage());
        }
        return lista;
    }

    // Inserta nuevo libro en el catálogo general
    public boolean insertarLibro(Libro libro) {
        String sql = "INSERT INTO libros (titulo, ruta_portada, id_autor, id_genero) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getRutaPortada());
            ps.setInt(3, libro.getIdAutor());
            ps.setInt(4, libro.getIdGenero());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar libro: " + e.getMessage());
            return false;
        }
    }

    //obtiene nombres d eautor
    public ObservableList<String> obtenerNombresAutores() {
        ObservableList<String> autores = FXCollections.observableArrayList();
        String sql = "SELECT nombre FROM autores";

        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                autores.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener nombres de autores: " + e.getMessage());
        }
        return autores;
    }
    //Obtiene nombres de la tabla generos
    public ObservableList<String> obtenerNombresGeneros() {
        ObservableList<String> generos = FXCollections.observableArrayList();
        String sql = "SELECT nombre_genero FROM generos";

        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                generos.add(rs.getString("nombre_genero"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener nombres de géneros: " + e.getMessage());
        }
        return generos;
    }
    //metodo para depurar
    public void listarLibrosConsola() {
        String sql = "SELECT * FROM libros";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " - Título: " + rs.getString("titulo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar en consola: " + e.getMessage());
        }
    }
}