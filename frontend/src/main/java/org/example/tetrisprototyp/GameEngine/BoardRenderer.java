package org.example.tetrisprototyp.GameEngine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import org.example.tetrisprototyp.Composite.Block;
import org.example.tetrisprototyp.Composite.Polyomino;
import org.example.tetrisprototyp.Composite.TetrisColors;

import java.util.List;

public class BoardRenderer {

    private final GraphicsContext gc;
    private final int tileSize;
    private final int width;
    private final int height;

    public BoardRenderer(GraphicsContext gc, int tileSize, int width, int height) {
        this.gc = gc;
        this.tileSize = tileSize;
        this.width = width;
        this.height = height;
    }



    public void render(List<Block> settledBlocks, Polyomino current) {
        double w = width * tileSize;
        double h = height * tileSize;

        // 1. Schöner Hintergrund (dunkelblau mit leichtem Verlauf)
        gc.setFill(Color.web("#0a001f"));
        gc.fillRect(0, 0, w, h);

        // Optional: dezenter Sternenhimmel oder Noise (kann man weglassen)
        // ...

        // 2. Subtile Grid-Linien
        gc.setStroke(Color.web("#ffffff0a"));
        gc.setLineWidth(1);
        for (int x = 0; x <= width; x++) {
            gc.strokeLine(x * tileSize, 0, x * tileSize, h);
        }
        for (int y = 0; y <= height; y++) {
            gc.strokeLine(0, y * tileSize, w, y * tileSize);
        }

        // 4. Settled Blocks
        for (Block b : settledBlocks) {
            b.render(gc, tileSize);
        }

        // 5. Aktuelles fallendes Teil (obendrauf!)
        if (current != null) {
            current.render(gc, tileSize);
        }

        // 6. Rahmen um das gesamte Board (Neon-Glow)
        gc.setStroke(Color.CYAN);
        gc.setLineWidth(6);
        gc.setEffect(new Glow(0.9));
        gc.strokeRoundRect(0, 0, w, h, 20, 20);
        gc.setEffect(null);
    }

}
