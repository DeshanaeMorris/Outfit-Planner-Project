package com.stylz.app;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class SettingsController {

    @FXML
    private ImageView settingsHeadingImage;

    @FXML
    public void initialize() {
        // Add pop effect on hover
        if (settingsHeadingImage != null) {
            settingsHeadingImage.setOnMouseEntered(event -> playPopEffect());
        }
    }

    private void playPopEffect() {
        ScaleTransition pop = new ScaleTransition(Duration.millis(300), settingsHeadingImage);
        pop.setFromX(1.0);
        pop.setFromY(1.0);
        pop.setToX(1.15);
        pop.setToY(1.15);
        pop.setCycleCount(2);
        pop.setAutoReverse(true);
        pop.play();
    }

    @FXML
    private void handleMusic(ActionEvent event) {
        System.out.println("Music settings clicked!");
        playPopEffect();
    }

    @FXML
    private void handleSounds(ActionEvent event) {
        System.out.println("Sounds settings clicked!");
        playPopEffect();
    }

    @FXML
    private void handleHome(ActionEvent event) {
        playPopEffect();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Homepage.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleInstructions(ActionEvent event) {
        playPopEffect();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Instructions.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        playPopEffect();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Homepage.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        playPopEffect();
        try {
            System.out.println("Logging out...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/StylzWelcome.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}