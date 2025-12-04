package org.example.tetrisprototyp.Factory;

import org.example.tetrisprototyp.Composite.Polyomino;
import org.example.tetrisprototyp.Composite.Tetromino;
import org.example.tetrisprototyp.Composite.Tromino;

import java.util.Random;

public class TrominoFactory implements PolyominoFactory{

    private final Random random = new Random();

    @Override
    public Polyomino createRandomPolyomino() {
        String[] types = {"I", "L"};
        String type = types[random.nextInt(types.length)];

        // Für L zufällig die Reversed-Variante wählen
        if (type.equals("L") && random.nextBoolean()) {
            type = "LR";
        }

        return createSpecificPolyomino(type);
    }

    @Override
    public Polyomino createSpecificPolyomino(String type) {
        return new Tromino(type);
    }
}
