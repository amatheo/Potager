package com.matheoauer.models;

import java.util.Observable;

public class Weather extends Observable {

    /**
     * The air humidity between 0 and 1
     * 0 means no humidity
     * 1 means 100% humidity
     */
    float airHumidity;

    /**
     * The air temperature in °C
     */
    float airTemperature;

    /**
     * The sun exposure between 0 and 1
     * 0 means no sun exposure
     * 1 means 100% sun exposure
     */
    float sunExposure;

    public Weather(float airHumidity, float airTemperature, float sunExposure) {
        this.airHumidity = airHumidity;
        this.airTemperature = airTemperature;
        this.sunExposure = sunExposure;
    }

    public float getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(float airHumidity) {
        this.airHumidity = airHumidity;
        this.setChanged();
        this.notifyObservers();
    }

    public float getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(float airTemperature) {
        this.airTemperature = airTemperature;
        this.setChanged();
        this.notifyObservers();
    }

    public float getSunExposure() {
        return sunExposure;
    }

    public void setSunExposure(float sunExposure) {
        this.sunExposure = sunExposure;
        this.setChanged();
        this.notifyObservers();
    }
}
