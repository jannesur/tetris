package org.example.tetrisprototyp.GameEngine;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.example.tetrisprototyp.Composite.Block;
import org.example.tetrisprototyp.Composite.Polyomino;
import org.example.tetrisprototyp.Factory.PolyominoFactory;
import org.example.tetrisprototyp.Factory.TetrominoFactory;

import java.util.ArrayList;
import java.util.List;


// Game Engine als Observable, welches die zwei Observer Soundmanager und HistoryManager informiert
public class GameEngine {

    private static final int TILE_SIZE = 30;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;

    private final Canvas canvas;
    private final GraphicsContext gc;
    private AnimationTimer gameLoop;
    private Polyomino currentPolyomino;
    private PolyominoFactory polyominoFactory;
    private CollisionManager collisionManager;
    private BoardRenderer boardRenderer;


    // Zeitsteuerung
    private long lastUpdate = 0;
    private double speed = 0.35; // Sekunden pro Schritt

    // Gesetzte Blöcke
    private List<Block> settledBlocks = new ArrayList<>();




    public GameEngine() {
        this.canvas = new Canvas(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);;
        this.gc = canvas.getGraphicsContext2D();
        this.polyominoFactory = new TetrominoFactory();
        this.currentPolyomino = spawnPolyomino();
        this.collisionManager = new CollisionManager(WIDTH, HEIGHT);
        this.boardRenderer = new BoardRenderer(gc, TILE_SIZE, WIDTH, HEIGHT);
    }

    public Canvas getCanvas() {
        return canvas;
    }


    public void renderBoard() {
        boardRenderer.render(settledBlocks, currentPolyomino);
    }


    public void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Da now zu Beginn 0 ist, wird abgebrochen, damit keine Bewegung passiert
                if (lastUpdate == 0) {
                    lastUpdate = now;
                    return;
                }

                // Berechnung der Zeit nach der letzten Bewegung des Blocks
                double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;

                // Der Block darf sich erst wieder bewegen, wenn mehr Zeit, als seine Bewegungsgeschwindigkeit verlaufen ist
                if (elapsedSeconds > speed) {
                    update();
                    renderBoard();
                    lastUpdate = now;
                }
            }
        };
        gameLoop.start();
    }


    public void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }


    private void update() {

        if (currentPolyomino != null) {
            // Prüft, ob der Block unten angekommen ist
            //System.out.println(currentTetromino.toString());
            if (collisionManager.canMove(currentPolyomino, 0, 1, settledBlocks)) {
                currentPolyomino.move(0,1);
            } else {

                // Block wurde gesetzt
                for (Block bc : currentPolyomino.getBlocks()) {
                    settledBlocks.add((Block) bc);
                }

                checkFullRows();


                //System.out.println("Tetromino gespawnt");
                currentPolyomino =  spawnPolyomino();
            }
        }
    }

    private Polyomino spawnPolyomino() {

        return polyominoFactory.createRandomPolyomino();

    }


    public void moveLeft() {
        // Bewegung nur möglich wenn nicht an Wand
        if (collisionManager.canMove(currentPolyomino, -1, 0, settledBlocks)) {
            currentPolyomino.move(-1, 0);
        }
    }

    public void moveRight() {
        // Bewegung nur möglich wenn nicht an Wand
        if (collisionManager.canMove(currentPolyomino, 1, 0, settledBlocks)) {
            currentPolyomino.move(1, 0);
        }
    }

    //
    public void rotateTetromino(String direction) {
        if (currentPolyomino == null) return;

        // Rotation mit dem Uhrzeigersinn
        if (direction.equals("clockwise")) {
            // Kopie wird erstellt und rotiert
            Polyomino rotated = clonePolyomino(currentPolyomino);
            rotated.rotateClockwise();
            //System.out.println("rotating Copy");


            // Wenn die Kopie erfolgreich rotiert wurde (sich bewegen darf) wird der eigentliche Block rotiert
            if (collisionManager.canMove(rotated, 0, 0, settledBlocks)) {
                currentPolyomino.rotateClockwise();
                //System.out.println("Clockwise rotated");
            }
        } else if (direction.equals("counterclockwise")) {
            // Kopie wird erstellt und rotiert
            Polyomino rotated = clonePolyomino(currentPolyomino);
            rotated.rotateCounterClockwise();
            //System.out.println("rotating Copy");


            // Wenn die Kopie erfolgreich rotiert wurde (sich bewegen darf) wird der eigentliche Block rotiert
            if (collisionManager.canMove(rotated, 0, 0, settledBlocks)) {
                currentPolyomino.rotateCounterClockwise();
                //System.out.println("Counterclockwise rotated");
            }
        }
    }




    // Erstellt eine Kopie, welchem zum Überprüfen der Rotation verwendet wird
    private Polyomino clonePolyomino(Polyomino original) {
        Polyomino copy = polyominoFactory.createSpecificPolyomino(original.getType());
        // Da ein neues Polyomino erstellt wird, müssen dessen Blöcke mit denen des Originals ersetzt werden
        copy.getBlocks().clear();
        for (Block b : original.getBlocks()) {
            copy.addBlock(new Block(b.getX(), b.getY(), b.getColor()));
        }
        copy.setPivot(original.getPivot());

/*
        for (Block bc : original.getBlocks()) {
            if (bc instanceof Block b) {
                // neue Block-Instanz mit gleicher Position und Farbe
                Block cloned = new Block(b.getX(), b.getY(), b.getColor());
                copy.addBlock(cloned);
            }
        }


 */
        return copy;
    }

    private void checkFullRows() {
        // Alle Reihen (y) von unten nach oben prüfen
        for (int y = HEIGHT - 1; y >= 0; y--) {
            final int row = y;

            // Alle Blöcke in Zeile y zählen
            long count = settledBlocks.stream()
                    .filter(b -> b.getY() == row)
                    .count();


            if (count >= WIDTH) {
                System.out.println("Reihe voll");
                // Alle Blöcke der Reihe aus settledBlocks löschen
                settledBlocks.removeIf(b -> b.getY() == row);

                // Alle Blöcke über dieser Zeile eine Reihe nach unten verschieben
                for (Block b : settledBlocks) {
                    if (b.getY() < row) {
                        b.move(0, 1); // nach unten
                    }
                }

                // Da wir die Zeilen verschoben haben, muss die neue untere Zeile nochmal überprüft werden
                y++;
            }
        }
    }


}

