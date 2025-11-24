package com.stylz.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameEndController {

    @FXML
    private ImageView modelBase;

    @FXML
    private void initialize() {
        try {
            // Load the selected model from ModelSelectionController
            Image selectedModel = new Image(
                    getClass().getResourceAsStream(ModelSelectionController.selectedModelPath)
            );
            modelBase.setImage(selectedModel);
            System.out.println("Game End - Loaded model: " + ModelSelectionController.selectedModelPath);
        } catch (Exception e) {
            System.out.println("Error loading model in Game End: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLogOut(ActionEvent event) {
        try {
            // Go back to log in screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Login.fxml"));
            Parent loginRoot = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene loginScene = new Scene(loginRoot);
            stage.setScene(loginScene);
            stage.show();
            System.out.println("Logged out successfully!");
        } catch (Exception e) {
            System.err.println("Error logging out: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleReplay(ActionEvent event) {
        try {
            // Go back to dressing room to create a new outfit
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Homepage.fxml"));
            Parent dressingRoomRoot = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene dressingRoomScene = new Scene(dressingRoomRoot);
            stage.setScene(dressingRoomScene);
            stage.show();
            System.out.println("Replaying - back to dressing room!");
        } catch (Exception e) {
            System.err.println("Error going to dressing room: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleGoBack(ActionEvent event) {
        try {
            // Go back to homepage
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/DressingRoom.fxml"));
            Parent homepageRoot = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene homepageScene = new Scene(homepageRoot);
            stage.setScene(homepageScene);
            stage.show();
            System.out.println("Back to Dressing Room!");
        } catch (Exception e) {
            System.err.println("Error going to Dressing Room: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleGoCatalog(ActionEvent event) {
        try {
            System.out.println("Saving outfit to catalog...");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Catalog.fxml"));
            Parent catalogRoot = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene catalogScene = new Scene(catalogRoot);
            stage.setScene(catalogScene);
            stage.show();
            System.out.println("Navigated to catalog!");
        } catch (Exception e) {
            System.err.println("Error going to catalog: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
