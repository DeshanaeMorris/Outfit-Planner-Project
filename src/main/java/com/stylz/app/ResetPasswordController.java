package com.stylz.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javafx.scene.control.Alert;

public class ResetPasswordController {

    @FXML
    private TextField emailField;

    @FXML
    private void handleReset(ActionEvent event) {
        String email = emailField.getText();

        if (email.isEmpty()) {
            System.out.println("Please enter your email!");
            return;
        }

        // Validate email format
        if (!email.contains("@")) {
            System.out.println("Please enter a valid email!");
            return;
        }

        // 🔥 Call Firebase password reset
        boolean success = sendPasswordResetEmail(email);

        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password Reset Sent");
            alert.setHeaderText(null);
            alert.setContentText("A reset link has been sent to your email.\n\n"
                    + "Please check your inbox and spam folder.");
            alert.showAndWait();

            try {
                // Navigate to Authentication screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Authentication.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to send reset email.");
        }
    }


    private boolean sendPasswordResetEmail(String email) {
        String apiKey = "AIzaSyBESor64BavEICoPXFx29qS6nc8vcKEW_0";
        String endpoint = "https://identitytoolkit.googleapis.com/v1/accounts:sendOobCode?key=" + apiKey;

        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            String jsonBody = String.format(
                    "{\"requestType\":\"PASSWORD_RESET\",\"email\":\"%s\"}",
                    email
            );

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input);
            }

            int response = conn.getResponseCode();
            return response == 200;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleGoBack(MouseEvent event) {
        try {
            // Go back to Login screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}