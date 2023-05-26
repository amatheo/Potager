package com.matheoauer.runnables;

import java.util.Calendar;
import java.util.Random;

public class WeatherSimulator extends Simulator {


    public WeatherSimulator() {
    }

    @Override
    public void run() {
        // Get the hour of the day from the new date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(newDate);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        // Example: Simulating temperature using a Gaussian function
        float meanTemperature = getMeanTemperature(hourOfDay);
        float temperatureVariation = getTemperatureVariation(hourOfDay);

        // Get the month from the new date
        int month = calendar.get(Calendar.MONTH);

        // Example: Adjusting temperature based on month
        if (month >= 3 && month <= 5) {
            // Spring season
            meanTemperature += 5.0f;
        } else if (month >= 6 && month <= 8) {
            // Summer season
            meanTemperature += 10.0f;
        } else if (month >= 9 && month <= 11) {
            // Autumn season
            meanTemperature += 3.0f;
        } else {
            // Winter season
            meanTemperature -= 5.0f;
        }

        Random random = new Random();
        float temperatureVariationRange = 2.0f; // Range of random temperature variation
        float randomTemperatureVariation = random.nextFloat() * temperatureVariationRange - temperatureVariationRange / 2.0f;
        float airTemperature = meanTemperature + temperatureVariation + randomTemperatureVariation;
        this.garden.getWeather().setAirTemperature(airTemperature);
        this.garden.getWeather().setSunExposure(getSunExposure(hourOfDay));
    }

    /**
     * Returns the mean temperature based on the hour of the day.
     *
     * @param hourOfDay the hour of the day (0-23)
     * @return the mean temperature at the given hour of the day
     */
    private float getMeanTemperature(int hourOfDay) {
        // Calculate the mean temperature using a mathematical function
        // Adjust the parameters to customize the temperature curve

        // Example: Using a Gaussian function
        float mean = 20.0f; // Mean temperature at noon
        float amplitude = 5.0f; // Amplitude of temperature variation
        float peakHour = 12.0f; // Hour of the peak temperature

        return (float) (mean + amplitude * Math.exp(-Math.pow(hourOfDay - peakHour, 2) / (2 * 3 * 3)));
    }

    /**
     * Returns the temperature variation based on the hour of the day.
     *
     * @param hourOfDay the hour of the day (0-23)
     * @return the temperature variation at the given hour of the day
     */
    private float getTemperatureVariation(int hourOfDay) {
        // Calculate the temperature variation using a mathematical function
        // Adjust the parameters to customize the temperature curve

        // Example: Using a bump function
        float peakTemperatureVariation = 5.0f; // Peak temperature variation
        float peakHour = 14.0f; // Hour of the peak temperature variation
        float width = 3.0f; // Width of the temperature variation

        float variation = peakTemperatureVariation * (float) Math.exp(-Math.pow(hourOfDay - peakHour, 2) / (2 * width * width));

        // Adjust the variation based on the time of day
        if (hourOfDay < 6 || hourOfDay > 18) {
            variation *= 0.5f; // Decrease variation during nighttime
        }

        return variation;
    }

    /**
     * Returns the sun exposure based on the hour of the day, following a normal distribution curve.
     *
     * @param hourOfDay the hour of the day (0-23)
     * @return the sun exposure at the given hour of the day
     */
    private float getSunExposure(int hourOfDay) {
        // Calculate the sun exposure using a normal distribution curve
        // Adjust the parameters to customize the sun exposure curve

        float meanExposure = 0.3f; // Mean sun exposure
        float standardDeviation = 0.2f; // Standard deviation of sun exposure

        // Calculate the probability density function (PDF) of the normal distribution
        float x = (float) hourOfDay;
        float exponent = -(float) Math.pow(x - 12.0f, 2) / (2 * standardDeviation * standardDeviation);
        float pdf = (float) (Math.exp(exponent) / (Math.sqrt(2 * Math.PI) * standardDeviation));

        // Scale the PDF to the range [0, 1]
        float sunExposure = meanExposure + pdf;

        // Ensure sun exposure is within [0, 1]
        sunExposure = Math.max(0.0f, Math.min(1.0f, sunExposure));

        return sunExposure;
    }
}
