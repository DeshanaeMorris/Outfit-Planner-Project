package com.stylz.app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;

public class ChangeModelPopupController {
    @FXML private AnchorPane popupRoot;
    @FXML private Button yesBtn;
    @FXML private Button cancelBtn;

    private DressingRoomController parent;

    // Called by parent to give reference back
    public void setParent(DressingRoomController controller) {
        this.parent = controller;
    }

    @FXML
    private void initialize() {

        // Cancel closes popup ONLY
        cancelBtn.setOnAction(event -> {
            ((AnchorPane) popupRoot.getParent()).getChildren().remove(popupRoot);
        });

        // YES resets the outfit + returns to model selection
        yesBtn.setOnAction(event -> {
            if (parent != null) parent.handleReset();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/ModelSelection.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) popupRoot.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        });
    }
}
