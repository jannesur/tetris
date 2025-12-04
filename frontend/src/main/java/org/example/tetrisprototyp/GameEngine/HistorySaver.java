package org.example.tetrisprototyp.GameEngine;

public class HistorySaver implements Observer{

    @Override
    public void update(String event) {

        if (event.equals("gameOver")) {
            System.out.println("Historie wird gespeichert");
        }

    }

}
