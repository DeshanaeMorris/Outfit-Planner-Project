package com.stylz.app;

import com.google.cloud.firestore.SetOptions;
import com.stylz.app.Firebase.FirestoreDatabase;
import com.stylz.app.Firebase.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OnlineStoreController {

    @FXML private Label totalPrice;
    @FXML private ListView<String> cartList;
    @FXML private Label balanceLabel;

    @FXML private ImageView whiteTop, blueShorts, blackHeels, whiteDress;
    @FXML private ImageView hat, bag, jewelry, sunglasses;

    private double balance = 140.00;
    private int total = 0;

    private int totalSpent = 0;

    // ---------------- ADD TO CART ----------------
    @FXML private void selectWhiteTop(MouseEvent e)     { addToCart("White Top", 20); }
    @FXML private void selectBlueShorts(MouseEvent e)   { addToCart("Blue Shorts", 20); }
    @FXML private void selectBlackHeels(MouseEvent e)   { addToCart("Black Heels", 20); }
    @FXML private void selectWhiteDress(MouseEvent e)   { addToCart("White Dress", 40); }
    @FXML private void selectHat(MouseEvent e)          { addToCart("Hat", 10); }
    @FXML private void selectBag(MouseEvent e)          { addToCart("Bag", 10); }
    @FXML private void selectJewelry(MouseEvent e)      { addToCart("Jewelry", 10); }
    @FXML private void selectSunglasses(MouseEvent e)   { addToCart("Sunglasses", 10); }

    private void addToCart(String item, int price) {
        cartList.getItems().add(item + " - $" + price);
        total += price;
        totalPrice.setText("$" + total);
    }

    // ---------------- REMOVE ITEM ----------------
    @FXML
    private void handleRemoveItem() {
        String selected = cartList.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        int price = Integer.parseInt(selected.substring(selected.lastIndexOf("$") + 1));
        cartList.getItems().remove(selected);

        total -= price;
        if (total < 0) total = 0;

        totalPrice.setText("$" + total);
    }

    // ---------------- POPUP ----------------
    private void showPopup(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    // ---------------- CHECKOUT (UPDATED) ----------------
    @FXML
    private void handleCheckout() {

        if (cartList.getItems().isEmpty()) {
            showPopup("Cart Empty", "There are no items in your cart.");
            return;
        }

        if (total > balance) {
            showPopup("Insufficient Balance", "You do not have enough money to complete this purchase.");
            return;
        }

        // Deduct balance
        balance -= total;
        balanceLabel.setText(String.format("Balance: $%.2f", balance));

        // Add this purchase to lifetime spent
        totalSpent += total; // 🔥 FIX: totalSpent updates correctly now

        // Add clean item names to purchased list
        for (String cartItem : cartList.getItems()) {
            String itemName = cartItem.split(" - ")[0];
            LocalInventory.purchased.add(itemName);
        }

        // ---------- SAVE TO FIRESTORE ----------
        String uid = UserSession.getUserId();
        Map<String, Object> purchase = new HashMap<>();
        purchase.put("clothesOwned", LocalInventory.purchased);
        purchase.put("totalSpent", totalSpent);
        purchase.put("balance", balance);

        FirestoreDatabase.getFirestore().collection("users")
                .document(uid)
                .set(purchase, SetOptions.merge());

        // Clear cart UI
        cartList.getItems().clear();
        total = 0;
        totalPrice.setText("$0");

        showPopup("Success", "Purchase completed!");
    }


    // ---------------- BACK BUTTON ----------------
    @FXML
    private void handleBack(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
            Parent homepageRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(homepageRoot));
            stage.show();

        } catch (IOException e) {
            System.err.println("Error loading homepage: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

