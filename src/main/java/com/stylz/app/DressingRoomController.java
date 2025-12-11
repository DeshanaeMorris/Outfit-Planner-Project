package com.stylz.app;
import com.google.cloud.Timestamp;
import com.stylz.app.Firebase.FirebaseAuthService;
import com.stylz.app.Firebase.FirebaseStorageService;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DressingRoomController {

    // Firebase outfit service
    private final OutfitService outfitService = new OutfitService();

    // Outfit passed in from Catalog
    public static Outfit outfitToLoad;

    // Model display layers
    @FXML
    private AnchorPane rootPane;
    @FXML
    private StackPane avatarPane;
    @FXML
    private ImageView modelTop;
    @FXML
    private ImageView modelBottom;
    @FXML
    private ImageView modelShoes;
    // For hat/sunglasses
    @FXML
    private ImageView modelDress;
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

    // Apply an item by its key
    private void applyItem(String itemId) {
        try {
            switch (itemId) {

                // TOPS
                case "white_top" -> {
                    Image img = new Image(getClass().getResourceAsStream("/images/DisplayTop1.PNG"));
                    modelTop.setImage(img);
                    selectedTop = "white_top";
                }
                case "pink_shirt" -> {
                    Image img = new Image(getClass().getResourceAsStream("/images/Top2-pic.png"));
                    modelTop.setImage(img);
                    selectedTop = "pink_shirt";
                }

                // BOTTOMS
                case "blue_shorts" -> {
                    Image img = new Image(getClass().getResourceAsStream("/images/Bottom1-pic.png"));
                    modelBottom.setImage(img);
                    selectedBottom = "blue_shorts";
                }
                case "black_skirt" -> {
                    Image img = new Image(getClass().getResourceAsStream("/images/black-skirt.png"));
                    modelBottom.setImage(img);
                    selectedBottom = "black_skirt";
                }

                // DRESS
                case "white_dress" -> {
                    Image img = new Image(getClass().getResourceAsStream("/images/Dress1-pic.png"));
                    modelTop.setImage(img);
                    modelBottom.setImage(null);
                    selectedTop = "white_dress";
                    selectedBottom = null;
                }

                // SHOES
                case "black_heels" -> {
                    Image img = new Image(getClass().getResourceAsStream("/images/Shoes1-pic.png"));
                    modelShoes.setImage(img);
                    selectedShoes = "black_heels";
                }

                // ACCESSORIES
                case "hat_1" -> {
                    Image img = new Image(getClass().getResourceAsStream("/images/Hat1-pic.png"));
                    modelAccessory1.setImage(img);
                    selectedAccessory1 = "hat_1";
                }
                case "bag_1" -> {
                    Image img = new Image(getClass().getResourceAsStream("/images/Bag1-pic.png"));
                    modelAccessory2.setImage(img);
                    selectedAccessory2 = "bag_1";
                }
                case "jewelry_1" -> {
                    Image img = new Image(getClass().getResourceAsStream("/images/Jewelry1.PNG"));
                    modelAccessory3.setImage(img);
                    selectedAccessory3 = "jewelry_1";
                }
                case "sunglasses_1" -> {
                    Image img = new Image(getClass().getResourceAsStream("/images/Glasses1-pic.png"));
                    modelAccessory4.setImage(img);
                    selectedAccessory4 = "sunglasses_1";
                }

                default -> System.out.println("Unknown itemId: " + itemId);
            }
        } catch (Exception e) {
            System.out.println("Error applying item " + itemId + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        String basePath = ModelSelectionController.selectedModelPath;
        if (outfitToLoad != null && outfitToLoad.getModelPath() != null) {
            basePath = outfitToLoad.getModelPath();
        }

        try {
            // Load selected model from ModelSelectionController
            Image selected = new Image(
                    getClass().getResourceAsStream(ModelSelectionController.selectedModelPath)
            );
            modelBase.setImage(selected);
            System.out.println("Loaded selected model: " + basePath);
        } catch (Exception e) {
            System.out.println("Error loading selected model: " + e.getMessage());
        }
        updateVisibilityBasedOnPurchases();
        // If coming from Catalog, apply saved outfit using keys
        if (outfitToLoad != null) {
            System.out.println("Applying outfit from Catalog (id = " + outfitToLoad.getId() + ")");

            if (outfitToLoad.getTopKey() != null) {
                applyItem(outfitToLoad.getTopKey());
            }
            if (outfitToLoad.getBottomKey() != null) {
                applyItem(outfitToLoad.getBottomKey());
            }
            if (outfitToLoad.getShoesKey() != null) {
                applyItem(outfitToLoad.getShoesKey());
            }
            if (outfitToLoad.getAccessory1Key() != null) {
                applyItem(outfitToLoad.getAccessory1Key());
            }
            if (outfitToLoad.getAccessory2Key() != null) {
                applyItem(outfitToLoad.getAccessory2Key());
            }
            if (outfitToLoad.getAccessory3Key() != null) {
                applyItem(outfitToLoad.getAccessory3Key());
            }
            if (outfitToLoad.getAccessory4Key() != null) {
                applyItem(outfitToLoad.getAccessory4Key());
            }

            // Clear so it's not reused accidentally
            outfitToLoad = null;
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
            modelBottom.setImage(null);
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
        modelDress.setImage(null);
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

        //Don't save if model has nothing on
        boolean hasAnyClothing =
                selectedTop != null ||
                        selectedBottom != null ||
                        selectedShoes != null ||
                        selectedAccessory1 != null ||
                        selectedAccessory2 != null ||
                        selectedAccessory3 != null ||
                        selectedAccessory4 != null;

        String userId = FirebaseAuthService.getCurrentUserUid();

        WritableImage snapshot = null;
        try {
            SnapshotParameters params = new SnapshotParameters();
            snapshot = avatarPane.snapshot(params, null);
            GameEndController.setLastOutfitImage(snapshot);
            System.out.println("Snapshot of outfit captured");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!hasAnyClothing) {
            System.out.println("No clothing selected - outfit will not be saved to Firestore.");
        } else if (userId == null) {
            System.out.println("USER IS NOT LOGGED IN - cannot save outfit to Firestore.");
        } else {
            String snapshotUrl = null;
            if (snapshot != null) {
                try {
                    snapshotUrl = FirebaseStorageService.uploadOutfitSnapshot(snapshot, userId);
                    System.out.println("Snapshot uploaded. URL = " + snapshotUrl);
                } catch (Exception e) {
                    System.out.println("Failed to upload snapshot: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            String modelPath = ModelSelectionController.selectedModelPath;

            Outfit outfit = new Outfit(
                    userId,
                    modelPath,
                    selectedTop,
                    selectedBottom,
                    selectedShoes,
                    selectedAccessory1,
                    selectedAccessory2,
                    selectedAccessory3,
                    selectedAccessory4,
                    Timestamp.now(),
                    snapshotUrl
            );

            try {
                outfitService.saveOutfit(outfit);
                System.out.println("Outfit successfully saved to Firestore!");
            } catch (Exception e) {
                System.out.println("Firestore save failed:");
                e.printStackTrace();
            }
        }

        // Navigate to GameEnd
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/GameEnd.fxml"));
            Parent endRoot = loader.load();

            GameEndController gameEnd = loader.getController();
            gameEnd.setOutfitImages(
                    modelTop.getImage(),
                    modelBottom.getImage(),
                    modelShoes.getImage(),
                    modelDress.getImage(),
                    modelAccessory1.getImage(),
                    modelAccessory2.getImage(),
                    modelAccessory3.getImage(),
                    modelAccessory4.getImage()
            );

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene endScene = new Scene(endRoot);

            stage.setScene(endScene);
            stage.show();

            System.out.println("Navigated to Game End page.");

        } catch (Exception e) {
            System.out.println("Error loading Game End page: " + e.getMessage());
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

            System.out.println("Successfully navigated back to Homepage!");
        } catch (IOException e) {
            System.err.println("Error loading homepage: " + e.getMessage());
            e.printStackTrace();

        }
    }

        private void updateVisibilityBasedOnPurchases () {
            List<String> items = com.stylz.app.LocalInventory.purchased;

            modelTop.setVisible(items.contains("White Top"));
            modelBottom.setVisible(items.contains("Blue Shorts"));
            modelShoes.setVisible(items.contains("Black Heels"));
            modelDress.setVisible(items.contains("White Dress"));
            modelAccessory1.setVisible(items.contains("Hat"));
            modelAccessory2.setVisible(items.contains("Bag"));
            modelAccessory3.setVisible(items.contains("Jewelry"));
            modelAccessory4.setVisible(items.contains("Sunglasses"));
        }
}
