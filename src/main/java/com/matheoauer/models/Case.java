package com.matheoauer.models;

import java.util.Observable;
import java.util.Observer;

public class Case extends Observable implements Observer {
    private int x;
    private int y;
    private Vegetable vegetable;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.vegetable = null;
    }

    public Case(int x, int y, Vegetable vegetable) {
        this(x, y);
        setVegetable(vegetable);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vegetable getVegetable() {
        return vegetable;
    }

    public void setVegetable(Vegetable vegetable) {
        if (this.vegetable != null){
            this.vegetable.deleteObserver(this);
        }
        vegetable.addObserver(this);
        this.vegetable = vegetable;
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setChanged();
        this.notifyObservers();
    }
}
