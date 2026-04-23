package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnEntrar;
    @FXML private Hyperlink btnIrRegistro;

    @FXML
    public void initialize() {
        btnEntrar.setOnAction(event -> intentarLogin());
        btnIrRegistro.setOnAction(event -> abrirRegistro());
    }

    private void intentarLogin() {
        String user = txtUsuario.getText();
        String pass = txtPassword.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            mostrarAlerta("Campos incompletos", "Por favor, introduce usuario y contraseña.");
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioLogueado = usuarioDAO.verificarLogin(user, pass);

        if (usuarioLogueado != null) {
            System.out.println("Sesión iniciada: " + usuarioLogueado.getNombreUsuario());
            // PASAMOS EL OBJETO USUARIO COMPLETO
            abrirPrincipal(usuarioLogueado);
        } else {
            mostrarAlerta("Error de autenticación", "Usuario o contraseña incorrectos.");
        }
    }

    private void abrirRegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/RegistroView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Registro de Usuario - Biblioteca");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void abrirPrincipal(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PrincipalView.fxml"));
            Parent root = loader.load();

            PrincipalController controller = loader.getController();
            //Pasamos ID y Nombre a la principal
            controller.setDatosUsuario(usuario.getId(), usuario.getNombreUsuario());

            Stage stage = new Stage();
            stage.setTitle("Panel Principal - Biblioteca");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) btnEntrar.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}