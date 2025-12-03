package com.stylz.app;
import com.stylz.app.Firebase.FirestoreDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stylz/app/StylzWelcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Outfit Planner");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() {
        FirestoreDatabase.initializeFirebase();
    }

    public static void main(String[] args) {
        launch();
    }
}