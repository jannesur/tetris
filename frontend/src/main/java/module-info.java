module org.example.tetrisprototyp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.example.tetrisprototyp to javafx.fxml;
    exports org.example.tetrisprototyp;
}