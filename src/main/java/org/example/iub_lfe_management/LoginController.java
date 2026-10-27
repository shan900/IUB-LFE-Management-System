
package org.example.iub_lfe_management;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField userIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void initialize() {
        // Login page initialization
    }

    @FXML
    private void handleLogin() {

        String id = userIdField.getText().trim();
        String password = passwordField.getText();

        if (id.isEmpty() || password.isEmpty()) {
            showAlert("Login Error",
                    "Please enter your ID and password.");
            return;
        }

        // Demo Student Login
        if (id.equals("1") && password.equals("123")) {
            openStudentDashboard();
        }

        // Demo Faculty Login
        else if (id.equals("1234") && password.equals("faculty123")) {
            showAlert("Faculty Login",
                    "Faculty dashboard will be added later.");
        }

        else {
            showAlert("Login Failed",
                    "Invalid ID or password.");
        }
    }

    private void openStudentDashboard() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/org/example/iub_lfe_management/shan/StudentDashboard.fxml"
                    )
            );

            Scene scene = new Scene(loader.load(), 650, 700);

            Stage stage = (Stage) userIdField
                    .getScene()
                    .getWindow();

            stage.setTitle("Student Dashboard - IUB LFE");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.centerOnScreen();

        } catch (IOException | RuntimeException e) {
            e.printStackTrace();

            showAlert("Error",
                    "Could not open Student Dashboard. Check the FXML file and controller package.");
        }
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
