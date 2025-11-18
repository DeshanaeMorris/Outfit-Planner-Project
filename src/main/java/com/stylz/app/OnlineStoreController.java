package com.stylz.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;

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
    }
}
