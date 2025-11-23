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
    private ImageView musicImage;

    @FXML
    private ImageView soundsImage;

    private void playPopEffect(ImageView image) {
        ScaleTransition pop = new ScaleTransition(Duration.millis(300), image);
        pop.setFromX(1.0);
        pop.setFromY(1.0);
        pop.setToX(1.3);
        pop.setToY(1.3);
        pop.setCycleCount(2);
        pop.setAutoReverse(true);
        pop.play();

        // Rotation animation
        RotateTransition rotate = new RotateTransition(Duration.millis(400), image);
        rotate.setFromAngle(0);
        rotate.setToAngle(10);
        rotate.setCycleCount(2);
        rotate.setAutoReverse(true);

        // Play both animations together
        ParallelTransition combined = new ParallelTransition(pop, rotate);
        combined.play();
    }

    @FXML
    private void handleMusic(ActionEvent event) {
        System.out.println("Music settings clicked!");
        playPopEffect(musicImage);
    }

    @FXML
    private void handleSounds(ActionEvent event) {
        System.out.println("Sounds settings clicked!");
        playPopEffect(soundsImage);
    }

    @FXML
    private void handleHome(ActionEvent event) {
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
