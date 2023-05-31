package com.matheoauer.runnables;

import com.matheoauer.models.Case;

import java.util.List;

public class SoilWatering extends Simulator {


    @Override
    public void run() {
        for (int i = 0; i < this.garden.getWidth(); i++) {
            for (int j = 0; j < this.garden.getHeight(); j++) {
                Case c = this.garden.getCase(i, j);
                // Share the humidity with the neighbors and decrease the humidity of the current case
                float humidity = c.getSoil().getHumidity();

                float humidityDecrease = humidity * 0.1f;
                float humidityShare = (humidityDecrease * 0.7f) / 4;

                // Get the neighbors cases and increase their humidity
                List<Case> neighbors = this.garden.getNeighbors(i, j);
                for (Case neighbor : neighbors) {
                    neighbor.getSoil().increaseHumidity(humidityShare);
                }
                c.getSoil().decreaseHumidity(humidityDecrease);
            }
        }
    }
}
