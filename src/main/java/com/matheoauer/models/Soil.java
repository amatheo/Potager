package com.matheoauer.models;

import java.io.Serializable;
import java.util.Observable;

public class Soil extends Observable implements Serializable {

    public Soil(float humidity) {
        this.humidity = humidity;
    }

    /**
     * The humidity of the soil
     * <p>Between 0 and 1</p>
     */
    private float humidity;

    public void increaseHumidity(float humidity) {
        this.humidity = Math.min(1, this.humidity + humidity);
        this.setChanged();
        this.notifyObservers();
    }

    public void decreaseHumidity(float humidity) {
        this.humidity = Math.max(0, this.humidity - humidity);
        this.setChanged();
        this.notifyObservers();
    }

    public float getHumidity() {
        return humidity;
    }
}
