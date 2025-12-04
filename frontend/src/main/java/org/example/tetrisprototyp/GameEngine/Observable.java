package org.example.tetrisprototyp.GameEngine;

public interface Observable {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String event);

}
