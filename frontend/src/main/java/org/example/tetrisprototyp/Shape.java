package org.example.tetrisprototyp;

import javafx.scene.canvas.GraphicsContext;

public interface Shape {

    void render(GraphicsContext gc, int tileSize); // Zeichnet den Block
    void move(int dx, int dy);

}
