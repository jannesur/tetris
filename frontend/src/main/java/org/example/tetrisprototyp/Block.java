package org.example.tetrisprototyp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Block implements Shape {

    private int x; // Position in Grid-Koordinaten
    private int y;
    private final Color color;

    public Block(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void render(GraphicsContext gc, int tileSize) {
        gc.setFill(color);
        gc.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(x * tileSize, y * tileSize, tileSize, tileSize);
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    // Getter für Kollisionen oder Logik
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public Color getColor() { return color; }

}
