
package org.example.iub_lfe_management.shan;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;

public class StudentDashboardController {

    @FXML
    private void checkEligibility() {
        try {
            URL url = getClass().getResource(
                    "/org/example/iub_lfe_management/shan/CheckEligibility.fxml"
            );

            if (url == null) {
                showMessage(
                        "File Not Found",
                        "CheckEligibility.fxml file paoa jacche na.\n\n"
                                + "Please check src/main/resources folder."
                );
                return;
            }

            FXMLLoader loader = new FXMLLoader(url);
            Scene scene = new Scene(loader.load());

            Stage stage = getCurrentStage();
            stage.setScene(scene);
            stage.setTitle("Check LFE Eligibility");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Navigation Error", e.toString());
        }
    }

    @FXML
    private void registerLFE() {
        showMessage("LFE 201 Registration",
                "LFE 201 registration form will be added here.");
    }

    @FXML
    private void viewRegistrationStatus() {
        showMessage("Registration Status",
                "Your registration status will be shown here.");
    }

    @FXML
    private void viewGroupInformation() {
        showMessage("Group/Section Information",
                "Your group and section information will be shown here.");
    }

    @FXML
    private void viewSchedule() {
        showMessage("LFE Schedule",
                "Your LFE schedule will be shown here.");
    }

    @FXML
    private void viewAttendance() {
        showMessage("LFE Attendance",
                "Your attendance information will be shown here.");
    }

    @FXML
    private void viewResult() {
        showMessage("LFE Result",
                "Your LFE result will be shown here.");
    }

    @FXML
    private void viewAnnouncements() {
        showMessage("LFE Announcements",
                "LFE announcements will be shown here.");
    }

    @FXML
    private void logout() {
        try {
            URL url = getClass().getResource(
                    "/org/example/iub_lfe_management/LoginView.fxml"
            );

            if (url == null) {
                showMessage("Error", "LoginView.fxml file not found.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(url);
            Scene scene = new Scene(loader.load());

            Stage stage = getCurrentStage();
            stage.setScene(scene);
            stage.setTitle("IUB LFE Management System");
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            showMessage("Logout Error", e.getMessage());
        }
    }

    private Stage getCurrentStage() {
        for (Window window : Window.getWindows()) {
            if (window.isShowing() && window instanceof Stage) {
                return (Stage) window;
            }
        }

        throw new IllegalStateException("Application window not found.");
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
