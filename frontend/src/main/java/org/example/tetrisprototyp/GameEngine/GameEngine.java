package org.example.tetrisprototyp.GameEngine;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.example.tetrisprototyp.Composite.Block;
import org.example.tetrisprototyp.Composite.Polyomino;
import org.example.tetrisprototyp.Factory.*;
import org.example.tetrisprototyp.History.HistorySaver;
import org.example.tetrisprototyp.UserManagement.UserSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Game Engine enthält die Hauptschleife des Spiels und implementiert die Spielmechanik und ihre Komponenten.
 * Observable, welches die zwei Observer Soundmanager und HistoryManager informiert.
 */
public class GameEngine implements Observable {

    // Größe der Kacheln und Breite/Höhe des Spielfeldes
    private static final int TILE_SIZE = 30;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;

    // Random und JavaFX-Variablen
    private final Random random = new Random();
    private final Canvas canvas;
    private final GraphicsContext gc;
    private AnimationTimer gameLoop;

    // Instanzen der für die Spielmechanik benötigten Komponenten
    private Polyomino currentPolyomino;
    private PolyominoFactory polyominoFactory;
    private CollisionManager collisionManager;
    private BoardRenderer boardRenderer;

    //Schwierigkeitsgrad & Punktzahl
    private int difficulty;
    private int score;

    // Variablen für Zeitsteuerung & Geschwindigkeitsregulierung
    private long lastUpdate = 0;
    double[] speedValues = {
            0.45, 0.40, 0.32, 0.25,
            0.18, 0.13, 0.10, 0.08, 0.06
    };//Geschwindigkeitsgrade
    private double speed = speedValues[0]; // Sekunden pro Schritt
    private int linesScored = 0; // Wird bei der Geschwindigkeitserhöhung verwendet
    private int level = 1; // Wird bei der Geschwindigkeitserhöhung verwendet

    // Liste mit allen gesetzten Blöcken
    private List<Block> settledBlocks = new ArrayList<>();

    // Liste aller Observer
    private final List<Observer> observers = new ArrayList<>();




    public GameEngine(Canvas canvas, int difficulty) {
        //this.canvas = new Canvas(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        //this.polyominoFactory = new TetrominoFactory();
        this.collisionManager = new CollisionManager(WIDTH, HEIGHT);
        this.boardRenderer = new BoardRenderer(gc, TILE_SIZE, WIDTH, HEIGHT);
        this.difficulty = difficulty;
        this.currentPolyomino = spawnPolyomino();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    // Aufrufen der Render-Funktion aus der boardRenderer-Klasse
    public void renderBoard() {
        boardRenderer.render(settledBlocks, currentPolyomino);
    }

    // Starten der Spielschleife, was nach Verlassen der Schwierigkeitsauswahl passiert
    public void startGameLoop() {

        System.out.println("Das Spiel wird in Schwierigkeit " + difficulty + " gestartet");


        // Erstellung der Observer
        Observer soundManager = new SoundManager();
        addObserver(soundManager);

        UserSession session = UserSession.getInstance();
        String jwt = session.getJwt();
        String username = session.getUsername();
        if (jwt != null && !jwt.isBlank() && username != null && !username.isBlank()) {
            Observer hSaver = new HistorySaver(username, jwt);
            addObserver(hSaver);
        }



        // Erstellung der gameLoop
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

    // Stoppen der Spielschleife und Informieren der Observer
    public void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
        notifyObservers("gameOver");
    }

    // Wird in der Spielschleife aufgerufen und enthält die Spielmechanik
    private void update() {

        if (currentPolyomino != null) {

            // Prüft, ob der Block unten angekommen ist.
            // Wenn der Block sich bewegen kann, so fällt er weiter nach unten.
            // Ansonsten wird er gesetzt.
            if (collisionManager.canMove(currentPolyomino, 0, 1, settledBlocks)) {
                currentPolyomino.move(0,1);
            } else {

                // Block wurde gesetzt
                for (Block bc : currentPolyomino.getBlocks()) {
                    settledBlocks.add((Block) bc);
                }

                checkFullRows();


                // Neues Polyomino erscheint
                currentPolyomino =  spawnPolyomino();
            }
        }
    }

    // Lässt ein neues Polyomino erscheinen. Enthält außerdem die überprüfung, ob das Spielfeld voll ist.
    private Polyomino spawnPolyomino() {

        if (difficulty == 1) {
            // Wenn "Leicht" ausgewählt wurde, so werden nur Tetrominos gespawnt.
            // Deshalb wird die Factory vor dem Start der Gameloop einmal initialisiert.
            this.polyominoFactory = new TetrominoFactory();
        } else {
            // Bei Schwierigkeitsgrad 2 und 3 wird zwischen den unterschiedlichen Polyomino-Factories gewechselt.
            List<PolyominoFactory> factories = List.of(
                    new DominoFactory(),
                    new TrominoFactory(),
                    new TetrominoFactory(),
                    new PentominoFactory()
            );
            // Zufällige Factory auswählen
            this.polyominoFactory = factories.get(random.nextInt(factories.size()));
        }

        // Erstellung des Polyomino mit der Factory
        Polyomino newPoly = polyominoFactory.createRandomPolyomino();


        // Prüfen, ob das neue Polyomino sofort kollidiert
        if (!collisionManager.canMove(newPoly, 0, 0, settledBlocks)) {
            // Board voll → Spiel beenden
            stopGameLoop(); // AnimationTimer stoppen
            return null;
        }

        return newPoly;
    }

    // Bewegung des Polyominos nach links


    public void moveLeft() {

        if (currentPolyomino == null) return;

        if (collisionManager.canMove(currentPolyomino, -1, 0, settledBlocks)) {

            currentPolyomino.move(-1, 0);

        }

    }

    public void moveRight() {

        if (currentPolyomino == null) return;

        if (collisionManager.canMove(currentPolyomino, 1, 0, settledBlocks)) {

            currentPolyomino.move(1, 0);

        }

    }


    // Rotieren des Polyominos. Dabei wird eine Kopie erstellt, an der geprüft wird, ob die Rotation gültig ist.
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

        return copy;
    }



    // Überprüfung, ob eine Reihe voll ist. Falls ja werden Observer informiert und das Level/Geschwindigkeit erhöht.
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

                // Observer informieren
                String eventScored = "scored";
                notifyObservers(eventScored);


                // Alle Blöcke über dieser Zeile eine Reihe nach unten verschieben
                for (Block b : settledBlocks) {
                    if (b.getY() < row) {
                        b.move(0, 1); // nach unten
                    }
                }

                // Da wir die Zeilen verschoben haben, muss die neue untere Zeile nochmal überprüft werden
                y++;

                // Alle 5 Reihen erhöht sich das Level und damit die Geschwindigkeit (bei Schwierigkeit 1&2).
                // Alle 3 Reihen bei Schwierigkeit 3.
                if (difficulty == 3){
                    linesScored++;
                    if (linesScored % 3 == 0) {
                        level++;
                        speed = speedValues[level];
                        System.out.println(level);
                    }
                } else {
                    linesScored++;
                    if (linesScored % 5 == 0) {
                        level++;
                        speed = speedValues[level];
                        System.out.println(level);
                    }
                }

                //Punktzahl erhöhen
                score += 100;

                // In GameStats übertragen
                GameStats.setScore(score);
                GameStats.setLevel(level);
                GameStats.setLinesScored(linesScored);
                GameStats.setDifficulty(difficulty);


            }
        }

    }



    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer obs : observers) {
            obs.update(event);
        }
    }

}

