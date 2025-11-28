package org.example.tetrisprototyp.GameEngine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.tetrisprototyp.Composite.Block;
import org.example.tetrisprototyp.Composite.Polyomino;

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

        // Hintergrund
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * tileSize, height * tileSize);

        // Raster
        gc.setStroke(Color.DARKGRAY);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                gc.strokeRect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }

        // Erst die liegenden Blöcke zeichnen
        for (Block b : settledBlocks) {
            b.render(gc, tileSize);
        }

        // Aktuelles Polyomino rendern
        if (current != null) {
            current.render(gc, tileSize);
        }
    }
}