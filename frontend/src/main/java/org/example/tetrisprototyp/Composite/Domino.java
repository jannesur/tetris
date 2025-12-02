package org.example.tetrisprototyp.Composite;


import javafx.scene.paint.Color;

public class Domino extends Polyomino {

    // Type muss angegeben werden, weil in der PolyominoFactory benötigt
    public Domino(String type) {
        setType(type.toUpperCase());
        createDomino(type);
    }

    private void createDomino(String type) {

        if (type.equals("Domino")) {
            // Zwei Blöcke übereinander (vertikales Domino)
            addBlock(new Block(4, 0, Color.WHITE)); // oberer Block
            addBlock(new Block(4, 1, Color.WHITE)); // unterer Block

            setPivot(getBlocks().get(0)); // Pivot für Rotation
        }
    }

}
