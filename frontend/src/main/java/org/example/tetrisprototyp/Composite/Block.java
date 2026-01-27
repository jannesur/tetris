package org.example.tetrisprototyp.Composite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;

// Grundbaustein jedes Polyomino
public class Block implements Shape {

    private int x;
    private int y;
    private final Color color;

    public Block(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void render(GraphicsContext gc, int tileSize) {

        double x = this.x * tileSize;
        double y = this.y * tileSize;
        double s = tileSize;

        // 1. Hauptblock mit Neon-Farbe
        gc.setFill(color);
        gc.fillRoundRect(x + 2, y + 2, s - 4, s - 4, 12, 12);

        // 2. Glanz-Highlight (weißer Verlauf oben-links)
        gc.setFill(Color.web("#ffffff60"));
        gc.fillRoundRect(x + 4, y + 4, s - 12, s - 12, 8, 8);

        // 3. Glanz-Reflex (kleiner weißer Punkt)
        gc.setFill(Color.WHITE);
        gc.fillOval(x + 8, y + 8, 8, 8);

        // 4. Schwarzer Innenrand für Tiefe
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRoundRect(x + 3, y + 3, s - 6, s - 6, 10, 10);

        // 5. Leichter Glow außen
        gc.setEffect(new Glow(0.8));
        gc.setStroke(color.brighter());
        gc.setLineWidth(4);
        gc.strokeRoundRect(x, y, s, s, 16, 16);
        gc.setEffect(null); // wichtig!
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
