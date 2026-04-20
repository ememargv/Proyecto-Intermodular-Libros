package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class PrincipalController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Label lblSaludo;

    //Tabla
    @FXML
    private TableView<?> tablaLibros;

    @FXML
    private TableColumn<?, ?> colTitulo;

    @FXML
    private TableColumn<?, ?> colAutor;

    @FXML
    private TableColumn<?, ?> colGenero;
   //Hasta aqui

    @FXML
    public void initialize() {
        // Vinculación del botón por código
        btnCerrarSesion.setOnAction(event -> {
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.close();
        });
    }

    public void setNombreUsuario(String nombre) {
        lblSaludo.setText("¡Bienvenido/a, " + nombre + "!");
    }
}