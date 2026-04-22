package controlador;

import dao.LibroDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Libro;
import java.io.IOException;

public class PrincipalController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnAñadirLibro;

    @FXML
    private Label lblSaludo;

    @FXML
    private TableView<Libro> tablaLibros;

    @FXML
    private TableColumn<Libro, String> colTitulo;

    @FXML
    private TableColumn<Libro, String> colAutor;

    @FXML
    private TableColumn<Libro, String> colGenero;

    private LibroDAO libroDAO = new LibroDAO();

    @FXML
    public void initialize() {
        // Vincula las columnas con los atributos del modelo Libro
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("nombreAutor"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("nombreGenero"));

        // Carga los libros desde la base de datos al iniciar
        cargarLibrosEnTabla();

        // Botón cerrar sesión
        btnCerrarSesion.setOnAction(event -> {
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.close();
        });

        if (btnAñadirLibro != null) {
            btnAñadirLibro.setOnAction(event -> abrirFormularioNuevoLibro());
        }
    }

    //abre ventana para añadir nuevo libro
    @FXML
    private void abrirFormularioNuevoLibro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/NuevoLibroView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Añadir nuevo libro a mi colección");

            //Esto bloquea la ventana principal hasta que se cierre la pequeña
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setScene(new Scene(root));
            stage.showAndWait(); // Uso showAndWait() para detener la ejecución aqui hasta que se cierre la ventana emergente.
            // Esto permite que, justo después de cerrar el formulario, la tabla se refresque automáticamente.

            //esto se ejecuta solo cuando la ventana de arriba se cierra.
            cargarLibrosEnTabla();

        } catch (IOException e) {
            System.err.println("Error al cargar la ventana de nuevo libro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void cargarLibrosEnTabla() {
        ObservableList<Libro> libros = libroDAO.obtenerLibrosParaTabla();
        tablaLibros.setItems(libros);
    }

    public void setNombreUsuario(String nombre) {
        lblSaludo.setText("¡Bienvenido/a, " + nombre + "!");
    }
}