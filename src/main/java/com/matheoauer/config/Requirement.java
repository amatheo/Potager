package com.matheoauer.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Requirement implements Serializable {
    private double humidity;

    private double temperatureMin;

    private double temperatureMax;

    @JsonProperty("humidity")
    public double getHumidity() {
        return this.humidity;
    }

    @JsonProperty("temperatureMin")
    public double getTemperatureMin() {
        return this.temperatureMin;
    }

    @JsonProperty("temperatureMax")
    public double getTemperatureMax() {
        return this.temperatureMax;
    }
}
