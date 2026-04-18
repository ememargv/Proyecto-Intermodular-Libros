package dao;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    public List<String> listarAutores() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre FROM autores ORDER BY nombre ASC";

        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println("Error al listar autores: " + e.getMessage());
        }
        return lista;
    }
}