package com.matheoauer.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GardenConfiguration {
    String name;
    int height;
    int width;
    ArrayList<Vegetable> vegetables;
    Simulation simulation;

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    @JsonProperty("height")
    public int getHeight() {
        return this.height;
    }

    @JsonProperty("width")
    public int getWidth() {
        return this.width;
    }

    @JsonProperty("simulation")
    public Simulation getSimulation() {
        return this.simulation;
    }

    @JsonProperty("vegetables")
    public ArrayList<Vegetable> getVegetables() {
        return this.vegetables;
    }
}
