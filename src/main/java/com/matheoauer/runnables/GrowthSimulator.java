package com.matheoauer.runnables;

import com.matheoauer.models.Case;
import com.matheoauer.models.Weather;

public class GrowthSimulator extends Simulator {

    @Override
    public void run() {
        for (int i = 0; i < this.garden.getWidth(); i++) {
            for (int j = 0; j < this.garden.getHeight(); j++) {
                handleGrowth(this.garden.getCase(i, j), this.garden.getWeather());
                shareHumidity(this.garden.getCase(i, j));
            }
        }
    }

    /**
     * Share the humidity of the current case with the neighbors
     *
     * @param c the current case
     */
    private void shareHumidity(Case c) {
        float humidity = c.getSoil().getHumidity();
        float humidityDecrease = humidity * 0.1f;
        float humidityShare = (humidityDecrease * 0.7f) / 4;

        for (Case neighbor : this.garden.getNeighbors(c.getX(), c.getY())) {
            neighbor.getSoil().increaseHumidity(humidityShare);
        }
        c.getSoil().decreaseHumidity(humidityDecrease);
    }

    /**
     * Handle the growth of the vegetable
     *
     * @param c       the current case
     * @param weather the weather of the garden
     */
    private void handleGrowth(Case c, Weather weather) {
        if (c.getVegetable() == null) {
            return;
        }
        if (c.getVegetable().isHarvestable()) {
            return;
        }
        // Get the base growth per day of the vegetable
        double baseGrowth = c.getVegetable().getVegetableConf().getGrowRate();

        float[] dayTemperature = weather.dayTemperature;

        double growthPerHour = baseGrowth / 24;
        for (int i = 0; i < dayTemperature.length; i++) {
            // If the temperature is below the minimum temperature of the vegetable, the vegetable will not grow
            if (dayTemperature[i] < c.getVegetable().getVegetableConf().getRequirement().getTemperatureMin()) {
                growthPerHour = 0;
                continue;
            }

            // If the temperature is above the maximum temperature of the vegetable, the vegetable will not grow
            if (dayTemperature[i] > c.getVegetable().getVegetableConf().getRequirement().getTemperatureMax()) {
                growthPerHour = 0;
                continue;
            }

            // If the humidity is below the minimum humidity of the vegetable, the vegetable will not grow
            if (c.getSoil().getHumidity() < c.getVegetable().getVegetableConf().getRequirement().getHumidity()) {
                growthPerHour = 0;
                continue;
            }

            float medianGrowRate = (float) ((c.getVegetable().getVegetableConf().getRequirement().getTemperatureMax() + c.getVegetable().getVegetableConf().getRequirement().getTemperatureMin()) / 2);
            float medianGrowRateDifference = Math.abs(medianGrowRate - dayTemperature[i]);
            float medianGrowRateDifferencePercentage = medianGrowRateDifference / medianGrowRate;

            // If the temperature is close to the median temperature, the vegetable will grow faster
            if (medianGrowRateDifferencePercentage < 0.1) {
                growthPerHour *= 1.2;
            }

            // Use standard distribution to calculate the growth rate. The final result should be between 0 and 1
            double growthRateMulti = Math.exp(-Math.pow((dayTemperature[i] - medianGrowRate), 2) / (2 * Math.pow(c.getVegetable().getVegetableConf().getRequirement().getTemperatureMax() - c.getVegetable().getVegetableConf().getRequirement().getTemperatureMin(), 2)));
            growthPerHour *= growthRateMulti;
            c.getVegetable().grow((float) growthPerHour);

            // Consume the water of the soil depending on growthRateMulti
            double baseComsumption = c.getVegetable().getVegetableConf().getHumidityConsumption();
            double consumptionPerHour = baseComsumption / 24;
            consumptionPerHour *= growthRateMulti;
            c.getSoil().decreaseHumidity((float) consumptionPerHour);
        }
        if (c.getVegetable().getDecay() > 0.75) {
            c.setVegetable(null);
        }
    }
}
