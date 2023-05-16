package com.matheoauer.runnables;

import com.matheoauer.models.Case;

public class SoilWatering extends Simulator {


    @Override
    public void run() {
        // Distribute the water of the soil to the surrounding cases depending on the distance and difference of humidity

        for (int i = 0; i < this.garden.getWidth(); i++) {
            for (int j = 0; j < this.garden.getHeight(); j++) {
                Case c = this.garden.getCase(i, j);
                float humidity = c.getSoil().getHumidity();
                float humidityToDistribute = humidity / 4;

                // Distribute the water to the surrounding cases
                if (i > 0) {
                    this.garden.getCase(i - 1, j).getSoil().increaseHumidity(humidityToDistribute);
                }
                if (i < this.garden.getWidth() - 1) {
                    this.garden.getCase(i + 1, j).getSoil().increaseHumidity(humidityToDistribute);
                }
                if (j > 0) {
                    this.garden.getCase(i, j - 1).getSoil().increaseHumidity(humidityToDistribute);
                }
                if (j < this.garden.getHeight() - 1) {
                    this.garden.getCase(i, j + 1).getSoil().increaseHumidity(humidityToDistribute);
                }

                // Decrease the humidity of the soil
                c.getSoil().decreaseHumidity(humidity / 2);
            }
        }
    }
}
