package controlador;

import dao.LibroDAO;
import dao.ColeccionDAO;
import javafx.collections.FXCollections;
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
    @FXML private Button btnEliminar;
    @FXML private TextField txtBuscar;
    @FXML private Label lblSaludo;
    @FXML private TableView<Libro> tablaLibros;
    @FXML private TableColumn<Libro, String> colTitulo;
    @FXML private TableColumn<Libro, String> colAutor;
    @FXML private TableColumn<Libro, String> colGenero;
    @FXML private TableColumn<Libro, Integer> colPuntuacion; // Nuevo

    private LibroDAO libroDAO = new LibroDAO();
    private ColeccionDAO coleccionDAO = new ColeccionDAO();
    private int idUsuarioLogueado;

    @FXML
    public void initialize() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("nombreAutor"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("nombreGenero"));
        colPuntuacion.setCellValueFactory(new PropertyValueFactory<>("puntuacion")); // Nuevo

        if (txtBuscar != null) {
            txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
                filtrarLibros(newValue);
            });
        }

        if (btnEliminar != null) {
            btnEliminar.setOnAction(event -> eliminarLibroSeleccionado());
        }

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

            cargarLibrosEnTabla();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarLibrosEnTabla() {
        if (idUsuarioLogueado > 0) {
            tablaLibros.getItems().clear();
            ObservableList<Libro> libros = libroDAO.obtenerLibrosParaTabla(this.idUsuarioLogueado);
            tablaLibros.setItems(libros);
        }
    }

    private void filtrarLibros(String texto) {
        if (texto == null || texto.isEmpty()) {
            cargarLibrosEnTabla();
        } else {
            ObservableList<Libro> todosLosLibros = libroDAO.obtenerLibrosParaTabla(this.idUsuarioLogueado);
            ObservableList<Libro> listaFiltrada = FXCollections.observableArrayList();
            String filtro = texto.toLowerCase();

            for (Libro libro : todosLosLibros) {
                if (libro.getTitulo().toLowerCase().contains(filtro) ||
                        libro.getNombreAutor().toLowerCase().contains(filtro)) {
                    listaFiltrada.add(libro);
                }
            }
            tablaLibros.setItems(listaFiltrada);
        }
    }

    private void eliminarLibroSeleccionado() {
        Libro seleccionado = tablaLibros.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            boolean ok = coleccionDAO.eliminarLibroDeColeccion(this.idUsuarioLogueado, seleccionado.getTitulo());
            if (ok) {
                cargarLibrosEnTabla();
            } else {
                mostrarAlerta("Error", "No se pudo eliminar el libro de tu colección.");
            }
        } else {
            mostrarAlerta("Atención", "Por favor, selecciona un libro de la tabla para eliminarlo.");
        }
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}