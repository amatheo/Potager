package com.matheoauer.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Simulation {
    double time_sleep;
    float daysToSimulate;

    @JsonProperty("time_sleep")
    public double getTime_sleep() {
        return this.time_sleep;
    }

    @JsonProperty("daysToSimulate")
    public float getDaysToSimulate() {
        return this.daysToSimulate;
    }
}
