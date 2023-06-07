package com.matheoauer.runnables;

import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.models.Case;

public class DecaySim extends Simulator {

    @Override
    public void run() {
        for (int i = 0; i < this.garden.getWidth(); i++) {
            for (int j = 0; j < this.garden.getHeight(); j++) {
                float decayProbability = GardenConfigLoader.getInstance().getGardenConfiguration().getDecayProbability();
                Case myCase = this.garden.getCase(i, j);
                if (myCase.getVegetable() == null) {
                    continue;
                }
                // Random
                if (Math.random() < decayProbability) {
                    // Is in decay
                    // Random amount between 0.01 and 0.2
                    float decayAmount = (float) (Math.random() * 0.19 + 0.01);
                    myCase.getVegetable().increaseDecay(0.4f);
                }
            }
        }

    }
}
