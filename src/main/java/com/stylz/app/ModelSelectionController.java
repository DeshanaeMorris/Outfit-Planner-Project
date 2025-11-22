package com.stylz.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModelSelectionController {
    public static String selectedModelPath = "/images/model1.png";  // default

    @FXML
    private void chooseModel1(MouseEvent event) { loadDressingRoom("/images/model1-2.png", event); }

    @FXML
    private void chooseModel2(MouseEvent event) { loadDressingRoom("/images/model2-2.png", event); }

    @FXML
    private void chooseModel3(MouseEvent event) { loadDressingRoom("/images/model3-2.png", event); }

    @FXML
    private void chooseModel4(MouseEvent event) { loadDressingRoom("/images/model4-2.png", event); }


    private void loadDressingRoom(String modelPath, MouseEvent event) {
        try {
            selectedModelPath = modelPath;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("DressingRoom.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Error loading dressing room: " + e.getMessage());
        }
    }
}