package org.example.tetrisprototyp;

import javafx.scene.paint.Color;

import java.util.Random;

// Die konkrete Fabrik zur Erstellung von Tetrominos. Die aus der abstrakten Fabrik überschriebene Methode erstellt mithilfe
// der Methoden dieser Klasse ein zufälliges Tetromino.
public class TetrominoFactory implements PolyominoFactory {

    private final Random random = new Random();

    @Override
    public Polyomino createRandomPolyomino() {
        String[] types = {"I", "O", "T", "L", "S"};
        return createSpecificPolyomino(types[random.nextInt(types.length)]);
    }

    @Override
    public Polyomino createSpecificPolyomino(String type) {
        return new Tetromino(type);
    }


    /*
    @Override
    public Polyomino createSpecificPolyomino(String type) {
        return switch (type.toUpperCase()) {
            case "I" -> createI();
            case "O" -> createO();
            case "T" -> createT();
            case "L" -> createL();
            case "S" -> createS();
            default -> throw new IllegalArgumentException("Unbekannter Tetromino-Typ: " + type);
        };
    }


    private Polyomino createI() {

        Polyomino t = new Tetromino();
        t.setType("I");
        t.addBlock(new Block(4,0, Color.CYAN));
        t.addBlock(new Block(5,0,Color.CYAN));
        t.addBlock(new Block(6,0,Color.CYAN));
        t.addBlock(new Block(7,0,Color.CYAN));
        t.setPivot(t.getBlocks().get(1));
        return t;
    }

    private Polyomino createO() {
        Tetromino t = new Tetromino();
        t.setType("O");
        t.addBlock(new Block(4, 0, Color.YELLOW));
        t.addBlock(new Block(5, 0, Color.YELLOW));
        t.addBlock(new Block(4, 1, Color.YELLOW));
        t.addBlock(new Block(5, 1, Color.YELLOW));
        t.setPivot(t.getBlocks().getFirst());
        return t;
    }

    private Polyomino createT() {
        Tetromino t = new Tetromino();
        t.setType("T");
        t.addBlock(new Block(4, 0, Color.PURPLE));
        t.addBlock(new Block(3, 1, Color.PURPLE));
        t.addBlock(new Block(4, 1, Color.PURPLE));
        t.addBlock(new Block(5, 1, Color.PURPLE));
        t.setPivot(t.getBlocks().get(2));
        return t;
    }

    private Polyomino createL() {
        Tetromino t = new Tetromino();
        t.setType("L");
        t.addBlock(new Block(3, 0, Color.ORANGE));
        t.addBlock(new Block(3, 1, Color.ORANGE));
        t.addBlock(new Block(4, 1, Color.ORANGE));
        t.addBlock(new Block(5, 1, Color.ORANGE));
        t.setPivot(t.getBlocks().get(2));
        return t;
    }

    private Polyomino createS() {
        Tetromino t = new Tetromino();
        t.setType("S");
        t.addBlock(new Block(4, 0, Color.GREEN));
        t.addBlock(new Block(5, 0, Color.GREEN));
        t.addBlock(new Block(3, 1, Color.GREEN));
        t.addBlock(new Block(4, 1, Color.GREEN));
        t.setPivot(t.getBlocks().get(3));
        return t;
    }


     */



}
