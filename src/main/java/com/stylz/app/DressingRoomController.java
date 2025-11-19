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
import javafx.stage.Stage;
import java.io.IOException;

public class DressingRoomController {

    // Model display layers
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

    //Initialize method
    @FXML
    private void initialize() {
        System.out.println("Dressing Room loaded successfully!");
    }

    //Select white top

    @FXML
    private void selectWhiteTop(MouseEvent event) {
        try {
            Image topImage = new Image(getClass().getResourceAsStream("/images/top-image.png"));
            modelTop.setImage(topImage);
            System.out.println("White top selected");
        } catch (Exception e) {
            System.out.println("Error loading white top: " + e.getMessage());
        }
    }


    @FXML
    private void selectPinkShirt(MouseEvent event) {
        try {
            Image shirtImage = new Image(getClass().getResourceAsStream("/images/pink-shirt.png"));
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
            Image shortsImage = new Image(getClass().getResourceAsStream("/images/bottom-image.png"));
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
            Image shoesImage = new Image(getClass().getResourceAsStream("/images/shoes-icon.png"));
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
            Image dressImage = new Image(getClass().getResourceAsStream("/images/dresses-image.png"));
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
            Image hatImage = new Image(getClass().getResourceAsStream("/images/hat-image.png"));
            modelAccessory1.setImage(hatImage);
            System.out.println("Hat selected");
        } catch (Exception e) {
            System.out.println("Error loading hat: " + e.getMessage());
        }
    }

    @FXML
    private void selectBag(MouseEvent event) {
        try {
            Image bagImage = new Image(getClass().getResourceAsStream("/images/bag-image.png"));
            modelAccessory2.setImage(bagImage);
            System.out.println("Bag selected");
        } catch (Exception e) {
            System.out.println("Error loading bag: " + e.getMessage());
        }
    }


    @FXML
    private void selectJewelry(MouseEvent event) {
        try {
            Image jewelryImage = new Image(getClass().getResourceAsStream("/images/jewelry-image.png"));
            modelAccessory2.setImage(jewelryImage);
            System.out.println("Jewelry selected");
        } catch (Exception e) {
            System.out.println("Error loading jewelry: " + e.getMessage());
        }
    }


    @FXML
    private void selectSunglasses(MouseEvent event) {
        try {
            Image sunglassesImage = new Image(getClass().getResourceAsStream("/images/sunglasses.png"));
            modelAccessory1.setImage(sunglassesImage);
            System.out.println("Sunglasses selected");
        } catch (Exception e) {
            System.out.println("Error loading sunglasses: " + e.getMessage());
        }
    }

    @FXML
    private void handleReset(ActionEvent event) {
        modelTop.setImage(null);
        modelBottom.setImage(null);
        modelShoes.setImage(null);
        modelAccessory1.setImage(null);
        modelAccessory2.setImage(null);
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

    }

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
    @FXML
    private void handleGoBack(ActionEvent event) {
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