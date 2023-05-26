package com.matheoauer.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Vegetable {
    ArrayList<Double> stage;
    Requirement requirement;
    String name;
    double growRate;

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

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
}
