package com.stylz.app;
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


public class DressingRoomController {

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

    //heels
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


    //
    @FXML
    private void selectWhiteDress(MouseEvent event) {
        try {
            Image dressImage = new Image(getClass().getResourceAsStream("/images/Dress1-pic.png"));
            modelTop.setImage(dressImage);
            modelBottom.setImage(null); // Clear bottom since dress covers it
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
