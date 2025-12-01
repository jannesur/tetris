package org.example.tetrisprototyp.GameEngine;

public class SoundManager implements Observer {

    @Override
    public void update(String event) {

        // Hier muss noch geschaut werden, dass wenn mehrere Reihen gleichzeitig voll werden
        // vielleicht trotzdem nur einmal der Sound abgespielt wird.
        if (event.equals("scored")) {
            System.out.println("Sound wird abgespielt");
        }

    }

}
