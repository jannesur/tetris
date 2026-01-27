package org.example.tetrisprototyp.Factory;

import org.example.tetrisprototyp.Composite.Polyomino;
import org.example.tetrisprototyp.Composite.Tetromino;

import java.util.Random;

// Die konkrete Fabrik zur Erstellung von Tetrominos. Die aus der abstrakten Fabrik überschriebene Methode erstellt mithilfe
// der Methoden dieser Klasse ein zufälliges Tetromino.
public class TetrominoFactory implements PolyominoFactory {

    private final Random random = new Random();

    @Override
    public Polyomino createRandomPolyomino() {
        String[] types = {"I", "O", "T", "L", "S", "LR", "SR"};
        String type = types[random.nextInt(types.length)];

        // Für L und S zufällig die Reversed-Variante wählen
        if (type.equals("L") && random.nextBoolean()) {
            type = "LR"; // L-Reversed
        } else if (type.equals("S") && random.nextBoolean()) {
            type = "SR"; // S-Reversed
        }

        return createSpecificPolyomino(type);
    }

    @Override
    public Polyomino createSpecificPolyomino(String type) {
        return new Tetromino(type);
    }


}
