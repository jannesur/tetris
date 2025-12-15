package org.example.tetrisprototyp.GameEngine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import org.example.tetrisprototyp.Composite.Block;
import org.example.tetrisprototyp.Composite.Polyomino;
import org.example.tetrisprototyp.Composite.TetrisColors;

import java.util.List;

// Klasse für das Render des Spielfelds und der Puzzleteile
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
        // Breite und Höhe
        double w = width * tileSize;
        double h = height * tileSize;

        // Hintergrund
        gc.setFill(Color.web("#0a001f"));
        gc.fillRect(0, 0, w, h);


        // Grid-Linien
        gc.setStroke(Color.web("#ffffff0a"));
        gc.setLineWidth(1);
        for (int x = 0; x <= width; x++) {
            gc.strokeLine(x * tileSize, 0, x * tileSize, h);
        }
        for (int y = 0; y <= height; y++) {
            gc.strokeLine(0, y * tileSize, w, y * tileSize);
        }

        // Gesetzte Blöcke
        for (Block b : settledBlocks) {
            b.render(gc, tileSize);
        }

        // Aktuelles Puzzleteil
        if (current != null) {
            current.render(gc, tileSize);
        }

        // Rahmen und das Spielfeld
        gc.setStroke(Color.CYAN);
        gc.setLineWidth(6);
        gc.setEffect(new Glow(0.9));
        gc.strokeRoundRect(0, 0, w, h, 20, 20);
        gc.setEffect(null);
    }

}
