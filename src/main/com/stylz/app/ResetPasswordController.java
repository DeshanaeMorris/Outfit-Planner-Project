package com.stylz.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

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

        System.out.println("Reset link sent to: " + email);

        try {
            // Navigate to Authentication screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Authentication.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
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