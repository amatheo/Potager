package com.matheoauer.models;

public class Garden {

    private final int width;
    private final int height;
    private final Case[][] cases;

    public Garden(int width, int height) {
        this.width = width;
        this.height = height;
        cases = new Case[width][height];

        this.build();
    }

    private void build() {
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[0].length; j++) {
                Soil defaultSoil = new Soil(0.2f);
                Vegetable defaultVegetable = new Vegetable("Corn", 0.4f, 0f);
                setCase(i, j, new Case(i, j, defaultVegetable, defaultSoil));
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
}
