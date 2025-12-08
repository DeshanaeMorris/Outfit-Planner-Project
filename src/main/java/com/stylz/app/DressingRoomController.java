package com.stylz.app;
import com.stylz.app.Firebase.FirebaseAuthService;
import com.stylz.app.Firebase.OutfitService;
import com.stylz.app.model.Outfit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


public class DressingRoomController {

    // Firebase outfit service
    private final OutfitService outfitService = new OutfitService();

    // Model display layers
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView modelTop;
    @FXML
    private ImageView modelBottom;
    @FXML
    private ImageView modelShoes;
    // For hat/sunglasses
    @FXML
    private ImageView modelAccessory1;
    // For bag/jewelry
    @FXML
    private ImageView modelAccessory2;
    @FXML
    private ImageView modelAccessory3; // for sunglasses
    @FXML
    private ImageView modelAccessory4;
    @FXML
    private ImageView modelBase;

    private String selectedTop;
    private String selectedBottom;
    private String selectedShoes;
    private String selectedAccessory1;
    private String selectedAccessory2;
    private String selectedAccessory3;
    private String selectedAccessory4;



    //Initialize method
    @FXML
    private void initialize() {
        try {
            // Load selected model from ModelSelectionController
            Image selected = new Image(
                    getClass().getResourceAsStream(ModelSelectionController.selectedModelPath)
            );
            modelBase.setImage(selected);
            System.out.println("Loaded selected model: " + ModelSelectionController.selectedModelPath);
        } catch (Exception e) {
            System.out.println("Error loading selected model: " + e.getMessage());
        }
    }
    //Select white top

    @FXML
    private void selectWhiteTop(MouseEvent event) {
        try {
            Image topImage = new Image(getClass().getResourceAsStream("/images/DisplayTop1.PNG"));
            modelTop.setImage(topImage);
            selectedTop = "white_top";
            System.out.println("White top selected");
        } catch (Exception e) {
            System.out.println("Error loading white top: " + e.getMessage());
        }
    }


    @FXML
    private void selectPinkShirt(MouseEvent event) {
        try {
            Image shirtImage = new Image(getClass().getResourceAsStream("/images/Top2-pic.png"));
            modelTop.setImage(shirtImage);
            selectedTop = "pink_shirt";
            System.out.println("Pink shirt selected");
        } catch (Exception e) {
            System.out.println("Error loading pink shirt: " + e.getMessage());
        }
    }

    //Bottom
    @FXML
    private void selectBlueShorts(MouseEvent event) {
        try {
            Image shortsImage = new Image(getClass().getResourceAsStream("/images/Bottom1-pic.png"));
            modelBottom.setImage(shortsImage);
            selectedBottom = "blue_shorts";
            System.out.println("Blue shorts selected");
        } catch (Exception e) {
            System.out.println("Error loading blue shorts: " + e.getMessage());
        }
    }

    //
    @FXML
    private void selectBlackSkirt(MouseEvent event) {
        try {
            Image skirtImage = new Image(getClass().getResourceAsStream("/images/black-skirt.png"));
            modelBottom.setImage(skirtImage);
            selectedBottom = "black_skirt";
            System.out.println("Black skirt selected");
        } catch (Exception e) {
            System.out.println("Error loading black skirt: " + e.getMessage());
        }
    }

    //heels
    @FXML
    private void selectBlackHeels(MouseEvent event) {
        try {
            Image shoesImage = new Image(getClass().getResourceAsStream("/images/Shoes1-pic.png"));
            modelShoes.setImage(shoesImage);
            selectedShoes = "black_heels";
            System.out.println("Black heels selected");
        } catch (Exception e) {
            System.out.println("Error loading shoes: " + e.getMessage());
        }
    }


    //
    @FXML
    private void selectWhiteDress(MouseEvent event) {
        try {
            Image dressImage = new Image(getClass().getResourceAsStream("/images/Dress1-pic.png"));
            modelTop.setImage(dressImage);
            modelBottom.setImage(null); // Clear bottom since dress covers it
            selectedTop = "white_dress";
            selectedBottom = null;
            System.out.println("White dress selected");
        } catch (Exception e) {
            System.out.println("Error loading dress: " + e.getMessage());
        }
    }

    @FXML
    private void selectHat(MouseEvent event) {
        try {
            Image hatImage = new Image(getClass().getResourceAsStream("/images/Hat1-pic.png"));
            modelAccessory1.setImage(hatImage);
            selectedAccessory1 = "hat_1";
            System.out.println("Hat selected");
        } catch (Exception e) {
            System.out.println("Error loading hat: " + e.getMessage());
        }
    }

