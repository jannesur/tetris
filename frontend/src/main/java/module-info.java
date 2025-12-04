module org.example.tetrisprototyp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires jdk.compiler;
    //requires org.example.tetrisprototyp;
    //requires org.example.tetrisprototyp;


    opens org.example.tetrisprototyp to javafx.fxml;
    exports org.example.tetrisprototyp;
    exports org.example.tetrisprototyp.GameEngine;
    opens org.example.tetrisprototyp.GameEngine to javafx.fxml;
    exports org.example.tetrisprototyp.Composite;
    opens org.example.tetrisprototyp.Composite to javafx.fxml;
    exports org.example.tetrisprototyp.Factory;
    opens org.example.tetrisprototyp.Factory to javafx.fxml;
    exports org.example.tetrisprototyp.Menu;
    opens org.example.tetrisprototyp.Menu to javafx.fxml;
}