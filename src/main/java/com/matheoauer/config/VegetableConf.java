package com.matheoauer.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class VegetableConf implements Serializable {
    ArrayList<Double> stage;
    Requirement requirement;
    String name;
    double growRate;
    double humidityConsumption;
    double price;

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    /**
     * The grow rate of the vegetable, between 0 and 1
     * <p>The grow rate is for a day, or 86400000 milliseconds</p>
     *
     * @return
     */
    @JsonProperty("grow_rate")
    public double getGrowRate() {
        return this.growRate;
    }

    @JsonProperty("requirement")
    public Requirement getRequirement() {
        return this.requirement;
    }

    @JsonProperty("stage")
    public ArrayList<Double> getStage() {
        return this.stage;
    }

    @JsonProperty("humidity_consumption")
    public double getHumidityConsumption() {
        return this.humidityConsumption;
    }

    @JsonProperty("price")
    public double getPrice() {
        return this.price;
    }
}
