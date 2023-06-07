package com.matheoauer.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Garden implements Serializable {

    private int width;
    private int height;
    private Case[][] cases;
    private Weather weather;
    private Inventory inventory;

    public Garden(int width, int height) {
        this.width = width;
        this.height = height;
        this.cases = new Case[width][height];
        this.inventory = new Inventory();
        this.weather = new Weather();
    }

    public void build() {
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[0].length; j++) {
                Soil defaultSoil = new Soil(0.70f);
                // 33% chance to have a vegetable
                Vegetable veg = null;
                if (Math.random() < 0.33) {
                    veg = new Vegetable("corn", 0.4f, 0f);
                }
                setCase(i, j, new Case(i, j, veg, defaultSoil));
            }
        }
    }

    /**
     * Get the width of the garden
     *
     * @return the width of the garden
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the garden
     *
     * @return the height of the garden
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the case at the given position, you can't set a case outside the initial size of the potager
     *
     * @param x the x position of the case
     * @param y the y position of the case
     * @param c the case to set
     */
    public void setCase(int x, int y, Case c) {
        if (x < 0 || x >= cases.length || y < 0 || y >= cases[0].length) {
            throw new IllegalArgumentException("The case is outside the potager");
        }
        // Remove all the observer from the old case
        if (cases[x][y] != null) {
            cases[x][y].deleteObservers();
        }
        cases[x][y] = c;
    }

    /**
     * Get the case at the given position
     *
     * @param x the x position of the case
     * @param y the y position of the case
     * @return the case at the given position
     */
    public Case getCase(int x, int y) {
        if (x < 0 || x >= cases.length || y < 0 || y >= cases[0].length) {
            throw new IllegalArgumentException("The case is outside the potager");
        }
        return cases[x][y];
    }

    /**
     * Get the neighbors of the case at the given position
     * <p>Make a cross pattern</p>
     *
     * @param x the x position
     * @param y the y position
     * @return the neighbors of the case at the given position
     */
    public List<Case> getNeighbors(int x, int y) {
        List<Case> neighbors = new ArrayList<>();
        if (x > 0) {
            neighbors.add(getCase(x - 1, y));
        }
        if (x < getWidth() - 1) {
            neighbors.add(getCase(x + 1, y));
        }
        if (y > 0) {
            neighbors.add(getCase(x, y - 1));
        }
        if (y < getHeight() - 1) {
            neighbors.add(getCase(x, y + 1));
        }
        return neighbors;
    }

    /**
     * Get the weather of the garden
     *
     * @return the weather of the garden
     */
    public Weather getWeather() {
        return weather;
    }

    /**
     * Get the inventory
     *
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
