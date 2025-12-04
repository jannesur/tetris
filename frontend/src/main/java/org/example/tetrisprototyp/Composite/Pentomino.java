package org.example.tetrisprototyp.Composite;

import javafx.scene.paint.Color;

public class Pentomino extends Polyomino {

    public Pentomino(String type) {
        setType(type.toUpperCase());
        createShape();
    }

    private void createShape() {
        switch (getType()) {
            case "I" -> createI();
            case "L" -> createL();
            case "P" -> createP();
            case "LR" -> createLReversed();
            default -> throw new IllegalArgumentException("Unbekannter Tetromino-Typ: " + getType());
        }
    }

    private void createI() {
        addBlock(new Block(3, 0, Color.CYAN));
        addBlock(new Block(4, 0, Color.CYAN));
        addBlock(new Block(5, 0, Color.CYAN));
        addBlock(new Block(6, 0, Color.CYAN));
        addBlock(new Block(7, 0, Color.CYAN));
        setPivot(getBlocks().get(2));
    }

    private void createL() {
        addBlock(new Block(4, 0, Color.ORANGE));
        addBlock(new Block(4, 1, Color.ORANGE));
        addBlock(new Block(4, 2, Color.ORANGE));
        addBlock(new Block(5, 2, Color.ORANGE));
        addBlock(new Block(6, 2, Color.ORANGE));
        setPivot(getBlocks().get(2));
    }


    private void createLReversed() {
        addBlock(new Block(6, 0, Color.BLUE));
        addBlock(new Block(6, 1, Color.BLUE));
        addBlock(new Block(6, 2, Color.BLUE));
        addBlock(new Block(5, 2, Color.BLUE));
        addBlock(new Block(4, 2, Color.BLUE));

        // Pivot: oberer Block der vertikalen Linie
        setPivot(getBlocks().get(0));
    }

    private void createP() {
        addBlock(new Block(4, 0, Color.GREEN));
        addBlock(new Block(5, 0, Color.GREEN));
        addBlock(new Block(4, 1, Color.GREEN));
        addBlock(new Block(5, 1, Color.GREEN));
        addBlock(new Block(4, 2, Color.GREEN));
        setPivot(getBlocks().get(2));
    }




}
