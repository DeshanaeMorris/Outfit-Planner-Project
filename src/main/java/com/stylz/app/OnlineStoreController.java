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


    @FXML private ImageView whiteTop, blueShorts, blackHeels, whiteDress;
    @FXML private ImageView hat, bag, jewelry, sunglasses;

    private int total = 0;

    // Add to cart
    @FXML private void selectWhiteTop(MouseEvent e)     { addToCart("White Top", 20); }
    @FXML private void selectBlueShorts(MouseEvent e)   { addToCart("Blue Shorts", 20); }
    @FXML private void selectBlackHeels(MouseEvent e)   { addToCart("Black Heels", 20); }
    @FXML private void selectWhiteDress(MouseEvent e)   { addToCart("White Dress", 40); }
    @FXML private void selectHat(MouseEvent e)         { addToCart("Hat", 10); }
    @FXML private void selectBag(MouseEvent e)         { addToCart("Bag", 10); }
    @FXML private void selectJewelry(MouseEvent e)     { addToCart("Jewelry", 10); }
    @FXML private void selectSunglasses(MouseEvent e)  { addToCart("Sunglasses", 10); }


    private void addToCart(String item, int price) {
        LocalInventory.purchased.add(item);
        cartList.getItems().add(item + " - $" + price);
        total += price;
        totalPrice.setText("$" + total);
    }


    // CHECKOUT
    @FXML private void handleCheckout() {

        String uid = UserSession.getUserId(); // User ID from your login

        Map<String, Object> purchase = new HashMap<>();
        purchase.put("clothesOwned", cartList.getItems());     // Save cart items
        purchase.put("totalSpent", total);                     // You can track later too

        FirestoreDatabase.getFirestore().collection("users")
                .document(uid)
                .set(purchase, SetOptions.merge());

        System.out.println("Saved to Firestore ✔ Purchased: " + cartList.getItems());
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

