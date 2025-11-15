package com.stylz.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import com.stylz.app.Firebase.FirebaseAuthService;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private Label messageLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please fill in all fields!");
            return;

        }

        String token = FirebaseAuthService.login(username, password);
        System.out.println("TOKEN = " + token);

        if (token != null) {
            messageLabel.setText("Login Success!");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Homepage.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            messageLabel.setText("Login Failed!");
        }

        System.out.println("Login successful for: " + username);
    }

    @FXML
    private void handleGoToSignUp(ActionEvent event) {
        try {
            // Navigate to Sign Up screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/SignUp.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void handleForgotUsername(ActionEvent event) {
        try {
            System.out.println("Forgot username clicked!");
            // Navigate to Reset Password screen (same as forgot password)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/ResetPassword.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleForgotPassword(ActionEvent event) {
        try {
            System.out.println("Forgot password clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/ResetPassword.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleExit(ActionEvent event) {
        System.out.println("Exiting application...");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}