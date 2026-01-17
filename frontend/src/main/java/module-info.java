module org.example.tetrisprototyp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    requires java.net.http;
    requires java.desktop;
    requires jdk.compiler;

    requires com.fasterxml.jackson.databind;

    // --- JavaFX / FXML ---
    exports org.example.tetrisprototyp;
    opens org.example.tetrisprototyp to javafx.fxml;

    exports org.example.tetrisprototyp.GameEngine;
    opens org.example.tetrisprototyp.GameEngine to javafx.fxml;

    exports org.example.tetrisprototyp.Composite;
    opens org.example.tetrisprototyp.Composite to javafx.fxml;

    exports org.example.tetrisprototyp.Factory;
    opens org.example.tetrisprototyp.Factory to javafx.fxml;

    exports org.example.tetrisprototyp.MenuController;
    opens org.example.tetrisprototyp.MenuController to javafx.fxml;

    // --- Jackson JSON (WICHTIG) ---
    exports org.example.tetrisprototyp.UserManagement;
    opens org.example.tetrisprototyp.UserManagement to com.fasterxml.jackson.databind;

    exports org.example.tetrisprototyp.History;
    opens org.example.tetrisprototyp.History to com.fasterxml.jackson.databind, javafx.fxml;
}
