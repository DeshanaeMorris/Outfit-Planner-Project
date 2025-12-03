package com.stylz.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

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
        try {
            // Load the homepage FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
            Parent homepageRoot = loader.load();

            // Get the current stage (window) from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Create new scene with the homepage
            Scene homepageScene = new Scene(homepageRoot);

            // Set the scene and show it
            stage.setScene(homepageScene);
            stage.show();

            System.out.println("Successfully navigated back to homepage");
        } catch (IOException e) {
            System.err.println("Error loading homepage: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Back button clicked");
    }
}
