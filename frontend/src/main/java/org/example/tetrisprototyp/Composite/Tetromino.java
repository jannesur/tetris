package org.example.tetrisprototyp.Composite;

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
            case "LR" -> createLReversed();
            case "SR" -> createSReversed();
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


    private void createLReversed() { // Umgedrehtes-L
        addBlock(new Block(5, 0, Color.ORANGE));
        addBlock(new Block(3, 1, Color.ORANGE));
        addBlock(new Block(4, 1, Color.ORANGE));
        addBlock(new Block(5, 1, Color.ORANGE));
        setPivot(getBlocks().get(3));
    }

    private void createS() {
        addBlock(new Block(4, 0, Color.GREEN));
        addBlock(new Block(5, 0, Color.GREEN));
        addBlock(new Block(3, 1, Color.GREEN));
        addBlock(new Block(4, 1, Color.GREEN));
        setPivot(getBlocks().get(3));
    }

    private void createSReversed() { // Umgedrehtes-S
        addBlock(new Block(3, 0, Color.GREEN));
        addBlock(new Block(4, 0, Color.GREEN));
        addBlock(new Block(4, 1, Color.GREEN));
        addBlock(new Block(5, 1, Color.GREEN));
        setPivot(getBlocks().get(2));
    }

}
