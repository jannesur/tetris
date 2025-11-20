package org.example.tetrisprototyp;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public abstract class Polyomino implements Shape {

    //private int blockCount;
    private List<Block> blocks =  new ArrayList<>();
    private String type;
    private Block pivot;

    public void addBlock(Block block) {
        blocks.add(block);
        // Wenn pivot nicht gesetzt -> erster Block als Standard-Pivot
        if (pivot == null && block instanceof Block) {
            pivot = (Block) block;
        }
    }

    @Override
    public void render(GraphicsContext gc, int tileSize) {
        for (Shape block : blocks) {
            block.render(gc, tileSize);
        }
    }

    @Override
    public void move(int dx, int dy) {
        for (Shape block : blocks) {
            block.move(dx, dy);
        }
    }

    public void rotateClockwise() {
        if (pivot == null) return;

        for (Block block : blocks) {
            if (block == pivot) continue;

            // Relative Position des Blocks zum Pivot berechnen
            int relX = block.getX() - pivot.getX();
            int relY = block.getY() - pivot.getY();

            // Rotation anwenden
            int newRelX = -relY;
            //int newRelY = relX;

            // Neue Position berechnen
            block.setX(pivot.getX() + newRelX);
            block.setY(pivot.getY() + relX);
        }
    }

    public void rotateCounterClockwise() {
        if (pivot == null) return;

        for (Block block : blocks) {
            if (block == pivot) continue;

            // Relative Position des Blocks zum Pivot berechnen
            int relX = block.getX() - pivot.getX();
            int relY = block.getY() - pivot.getY();

            // Rotation anwenden
            //int newRelX = relY;
            int newRelY = -relX;

            block.setX(pivot.getX() + relY);
            block.setY(pivot.getY() + newRelY);
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setPivot(Block pivot) {
        this.pivot = pivot;
    }

    public Block getPivot() {
        return pivot;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
