package com.stylz.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class InstructionsController {

    @FXML
    private void handleGoBack(MouseEvent event) {
        try {
            // Go back to Homepage
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stylz/app/Homepage.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
