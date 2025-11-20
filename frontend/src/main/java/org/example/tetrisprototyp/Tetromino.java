package org.example.tetrisprototyp;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;


public class Tetromino extends Polyomino {

    public Tetromino(String type) {
        setType(type.toUpperCase());
        createShape();
    }

    private void createShape() {
        switch (getType()) {
            case "I" -> createI();
            case "O" -> createO();
            case "T" -> createT();
            case "L" -> createL();
            case "S" -> createS();
            default -> throw new IllegalArgumentException("Unbekannter Tetromino-Typ: " + getType());
        }
    }

    private void createI() {
        addBlock(new Block(4, 0, Color.CYAN));
        addBlock(new Block(5, 0, Color.CYAN));
        addBlock(new Block(6, 0, Color.CYAN));
        addBlock(new Block(7, 0, Color.CYAN));
        setPivot(getBlocks().get(1));
    }

    private void createO() {
        addBlock(new Block(4, 0, Color.YELLOW));
        addBlock(new Block(5, 0, Color.YELLOW));
        addBlock(new Block(4, 1, Color.YELLOW));
        addBlock(new Block(5, 1, Color.YELLOW));
        setPivot(getBlocks().get(0));
    }

    private void createT() {
        addBlock(new Block(4, 0, Color.PURPLE));
        addBlock(new Block(3, 1, Color.PURPLE));
        addBlock(new Block(4, 1, Color.PURPLE));
        addBlock(new Block(5, 1, Color.PURPLE));
        setPivot(getBlocks().get(2));
    }

    private void createL() {
        addBlock(new Block(3, 0, Color.ORANGE));
        addBlock(new Block(3, 1, Color.ORANGE));
        addBlock(new Block(4, 1, Color.ORANGE));
        addBlock(new Block(5, 1, Color.ORANGE));
        setPivot(getBlocks().get(2));
    }

    private void createS() {
        addBlock(new Block(4, 0, Color.GREEN));
        addBlock(new Block(5, 0, Color.GREEN));
        addBlock(new Block(3, 1, Color.GREEN));
        addBlock(new Block(4, 1, Color.GREEN));
        setPivot(getBlocks().get(3));
    }
    /*
    private final int blockCount = 4;
    private List<Block> blocks = new ArrayList<>(blockCount);
    private Block pivot;



    public void addBlock(Block block) {
        blocks.add(block);
        // Wenn pivot nicht gesetzt -> erster Block als Standard-Pivot
        if (pivot == null && block instanceof Block b) {
            pivot = b;
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


     */

}