    @FXML
    private void selectBag(MouseEvent event) {
        try {
            Image bagImage = new Image(getClass().getResourceAsStream("/images/Bag1-pic.png"));
            modelAccessory2.setImage(bagImage);
            selectedAccessory2 = "bag_1";
            System.out.println("Bag selected");
        } catch (Exception e) {
            System.out.println("Error loading bag: " + e.getMessage());
        }
    }


    @FXML
    private void selectJewelry(MouseEvent event) {
        try {
            Image jewelryImage = new Image(getClass().getResourceAsStream("/images/Jewelry1.PNG"));
            modelAccessory3.setImage(jewelryImage);
            selectedAccessory3 = "jewelry_1";
            System.out.println("Jewelry selected");
        } catch (Exception e) {
            System.out.println("Error loading jewelry: " + e.getMessage());
        }
    }


    @FXML
    private void selectSunglasses(MouseEvent event) {
        try {
            Image sunglassesImage = new Image(getClass().getResourceAsStream("/images/Glasses1-pic.png"));
            modelAccessory4.setImage(sunglassesImage);
            selectedAccessory4 = "sunglasses_1";
            System.out.println("Sunglasses selected");
        } catch (Exception e) {
            System.out.println("Error loading sunglasses: " + e.getMessage());
        }
    }

    @FXML
    private void handleChangeModel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/ChangeModelPopup.fxml"));
            AnchorPane popup = loader.load();

            // Link popup to this controller
            ChangeModelPopupController popupController = loader.getController();
            popupController.setParent(this);

            // Add popup ON TOP of dressing room
            AnchorPane root = (AnchorPane) ((Node) event.getSource()).getScene().getRoot();
            root.getChildren().add(popup);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error opening popup.");
        }

    }

    @FXML
    public void handleReset() {
        modelTop.setImage(null);
        modelBottom.setImage(null);
        modelShoes.setImage(null);
        modelAccessory1.setImage(null);
        modelAccessory2.setImage(null);
        modelAccessory3.setImage(null);
        modelAccessory4.setImage(null);

        selectedTop = null;
        selectedBottom = null;
        selectedShoes = null;
        selectedAccessory1 = null;
        selectedAccessory2 = null;
        selectedAccessory3 = null;
        selectedAccessory4 = null;

        System.out.println("Outfit reset - all clothing cleared!");
    }


    @FXML
    private void handleSave(ActionEvent event) {
        System.out.println("=== OUTFIT SAVED ===");

        System.out.println("Top: " + (modelTop.getImage() != null ? "Selected" : "None"));
        System.out.println("Bottom: " + (modelBottom.getImage() != null ? "Selected" : "None"));
        System.out.println("Shoes: " + (modelShoes.getImage() != null ? "Selected" : "None"));
        System.out.println("Accessory 1: " + (modelAccessory1.getImage() != null ? "Selected" : "None"));
        System.out.println("Accessory 2: " + (modelAccessory2.getImage() != null ? "Selected" : "None"));
        System.out.println("Accessory 3: " + (modelAccessory2.getImage() != null ? "Selected" : "None"));
        System.out.println("Accessory 4: " + (modelAccessory2.getImage() != null ? "Selected" : "None"));
        System.out.println("==================");

        //Build list of selected item IDs
        List<String> items = new ArrayList<>();
        if (selectedTop != null) items.add(selectedTop);
        if (selectedBottom != null) items.add(selectedBottom);
        if (selectedShoes != null) items.add(selectedShoes);
        if (selectedAccessory1 != null) items.add(selectedAccessory1);
        if (selectedAccessory2 != null) items.add(selectedAccessory2);
        if (selectedAccessory3 != null) items.add(selectedAccessory3);
        if (selectedAccessory4 != null) items.add(selectedAccessory4);

        //Get current logged-in user
        String userId = FirebaseAuthService.getCurrentUserUid();

        if (userId == null) {
            System.out.println("⚠️ USER IS NULL — NOT LOGGED IN");
        } else {
            Outfit outfit = new Outfit(userId, items);
            try {
                outfitService.saveOutfit(outfit);
                System.out.println("✅ Outfit successfully saved to Firestore!");
            } catch (Exception e) {
                System.out.println("❌ Firestore save failed:");
                e.printStackTrace();
            }
        }

        // NOW navigate
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/GameEnd.fxml"));
            Parent endRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene endScene = new Scene(endRoot);

            stage.setScene(endScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void goBack(ActionEvent event) {
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
    }
}
