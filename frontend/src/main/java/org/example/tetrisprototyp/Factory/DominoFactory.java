package org.example.tetrisprototyp.Factory;

import org.example.tetrisprototyp.Composite.Domino;
import org.example.tetrisprototyp.Composite.Polyomino;
import org.example.tetrisprototyp.Composite.Tetromino;

public class DominoFactory implements PolyominoFactory {

    @Override
    public Polyomino createRandomPolyomino() {

        String type = "Domino";
        return createSpecificPolyomino(type);
    }

    @Override
    public Polyomino createSpecificPolyomino(String type) {
        return new Domino(type);
    }


}
