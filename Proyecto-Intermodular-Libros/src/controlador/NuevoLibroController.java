package controlador;

import dao.LibroDAO;
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

    //mañana para vincular el libro al usuario que ha hecho login
    private int idUsuarioLogueado;

    @FXML
    public void initialize() {
        //Rellenar los combos desde la base de datos (LibroDAO)
        cbAutor.setItems(libroDAO.obtenerNombresAutores());
        cbGenero.setItems(libroDAO.obtenerNombresGeneros());

        //Rellenar estados
        cbEstado.setItems(FXCollections.observableArrayList("Pendiente", "Leyendo", "Leído"));

        //Decido aqui mejor qeu en scene builder
        btnCancelar.setOnAction(e -> cerrarVentana());
        btnGuardar.setOnAction(e -> guardarLibro());
    }

    private void guardarLibro() {
        String titulo = txtTitulo.getText();
        String nombreAutor = cbAutor.getValue();
        String nombreGenero = cbGenero.getValue();
        String estado = cbEstado.getValue();

        // Validación básica
        if (titulo.isEmpty() || nombreAutor == null || nombreGenero == null || estado == null) {
            mostrarAlerta("Campos obligatorios", "Por favor, rellena todos los campos.");
            return;
        }

        //para ver que la ventana funciona
        System.out.println("LOG: Intentando guardar -> " + titulo + " | Autor: " + nombreAutor);

        // Mañana implementar la búsqueda de IDs y el insert en 'libros' y 'colección'

        cerrarVentana();
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

    //Método para recibir el ID del usuario desde la pantalla principal
    public void setIdUsuarioLogueado(int id) {
        this.idUsuarioLogueado = id;
    }
}