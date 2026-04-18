package dao;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {

    // Este método devuelve la lista de nombres de géneros desde la BD
    public List<String> listarGeneros() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre_genero FROM generos ORDER BY nombre_genero ASC";

        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(rs.getString("nombre_genero"));
            }
        } catch (Exception e) {
            System.out.println("Error al listar géneros: " + e.getMessage());
        }
        return lista;
    }
}