package com.matheoauer.runnables;

import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.models.Case;

public class DecaySim extends Simulator {

    @Override
    public void run() {
        float decayProbability = GardenConfigLoader.getInstance().getGardenConfiguration().getDecayProbability();
        float decayAmount = GardenConfigLoader.getInstance().getGardenConfiguration().getDecayAmount();
        for (int i = 0; i < this.garden.getWidth(); i++) {
            for (int j = 0; j < this.garden.getHeight(); j++) {
                
                Case myCase = this.garden.getCase(i, j);
                if (myCase.getVegetable() == null) {
                    continue;
                }
                // Random
                if (Math.random() < decayProbability) {
                    // Is in decay
                    // Random amount between 0.01 and 0.2
                    float decayAmountCalc = (float) (Math.random() * 0.19 + decayAmount);
                    myCase.getVegetable().increaseDecay(decayAmountCalc);
                }
            }
        }

    }
}
