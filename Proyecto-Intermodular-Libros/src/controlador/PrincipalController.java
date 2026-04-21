package controlador;

import dao.LibroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Libro;
import java.util.List;

public class PrincipalController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Label lblSaludo;

    // Configuración de la tabla con el modelo Libro
    @FXML
    private TableView<Libro> tablaLibros;

    @FXML
    private TableColumn<Libro, String> colTitulo;

    @FXML
    private TableColumn<Libro, String> colAutor;

    @FXML
    private TableColumn<Libro, String> colGenero;

    // Instancia del DAO para acceder a los datos
    private LibroDAO libroDAO = new LibroDAO();

    @FXML
    public void initialize() {
        //Vincular las columnas con los atributos del modelo Libro
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("nombreAutor"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("nombreGenero"));

        //Cargar los libros desde la base de datos
        cargarLibrosEnTabla();

        //botón cerrar sesión
        btnCerrarSesion.setOnAction(event -> {
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.close();
        });
    }

    private void cargarLibrosEnTabla() {
        //Obtenemos la lista desde el DAO
        List<Libro> lista = libroDAO.obtenerLibrosParaTabla();

        //La convertimos en una lista observable para JavaFX
        ObservableList<Libro> listaObservable = FXCollections.observableArrayList(lista);

        //Pasar datos a la tabla
        tablaLibros.setItems(listaObservable);
    }

    public void setNombreUsuario(String nombre) {
        lblSaludo.setText("¡Bienvenido/a, " + nombre + "!");
    }
}