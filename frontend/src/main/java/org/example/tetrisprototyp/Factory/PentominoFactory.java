package org.example.tetrisprototyp.Factory;

import org.example.tetrisprototyp.Composite.Pentomino;
import org.example.tetrisprototyp.Composite.Polyomino;
import org.example.tetrisprototyp.Composite.Tetromino;

import java.util.Random;

public class PentominoFactory implements PolyominoFactory {

    private final Random random = new Random();

    @Override
    public Polyomino createRandomPolyomino() {
        String[] types = {"I", "L", "P", "LR"};
        String type = types[random.nextInt(types.length)];


        return createSpecificPolyomino(type);
    }

    @Override
    public Polyomino createSpecificPolyomino(String type) {
        return new Pentomino(type);
    }

}
