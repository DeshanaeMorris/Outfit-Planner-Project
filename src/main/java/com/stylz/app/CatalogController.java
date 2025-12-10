package com.stylz.app;

import com.stylz.app.Firebase.FirebaseAuthService;
import com.stylz.app.Firebase.OutfitService;
import com.stylz.app.model.Outfit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;


public class CatalogController {

    private final OutfitService outfitService = new OutfitService();

    //3 polaroid VBoxes from FXML
    @FXML
    private VBox purpleCard;
    @FXML
    private VBox greenCard;
    @FXML
    private VBox yellowCard;

    @FXML
    public void initialize() {
        System.out.println("Catalog initialized...");

        String userId = FirebaseAuthService.getCurrentUserUid();
        if (userId == null) {
            System.out.println("No logged-in user");
            return;
        }

        try {
            List<Outfit> outfits = outfitService.getLastOutfitsForUser(userId, 3);
            System.out.println("Loaded " + outfits.size() + " outfits for user " + userId);
            bindOutfitsToPolaroids(outfits);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Failed to load outfits: " + e.getMessage());
        }
    }

    private void bindOutfitsToPolaroids(List<Outfit> outfits) {
        VBox[] cards = { purpleCard, greenCard, yellowCard };

        for (int i = 0; i < cards.length; i++) {
            VBox card = cards[i];
            if (card == null) continue;

            card.getChildren().clear();

            if (i < outfits.size()) {
                Outfit outfit = outfits.get(i);

                card.setVisible(true);
                card.setManaged(true);

                card.setUserData(outfit);

                StackPane outfitView = createOutfitStack(outfit);
                card.getChildren().add(outfitView);

                card.setOnMouseClicked(e -> openOutfit(outfit));

            } else {
                card.setVisible(false);
                card.setManaged(false);
            }
        }
    }

    private String resolveImagePathFromKey(String key) {
        if (key == null || key.isEmpty()) return null;

        return switch (key) {
            // Tops
            case "white_top"   -> "/images/DisplayTop1.PNG";
            case "pink_shirt"  -> "/images/Top2-pic.png";
            case "white_dress" -> "/images/Dress1-pic.png"; // dress acts like a top layer

            // Bottoms
            case "blue_shorts" -> "/images/Bottom1-pic.png";
            case "black_skirt" -> "/images/black-skirt.png";

            // Shoes
            case "black_heels" -> "/images/Shoes1-pic.png";

            // Accessories
            case "hat_1"        -> "/images/Hat1-pic.png";
            case "bag_1"        -> "/images/Bag1-pic.png";
            case "jewelry_1"    -> "/images/Jewelry1.PNG";
            case "sunglasses_1" -> "/images/Glasses1-pic.png";

            default -> {
                System.out.println("No image mapping for key: " + key);
                yield null;
            }
        };
    }

