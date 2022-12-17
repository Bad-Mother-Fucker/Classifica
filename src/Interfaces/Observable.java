package Interfaces;

import java.util.ArrayList;

public interface Observable {
    ArrayList<Observer> observers = new ArrayList<>();

    void addObserver(Observer o);
    void remove(Observer o);
    void notifyObservers();

}
