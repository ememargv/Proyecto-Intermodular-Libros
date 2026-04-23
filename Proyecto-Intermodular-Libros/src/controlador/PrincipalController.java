package controlador;

import dao.LibroDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Libro;
import java.io.IOException;

public class PrincipalController {

    @FXML private Button btnCerrarSesion;
    @FXML private Button btnAñadirLibro;
    @FXML private Label lblSaludo;
    @FXML private TableView<Libro> tablaLibros;
    @FXML private TableColumn<Libro, String> colTitulo;
    @FXML private TableColumn<Libro, String> colAutor;
    @FXML private TableColumn<Libro, String> colGenero;

    private LibroDAO libroDAO = new LibroDAO();
    private int idUsuarioLogueado;

    @FXML
    public void initialize() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("nombreAutor"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("nombreGenero"));

        // No llamo aquí a cargarLibrosEnTabla() porque aún no tengo el ID
        //espera a setDatosUsuario()

        btnCerrarSesion.setOnAction(event -> {
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.close();
        });

        if (btnAñadirLibro != null) {
            btnAñadirLibro.setOnAction(event -> abrirFormularioNuevoLibro());
        }
    }

    public void setDatosUsuario(int id, String nombre) {
        this.idUsuarioLogueado = id;
        lblSaludo.setText("¡Bienvenido/a, " + nombre + "!");

        //teniendo el ID carga sus libros
        cargarLibrosEnTabla();
    }

    @FXML
    private void abrirFormularioNuevoLibro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/NuevoLibroView.fxml"));
            Parent root = loader.load();

            NuevoLibroController controller = loader.getController();
            controller.setIdUsuarioLogueado(this.idUsuarioLogueado);

            Stage stage = new Stage();
            stage.setTitle("Añadir nuevo libro a mi colección");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Refrescamos la tabla al volver
            cargarLibrosEnTabla();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarLibrosEnTabla() {
        if (idUsuarioLogueado > 0) {
            tablaLibros.getItems().clear();
            // Pedimos solo los libros de cada usuario
            ObservableList<Libro> libros = libroDAO.obtenerLibrosParaTabla(this.idUsuarioLogueado);
            tablaLibros.setItems(libros);
        }
    }
}