    private StackPane createOutfitStack(Outfit outfit) {

        StackPane stack = new StackPane();
        stack.setPrefWidth(160);
        stack.setPrefHeight(220);
        stack.setMaxWidth(160);
        stack.setMaxHeight(220);

        // ---- SCALE MULTIPLIER ----
        double scale = 0.50;  // shrink everything to 50%

        // BASE MODEL
        ImageView modelBase = createLayerImage(outfit.getModelPath());
        modelBase.setFitWidth(100);
        modelBase.setFitHeight(150);
        modelBase.setPreserveRatio(true);

        // BOTTOM
        ImageView bottom = createLayerImage(resolveImagePathFromKey(outfit.getBottomKey()));
        bottom.setFitWidth(77);
        bottom.setFitHeight(150);
        bottom.setPreserveRatio(true);
        bottom.setTranslateX(4.3);
        bottom.setTranslateY(16);


        // SHOES
        ImageView shoes = createLayerImage(resolveImagePathFromKey(outfit.getShoesKey()));
        shoes.setFitWidth(40);
        shoes.setFitHeight(150);
        shoes.setPreserveRatio(true);
        shoes.setTranslateX(0.9);
        shoes.setTranslateY(51);

        // TOP
        ImageView top = createLayerImage(resolveImagePathFromKey(outfit.getTopKey()));
        top.setFitWidth(29);
        top.setFitHeight(29);
        top.setPreserveRatio(true);
        top.setTranslateX(-0.6);
        top.setTranslateY(-30);

        // ACCESSORY 1
        ImageView acc1 = createLayerImage(resolveImagePathFromKey(outfit.getAccessory1Key()));
        acc1.setFitWidth(200);
        acc1.setFitHeight(200);
        acc1.setPreserveRatio(true);
        acc1.setTranslateX(-47);
        acc1.setTranslateY(-7);

        // ACCESSORY 2
        ImageView acc2 = createLayerImage(resolveImagePathFromKey(outfit.getAccessory2Key()));
        acc2.setFitWidth(208);
        acc2.setFitHeight(208);
        acc2.setPreserveRatio(true);
        acc2.setTranslateX(23);
        acc2.setTranslateY(-2.4);

        // ACCESSORY 3
        ImageView acc3 = createLayerImage(resolveImagePathFromKey(outfit.getAccessory3Key()));
        acc3.setFitWidth(200);
        acc3.setFitHeight(200);
        acc3.setPreserveRatio(true);
        acc3.setTranslateX(21);
        acc3.setTranslateY(-2.3);

        // ACCESSORY 4
        ImageView acc4 = createLayerImage(resolveImagePathFromKey(outfit.getAccessory4Key()));
        acc4.setFitWidth(203);
        acc4.setFitHeight(203);
        acc4.setPreserveRatio(true);
        acc4.setTranslateX(70);
        acc4.setTranslateY(-3.1);

        stack.getChildren().addAll(modelBase, bottom, shoes, top, acc1, acc2, acc3, acc4);
        // Clip to oval shape
        Ellipse clip = new Ellipse(80, 110);
        clip.setCenterX(80);
        clip.setCenterY(110);
        stack.setClip(clip);

        return stack;
    }

    private ImageView createLayerImage(String path) {
        ImageView iv = new ImageView();

        if (path == null || path.isEmpty()) {
            return iv;
        }

        System.out.println("Loading image for path: " + path);

        try {
            iv.setImage(new Image(
                    java.util.Objects.requireNonNull(
                            getClass().getResourceAsStream(path),
                            "Image not found: " + path
                    )
            ));
        } catch (NullPointerException e) {
            System.out.println("Image not found for path: " + path);
        }

        iv.setPreserveRatio(true);
        return iv;
    }

    private void openOutfit(Outfit outfit) {
        try {
            System.out.println("Opening outfit from catalog: " + outfit.getId());
            DressingRoomController.outfitToLoad = outfit;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/DressingRoom.fxml"));
            Parent root = loader.load();

            Stage stage = getCurrentStage();
            if (stage != null) {
                Scene scene = new Scene(root);
                scene.getStylesheets().add(Objects.requireNonNull(
                        getClass().getResource("/styles.css")).toExternalForm());
                stage.setScene(scene);
                stage.setTitle("Dressing Room");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load DressingRoom.fxml: " + e.getMessage());
        }
    }
    @FXML
    private void handleLogout(javafx.event.ActionEvent event){
        System.out.println("Logging Lout from Catalog...");
        navigateTo("Login.fxml", "STYLZ Co. - Login");
    }

    @FXML
    private void handleHome(javafx.event.ActionEvent event){
        System.out.println("Going to Home from Catalog...");
        navigateTo("Homepage.fxml", "STYLZ Co. - Home");
    }

    @FXML
    private void handleGoBack(javafx.scene.input.MouseEvent event){
        System.out.println("Going back from Catalog...");
        navigateTo("Homepage.fxml", "STYLZ Co. - Home");
    }

    //JUnit
    public static String buildFxmlPath(String fxmlFile) {
        return "/com/stylz/app/" + fxmlFile;
    }

    @FXML
    private void navigateTo(String fxmlFile, String title) {
        try {
            String fxmlPath = "/com/stylz/app/" + fxmlFile;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());

            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

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