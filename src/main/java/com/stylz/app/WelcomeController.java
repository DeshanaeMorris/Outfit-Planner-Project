package com.stylz.app;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WelcomeController {

    @FXML
    private ImageView logoImageView;

    @FXML
    private Button startButton;

    @FXML
    public void initialize() {
        // Fade animation for logo
        fadeAnimation();

        // Wiggle animation for start button
        wiggleAnimation();
    }

    private void fadeAnimation() {
        FadeTransition fade = new FadeTransition(Duration.seconds(2), logoImageView);
        fade.setFromValue(0.3);
        fade.setToValue(1.0);
        fade.setCycleCount(Animation.INDEFINITE);
        fade.setAutoReverse(true);
        fade.play();
    }

    private void wiggleAnimation() {
        RotateTransition rotate = new RotateTransition(Duration.millis(100), startButton);
        rotate.setByAngle(5);
        rotate.setCycleCount(2);
        rotate.setAutoReverse(true);

        SequentialTransition wiggle = new SequentialTransition(rotate);
        wiggle.setCycleCount(Animation.INDEFINITE);
        wiggle.setDelay(Duration.seconds(2));
        wiggle.play();
    }

    @FXML
    private void handleStartButton() {
        try {
            // Load the LoginRegister page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/LoginRegister.fxml"));
            Parent root = loader.load();

            // Get the current stage and set the new scene
            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(new Scene(root, 400, 600));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading LoginRegister page: " + e.getMessage());
        }
    }
}