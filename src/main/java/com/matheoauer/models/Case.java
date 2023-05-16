package com.matheoauer.models;

import java.util.Observable;
import java.util.Observer;

public class Case extends Observable implements Observer {
    private final int x;
    private final int y;
    private Vegetable vegetable;
    private Soil soil;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.vegetable = null;
    }

    public Case(int x, int y, Vegetable vegetable, Soil soil) {
        this(x, y);
        setVegetable(vegetable);
        setSoil(soil);
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

    public Soil getSoil() {
        return soil;
    }

    public void setVegetable(Vegetable vegetable) {
        if (this.vegetable != null) {
            this.vegetable.deleteObserver(this);
        }
        vegetable.addObserver(this);
        this.vegetable = vegetable;
        this.setChanged();
        this.notifyObservers();
    }

    private void setSoil(Soil soil) {
        if (this.soil != null) {
            this.soil.deleteObserver(this);
        }
        soil.addObserver(this);
        this.soil = soil;
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setChanged();
        this.notifyObservers();
    }
}
