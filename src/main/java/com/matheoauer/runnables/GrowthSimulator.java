package com.matheoauer.runnables;

public class GrowthSimulator extends Simulator {

    @Override
    public void run() {
        for (int i = 0; i < this.garden.getWidth(); i++) {
            for (int j = 0; j < this.garden.getHeight(); j++) {
                if (this.garden.getCase(i, j).getVegetable() != null) {
                    this.garden.getCase(i, j).getVegetable().grow(0.001f);
                }
            }
        }
    }
}
