package org.example.tetrisprototyp.Composite;

import javafx.scene.paint.Color;

public class Tromino extends Polyomino {

    public Tromino(String type) {
        setType(type.toUpperCase());
        createShape();
    }

    private void createShape() {
        switch (getType()) {
            case "I" -> createI();
            case "L" -> createL();
            case "LR" -> createLReversed();
            default -> throw new IllegalArgumentException("Unbekannter Tromino-Typ: " + getType());
        }
    }

    private void createI() {
        addBlock(new Block(4, 0, Color.CYAN));
        addBlock(new Block(5, 0, Color.CYAN));
        addBlock(new Block(6, 0, Color.CYAN));

        // Pivot: Mitte
        setPivot(getBlocks().get(1));
    }

    private void createL() {
        addBlock(new Block(4, 0, Color.ORANGE));
        addBlock(new Block(5, 0, Color.ORANGE));
        addBlock(new Block(4, 1, Color.ORANGE));

        // Pivot: oberer linker Block
        setPivot(getBlocks().get(0));
    }

    private void createLReversed() {
        addBlock(new Block(5, 0, Color.ORANGE));
        addBlock(new Block(4, 0, Color.ORANGE));
        addBlock(new Block(5, 1, Color.ORANGE));

        // Pivot: oben rechts
        setPivot(getBlocks().get(0));
    }

}
