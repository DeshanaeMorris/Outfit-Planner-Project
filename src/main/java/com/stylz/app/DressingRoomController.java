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

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;


public class DressingRoomController {
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
    @FXML
    private ImageView modelDress;
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
    @FXML
    private Button finishButton;


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
        if (finishButton != null) {
            createHeartbeatAnimation(finishButton);

        }
    }
        //Finish button animation
        private void createHeartbeatAnimation(Button button) {
            ScaleTransition beat1 = new ScaleTransition(Duration.millis(280), button);
            beat1.setToX(1.15);
            beat1.setToY(1.15);

            ScaleTransition relax1 = new ScaleTransition(Duration.millis(280), button);
            relax1.setToX(1.0);
            relax1.setToY(1.0);

            ScaleTransition beat2 = new ScaleTransition(Duration.millis(280), button);
            beat2.setToX(1.15);
            beat2.setToY(1.15);

            ScaleTransition relax2 = new ScaleTransition(Duration.millis(560), button);
            relax2.setToX(1.0);
            relax2.setToY(1.0);

            SequentialTransition heartbeat = new SequentialTransition(
                    beat1, relax1, beat2, relax2
            );
            heartbeat.setCycleCount(Animation.INDEFINITE);
            heartbeat.play();
        }
    //Select white top

    @FXML
    private void selectWhiteTop(MouseEvent event) {
        try {
            Image topImage = new Image(getClass().getResourceAsStream("/images/DisplayTop1.PNG"));
            modelTop.setImage(topImage);
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
            System.out.println("Black skirt selected");
        } catch (Exception e) {
            System.out.println("Error loading black skirt: " + e.getMessage());
        }
    }

    //shoes
    @FXML
    private void selectBlackHeels(MouseEvent event) {
        try {
            Image shoesImage = new Image(getClass().getResourceAsStream("/images/Shoes1-pic.png"));
            modelShoes.setImage(shoesImage);
            System.out.println("Black heels selected");
        } catch (Exception e) {
            System.out.println("Error loading shoes: " + e.getMessage());
        }
    }


    //Dress
    @FXML
    private void selectWhiteDress(MouseEvent event) {
        try {
            Image dressImage = new Image(getClass().getResourceAsStream("/images/Dress1-picFitted.png"));
            modelDress.setImage(dressImage);
            modelTop.setImage(null);
            modelBottom.setImage(null); // Clear bottom since dress covers it
            System.out.println("Pink dress selected");
        } catch (Exception e) {
            System.out.println("Error loading dress: " + e.getMessage());
        }
    }
    //hat
    @FXML
    private void selectHat(MouseEvent event) {
        try {
            Image hatImage = new Image(getClass().getResourceAsStream("/images/Hat1-pic.png"));
            modelAccessory1.setImage(hatImage);
            System.out.println("Hat selected");
        } catch (Exception e) {
            System.out.println("Error loading hat: " + e.getMessage());
        }
    }
    //bag selection
    @FXML
    private void selectBag(MouseEvent event) {
        try {
            Image bagImage = new Image(getClass().getResourceAsStream("/images/Bag1-pic.png"));
            modelAccessory2.setImage(bagImage);
            System.out.println("Bag selected");
        } catch (Exception e) {
            System.out.println("Error loading bag: " + e.getMessage());
        }
    }

    //jewelry
    @FXML
    private void selectJewelry(MouseEvent event) {
        try {
            Image jewelryImage = new Image(getClass().getResourceAsStream("/images/Jewelry1.PNG"));
            modelAccessory3.setImage(jewelryImage);
            System.out.println("Jewelry selected");
        } catch (Exception e) {
            System.out.println("Error loading jewelry: " + e.getMessage());
        }
    }

    //sunglasses
    @FXML
    private void selectSunglasses(MouseEvent event) {
        try {
            Image sunglassesImage = new Image(getClass().getResourceAsStream("/images/Glasses1-pic.png"));
            modelAccessory4.setImage(sunglassesImage);
            System.out.println("Sunglasses selected");
        } catch (Exception e) {
            System.out.println("Error loading sunglasses: " + e.getMessage());
        }
    }
    //To choose a model and change
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
    //To reset the model clothing
    @FXML
    public void handleReset() {
        modelTop.setImage(null);
        modelBottom.setImage(null);
        modelShoes.setImage(null);
        modelDress.setImage(null);
        modelAccessory1.setImage(null);
        modelAccessory2.setImage(null);
        modelAccessory3.setImage(null);
        modelAccessory4.setImage(null);
        System.out.println("Outfit reset - all clothing cleared!");
    }

    //Save the outfit in firebase
    @FXML
    private void handleSave(ActionEvent event) {
        try {
            String uid = FirebaseAuthService.getCurrentUserUid();

            if (uid == null) {
                System.out.println("ERROR: No user logged in.");
                return;
            }

            // Build item list based on what's selected
            List<String> selectedItems = new ArrayList<>();

            if (modelTop.getImage() != null) selectedItems.add("top");
            if (modelBottom.getImage() != null) selectedItems.add("bottom");
            if (modelShoes.getImage() != null) selectedItems.add("shoes");
            if (modelDress.getImage() != null) selectedItems.add("dress");
            if (modelAccessory1.getImage() != null) selectedItems.add("accessory1");
            if (modelAccessory2.getImage() != null) selectedItems.add("accessory2");
            if (modelAccessory3.getImage() != null) selectedItems.add("accessory3");
            if (modelAccessory4.getImage() != null) selectedItems.add("accessory4");

            // Create Outfit object
            Outfit outfit = new Outfit(uid, selectedItems);

            // Save to Firestore
            outfitService.saveOutfit(outfit);

            System.out.println("Outfit saved for user: " + uid);
            System.out.println("Items: " + selectedItems);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to save outfit.");
        }

    }

    //To go back to the homepage scene
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

            System.out.println("Returned to Homepage!");
        } catch (IOException e) {
            System.err.println("Error loading homepage: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //Finish button - to go to the end of the game
    @FXML
    private void handleFinish(ActionEvent event) {
        try{
            System.out.println("Finish!!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/GameEnd.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        }catch (Exception e){
            System.out.println("Error loading main.fxml: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Finished Outfit!");
    }
}
