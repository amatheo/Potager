package com.matheoauer.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GardenConfiguration {
    String name;
    int height;
    int width;
    ArrayList<VegetableConf> vegetables;
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
    public ArrayList<VegetableConf> getVegetables() {
        return this.vegetables;
    }
}
