package controlador;

import dao.LibroDAO;
import dao.ColeccionDAO;
import modelo.Libro;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NuevoLibroController {

    @FXML private TextField txtTitulo;
    @FXML private ComboBox<String> cbAutor;
    @FXML private ComboBox<String> cbGenero;
    @FXML private ComboBox<String> cbEstado;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;

    private LibroDAO libroDAO = new LibroDAO();
    private ColeccionDAO coleccionDAO = new ColeccionDAO();
    private int idUsuarioLogueado;

    @FXML
    public void initialize() {
        cbAutor.setItems(libroDAO.obtenerNombresAutores());
        cbGenero.setItems(libroDAO.obtenerNombresGeneros());
        cbEstado.setItems(FXCollections.observableArrayList("Pendiente", "Leyendo", "Leído"));

        btnCancelar.setOnAction(e -> cerrarVentana());
        btnGuardar.setOnAction(e -> guardarLibro());
    }

    private void guardarLibro() {
        String titulo = txtTitulo.getText();
        String nombreAutor = cbAutor.getValue();
        String nombreGenero = cbGenero.getValue();
        String estado = cbEstado.getValue();

        if (titulo.isEmpty() || nombreAutor == null || nombreGenero == null || estado == null) {
            mostrarAlerta("Campos obligatorios", "Por favor, rellena todos los campos.");
            return;
        }

        int idAutor = libroDAO.obtenerIdAutorPorNombre(nombreAutor);
        int idGenero = libroDAO.obtenerIdGeneroPorNombre(nombreGenero);

        if (idAutor != -1 && idGenero != -1) {
            Libro nuevo = new Libro(titulo, null, idAutor, idGenero);

            //Inserta el libro y obtiene su ID automático
            int idLibroNuevo = libroDAO.insertarLibroYObtenerId(nuevo);

            if (idLibroNuevo != -1) {
                //Inserta en la tabla colección con el ID del usuario
                boolean exito = coleccionDAO.agregarLibroAColeccion(idUsuarioLogueado, idLibroNuevo, estado);

                if (exito) {
                    System.out.println("LOG: Libro añadido a la colección del usuario " + idUsuarioLogueado);
                    cerrarVentana();
                } else {
                    mostrarAlerta("Error", "No se pudo añadir el libro a tu colección.");
                }
            } else {
                mostrarAlerta("Error", "No se pudo crear el libro en el catálogo.");
            }
        }
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void setIdUsuarioLogueado(int id) {
        this.idUsuarioLogueado = id;
    }
}