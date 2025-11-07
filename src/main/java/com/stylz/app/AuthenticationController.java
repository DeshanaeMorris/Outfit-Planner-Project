package com.stylz.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class AuthenticationController {

    @FXML
    private void handleContinue(ActionEvent event) {
        try {
            System.out.println("Continuing to login...");
            // Navigate back to Login screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Login.fxml"));
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
            // Go back to Reset Password screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/ResetPassword.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}