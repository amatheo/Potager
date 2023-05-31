package com.matheoauer.models;

import com.matheoauer.runnables.Scheduler;

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
        this.soil = null;
    }

    public Case(int x, int y, Vegetable vegetable, Soil soil) {
        this(x, y);
        if (vegetable != null) {
            setVegetable(vegetable);
        }
        if (soil != null) {
            setSoil(soil);
        }
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
        if (this.vegetable != null) {
            this.vegetable.deleteObserver(this);
        }
        this.vegetable = vegetable;
        if (vegetable != null) {
            this.vegetable.addObserver(this);
            this.vegetable.setPlantedAt(Scheduler.getInstance().getDate());
        }
        this.setChanged();
        this.notifyObservers();
    }

    public Soil getSoil() {
        return soil;
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
