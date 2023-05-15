package com.matheoauer.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Simulation {
    double time_sleep;
    int time_multiplier;

    @JsonProperty("time_sleep")
    public double getTime_sleep() {
        return this.time_sleep;
    }

    @JsonProperty("time_multiplier")
    public int getTime_multiplier() {
        return this.time_multiplier;
    }
}
