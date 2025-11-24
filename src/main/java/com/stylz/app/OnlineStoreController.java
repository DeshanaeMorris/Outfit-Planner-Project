package com.stylz.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class OnlineStoreController {

    @FXML
    private Label totalPrice;

    @FXML
    private ImageView topYellow, topPink,topGreen, bottomYellow, bottomPink, bottomGreen;

    private int total = 0;

    @FXML
    private void selectTopYellow(MouseEvent event) {
        int topYellowPrice = 20;
        total += topYellowPrice;
        updateTotal();
    }

    @FXML
    private void selectTopPink(MouseEvent event) {
        int topPinkPrice = 25;
        total += topPinkPrice;
        updateTotal();
    }

    @FXML
    private void selectTopGreen(MouseEvent event) {
        int topGreenPrice = 50;
        total += topGreenPrice;
        updateTotal();
    }

    @FXML
    private void selectBottomYellow(MouseEvent event) {
        int bottomYellowPrice = 30;
        total += bottomYellowPrice;
        updateTotal();
    }

    @FXML
    private void selectBottomPink(MouseEvent event) {
        int bottomPinkPrice = 35;
        total += bottomPinkPrice;
        updateTotal();
    }

    @FXML
    private void selectBottomGreen(MouseEvent event) {
        int bottomGreenPrice = 40;
        total += bottomGreenPrice;
        updateTotal();
    }

    private void updateTotal() {
        totalPrice.setText("$" + total);
    }

    @FXML
    private void handleCheckout() {
        System.out.println("Checking out with total: $" + total);
    }

    @FXML
    private void handleBack(MouseEvent event) {
        System.out.println("Back button clicked");
        navigateTo("Homepage.fxml", "STYLZ Co. - Home");
    }

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
