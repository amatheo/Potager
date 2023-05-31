package com.matheoauer.runnables;

import java.util.Calendar;
import java.util.Date;

public class WeatherSimulator extends Simulator {


    public WeatherSimulator() {
    }

    @Override
    public void run() {
        // Execute the simulation for a number of time depending on the delta time (ms)
        // Example: Simulating the weather for 1 hour

        long deltaTime = Scheduler.getInstance().getDeltaTime();

        for (int i = 0; i < 24; i++) {
            Date currentSimulate = new Date(oldDate.getTime() + (i * 1000 * 60 * 60));
            this.garden.getWeather().dayTemperature[i] = simulateWeather(currentSimulate);
        }

    }

    private float simulateWeather(Date date) {
        // Get the hour of the day from the new date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        // Get the mean temperature based on the day of the year and the hour of the day
        return getMeanTemperature(date, hourOfDay);
    }

    /**
     * Calculate the mean temperature based on the day of the year and the hour of the day
     *
     * @param date      the date
     * @param hourOfDay the hour of the day
     * @return the mean temperature
     */
    private float getMeanTemperature(Date date, int hourOfDay) {
        // Get the day of the year
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);


        float meanTemp = 11.0f; // Mean temperature in France

        // Calculate the mean temperature based on the day of the year
        float temperature = meanTemp + 9.0f * (float) Math.cos(2 * Math.PI * (dayOfYear + 10) / 365) / 2.0f;

        // Modulate using bump function depending on the hour of the day
        float bump = (float) Math.exp(-Math.pow((hourOfDay - 14.0f) / 6.0f, 2));
        temperature = temperature * (1.0f - bump) + 20.0f * bump;

        return temperature;
    }
}
