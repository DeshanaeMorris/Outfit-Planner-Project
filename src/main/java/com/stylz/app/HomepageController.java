package com.stylz.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

public class HomepageController {

    @FXML
    private void handleDressingRoom(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/DressingRoom.fxml"));
            Parent dressingRoomRoot = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene dressingRoomScene = new Scene(dressingRoomRoot);
            stage.setScene(dressingRoomScene);
            stage.show();

            System.out.println("Navigated to Dressing Room!");
        } catch (IOException e) {
            System.err.println("Error loading Dressing Room: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleStore(ActionEvent event) {
        System.out.println("Store clicked!");
    }

    @FXML
    private void handleCatalog(ActionEvent event) {
        System.out.println("Catalog clicked!");
    }

    @FXML
    private void handleInstructions(ActionEvent event) {
        try {
            System.out.println("Instructions clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Instructions.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSettings(ActionEvent event) {
        try {
            System.out.println("Settings clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Settings.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout(MouseEvent event) {
        try {
            System.out.println("Logging out...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/StylzWelcome.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("Error loading StylzWelcome:");
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Homepage.fxml"));
            Parent homepageRoot = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene homepageScene = new Scene(homepageRoot);
            stage.setScene(homepageScene);
            stage.show();

            System.out.println("Returned to Homepage!");
        } catch (IOException e) {
            System.err.println("Error loading homepage: " + e.getMessage());
            e.printStackTrace();
        }
    }

}