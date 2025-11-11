package com.stylz.app;

import com.stylz.app.Firebase.FirebaseAuthService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class SignUpController {

    @FXML
    private Label messageLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private VBox mainVBox;

    @FXML
    public void initialize() {
        // Request focus to ensure text fields work immediately
        mainVBox.requestFocus();
    }

    @FXML
    private void handleCreateAccount(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            System.out.println("Please fill in all fields!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords don't match!");
            return;
        }
        //Call Firebase Sign-Up
        String token = FirebaseAuthService.register(username, password);
        System.out.println("SIGNUP TOKEN = " + token);

        if (token != null) {
            System.out.println("✅ Account created for: " + username);

            // ✅ Navigate to homepage
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Homepage.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Account creation failed!");
        }
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/LoginRegister.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
