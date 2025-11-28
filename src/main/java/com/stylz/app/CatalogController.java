package com.stylz.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;


public class CatalogController {

    @FXML
    private void handleLogout() {
        System.out.println("Logging Lout from Catalog...");
        navigateTo("Login.fxml", "STYLZ Co. - Login");
    }

    @FXML
    private void handleHome() {
        System.out.println("Going to Home from Catalog...");
        navigateTo("Homepage.fxml", "STYLZ Co. - Home");
    }

    @FXML
    private void handlePhotoClick(MouseEvent event) {

        VBox photoCard = (VBox) event.getSource();

        String color = (String) photoCard.getUserData();

        System.out.println("Photo clicked: " + color + " outfit");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/DressingRoom.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

            Stage stage = getCurrentStage();
            if (stage != null) {
                stage.setScene(scene);
                stage.setTitle("Dressing Room");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load DressingRoom.fxml" + e.getMessage());
        }

    }
    @FXML
    private void handleGoBack() {
        System.out.println("Going back from Catalog...");
        navigateTo("Homepage.fxml", "STYLZ Co. - Home");
    }
    /**
     * ✅ Pure logic helper for building the FXML path.
     * This is what we'll test with JUnit.
     */
    public static String buildFxmlPath(String fxmlFile) {
        return "/com/stylz/app/" + fxmlFile;
    }


    @FXML
    private void navigateTo(String fxmlFile, String title) {
        try {
            String fxmlPath = "/com/stylz/app/" + fxmlFile;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());

            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/stylz/app/styles.css")).toExternalForm());

            Stage stage = getCurrentStage();
            if (stage != null) {
                stage.setScene(scene);
                stage.setTitle(title);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading " + fxmlFile + ": " + e.getMessage());
        }
    }

    private Stage getCurrentStage() {
        return (Stage) Stage.getWindows().stream()
                .filter(Window::isShowing)
                .findFirst()
                .orElse(null);
    }
}