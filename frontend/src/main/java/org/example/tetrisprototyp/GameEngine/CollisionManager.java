package org.example.tetrisprototyp.GameEngine;

import org.example.tetrisprototyp.Composite.Block;
import org.example.tetrisprototyp.Composite.Polyomino;

import java.util.List;

// Klasse für die Kollision
public class CollisionManager {

    private final int width;
    private final int height;

    public CollisionManager(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /** Prüft Kollisionen eines Polyominos mit Spielfeld und gesetzten Blöcken */
    public boolean canMove(Polyomino polyomino, int dx, int dy, List<Block> settledBlocks) {
        for (Block block : polyomino.getBlocks()) {
            int newX = block.getX() + dx;
            int newY = block.getY() + dy;

            // Spielfeldgrenzen
            if (newX < 0 || newX >= width || newY >= height) return false;

            // Kollision mit liegenden Blöcken
            for (Block settled : settledBlocks) {
                if (settled.getX() == newX && settled.getY() == newY) return false;
            }
        }
        return true;
    }
}
