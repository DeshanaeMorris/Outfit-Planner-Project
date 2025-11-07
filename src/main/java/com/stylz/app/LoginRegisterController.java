package com.stylz.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class LoginRegisterController {

    @FXML
    private void handleRegister(ActionEvent event) {
        try {
            System.out.println("Register button clicked!");

            // Load the Sign Up screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/SignUp.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        try {
            System.out.println("Login button clicked!");

            // Load the Login screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Login.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}