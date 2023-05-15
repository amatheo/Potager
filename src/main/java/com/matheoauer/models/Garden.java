package com.matheoauer.models;

import com.matheoauer.models.Case;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Garden extends Observable implements Observer {

    private Case[][] cases;

    private int width;
    private int height;

    public Garden(int width, int height) {
        this.width = width;
        this.height = height;
        cases = new Case[width][height];

        Vegetable v = new Vegetable("Tomato");

        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[0].length; j++) {
                setCase(i, j, new Case(i, j, v));
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Set the case at the given position, you can't set a case outside the initial size of the potager
     * @param x the x position of the case
     * @param y the y position of the case
     * @param c the case to set
     */
    public void setCase(int x, int y, Case c) {
        if (x < 0 || x >= cases.length || y < 0 || y >= cases[0].length) {
            throw new IllegalArgumentException("The case is outside the potager");
        }
        // Remove the observer from the old case
        if (cases[x][y] != null) {
            cases[x][y].deleteObserver(this);
        }
        cases[x][y] = c;
        cases[x][y].addObserver(this);
    }

    /**
     * Get the case at the given position
     * @param x the x position of the case
     * @param y the y position of the case
     * @return the case at the given position
     */
    public Case getCase(int x, int y) {
        return cases[x][y];
    }

    @Override
    public void update(Observable o, Object arg) {
        Case c = (Case) o;
        this.setChanged();
        this.notifyObservers(c);
    }
}
