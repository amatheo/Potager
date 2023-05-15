package com.matheoauer.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vegetable {
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
}
