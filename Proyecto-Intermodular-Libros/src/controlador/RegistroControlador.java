package controlador;

import dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Usuario;

public class RegistroControlador {

    @FXML
    private TextField txtNombreUsuario;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfirmarPassword;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnVolver;

    private UsuarioDAO UsuarioDAO = new UsuarioDAO();

    @FXML
    public void initialize() {
        // Acción del botón Registrar
        btnRegistrar.setOnAction(event -> registrarNuevoUsuario());

        // Acción del botón Volver (Cierra esta ventana)
        btnVolver.setOnAction(event -> {
            Stage stage = (Stage) btnVolver.getScene().getWindow();
            stage.close();
        });
    }

    private void registrarNuevoUsuario() {
        String user = txtNombreUsuario.getText();
        String email = txtEmail.getText();
        String pass = txtPassword.getText();
        String confPass = txtConfirmarPassword.getText();

        // Validaciones básicas
        if (user.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        if (!pass.equals(confPass)) {
            mostrarAlerta("Error", "Las contraseñas no coinciden.");
            return;
        }

        // Crear objeto y guardar en BD
        Usuario nuevoUsuario = new Usuario(user, email, pass);
        if (UsuarioDAO.registrarUsuario(nuevoUsuario)) {
            mostrarAlerta("Éxito", "Usuario registrado correctamente.");
            // Opcional: Cerrar ventana tras registro
            ((Stage) btnRegistrar.getScene().getWindow()).close();
        } else {
            mostrarAlerta("Error", "No se pudo registrar el usuario. El nombre o email podrían estar duplicados.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}