
package org.example.iub_lfe_management.shan;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CheckEligibilityController {

    @FXML
    private TextField studentIdField;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label departmentLabel;

    @FXML
    private Label creditsLabel;

    @FXML
    private Label eligibilityLabel;

    @FXML
    private void searchStudent() {
        String studentId = studentIdField.getText().trim();

        if (studentId.isEmpty()) {
            showMessage("Input Error", "Please enter your Student ID.");
            return;
        }

        String resourcePath =
                "/org/example/iub_lfe_management/shan/students.txt";

        try (InputStream input =
                     getClass().getResourceAsStream(resourcePath)) {

            if (input == null) {
                showMessage(
                        "File Error",
                        "Student data file was not found.\n"
                                + "Expected path: " + resourcePath
                );
                return;
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(input, StandardCharsets.UTF_8))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    if (line.isEmpty()
                            || line.startsWith("studentId|")) {
                        continue;
                    }

                    String[] data = line.split("\\|", -1);

                    if (data.length != 4) {
                        continue;
                    }

                    if (data[0].trim().equals(studentId)) {
                        displayStudent(data);
                        return;
                    }
                }
            }

            clearStudentDetails();
            eligibilityLabel.setText("Eligibility: Student Not Found");

            showMessage(
                    "Search Result",
                    "No student record found for ID: " + studentId
            );

        } catch (IOException e) {
            e.printStackTrace();
            showMessage(
                    "File Error",
                    "Unable to read student records: " + e.getMessage()
            );
        }
    }

    private void displayStudent(String[] data) {
        String studentId = data[0].trim();
        String studentName = data[1].trim();
        String department = data[2].trim();
        String creditsText = data[3].trim();

        idLabel.setText("Student ID: " + studentId);
        nameLabel.setText("Student Name: " + studentName);
        departmentLabel.setText("Department: " + department);
        creditsLabel.setText("Total Credits: " + creditsText);

        try {
            int credits = Integer.parseInt(creditsText);

            // Demonstration rule only; use official IUB criteria.
            if (credits >= 80) {
                eligibilityLabel.setText(
                        "Eligibility: Eligible "
                );
            } else {
                eligibilityLabel.setText(
                        "Eligibility: Not Eligible only Above 80 Credit"
                );
            }

        } catch (NumberFormatException e) {
            eligibilityLabel.setText(
                    "Eligibility: Invalid Credits Data"
            );
        }
    }

    private void clearStudentDetails() {
        idLabel.setText("Student ID: -");
        nameLabel.setText("Student Name: -");
        departmentLabel.setText("Department: -");
        creditsLabel.setText("Total Credits: -");
    }

    @FXML
    private void goBack() {
        try {
            java.net.URL url = getClass().getResource(
                    "/org/example/iub_lfe_management/shan/StudentDashboard.fxml"
            );

            if (url == null) {
                showMessage(
                        "File Error",
                        "StudentDashboard.fxml was not found."
                );
                return;
            }

            FXMLLoader loader = new FXMLLoader(url);
            Scene scene = new Scene(loader.load());

            Stage stage = getCurrentStage();
            stage.setScene(scene);
            stage.setTitle("Student Dashboard - IUB LFE");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showMessage(
                    "Navigation Error",
                    "Unable to return to the Student Dashboard."
            );
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
