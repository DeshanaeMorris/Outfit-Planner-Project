module group.stylz {
    requires javafx.controls;
    requires javafx.fxml;


    opens group.stylz to javafx.fxml;
    exports group.stylz;
}