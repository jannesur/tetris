package org.example.tetrisprototyp.Factory;


import org.example.tetrisprototyp.Composite.Polyomino;

// Die abstrakte Fabrik, welche von den konkreten Fabriken (z.B. TetrominoFactory) implementiert wird.
// Die GameEngine erstellt je nach Schwierigkeitsgrad eine der konkreten Fabriken und erstellt mit ihr zufällige Polyominos.
public interface PolyominoFactory {

    Polyomino createRandomPolyomino();

    Polyomino createSpecificPolyomino(String type);

}
