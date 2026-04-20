package controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexion.ConexionBD;

public class LoginController {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnEntrar;

    @FXML
    public void initialize() {
        btnEntrar.setOnAction(event -> intentarLogin());
    }

    private void intentarLogin() {
        String user = txtUsuario.getText();
        String pass = txtPassword.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            mostrarAlerta("Campos incompletos", "Por favor, introduce usuario y contraseña.");
            return;
        }

        if (validarCredenciales(user, pass)) {
            System.out.println("Sesión iniciada: " + user);
            abrirPrincipal(user);
        } else {
            mostrarAlerta("Error de autenticación", "El usuario o la contraseña son incorrectos.");
        }
    }

        private void abrirPrincipal(String nombreUsuario) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PrincipalView.fxml"));
            Parent root = loader.load();

            PrincipalController controller = loader.getController();
            controller.setNombreUsuario(nombreUsuario);

            Stage stage = new Stage();
            stage.setTitle("Panel Principal - Biblioteca");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) btnEntrar.getScene().getWindow();
            currentStage.close();

            } catch (Exception e) {
            System.err.println("Error al cargar la ventana principal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean validarCredenciales(String user, String pass) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND password = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null) return false;
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.err.println("Error en la consulta de login: " + e.getMessage());
            return false;
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