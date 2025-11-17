package com.stylz.app;

import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.FirebaseAuth;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class SignUpController {

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
        System.out.println("Create Account button clicked");

        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            System.out.println("Please fill in all fields!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords don't match!");
            showAlert("Password Error", "Passwords don't match.");
            showAlert("Missing Information", "Please fill in all fields.");
            return;
        }

        if (password.length() < 6) {
            System.out.println("Password too short!");
            showAlert("Password Error", "Password must be at least 6 characters long.");
            return;
        }

        try {
            FirestoreContext.init();

            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(username)
                    .setPassword(password);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            System.out.println("User created: " + userRecord.getEmail());
            showAlert("Success", "Account created for: " + userRecord.getEmail());


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/LoginRegister.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
