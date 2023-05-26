package com.matheoauer.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Requirement {
    public double humidity;

    @JsonProperty("humidity")
    public double getHumidity() {
        return this.humidity;
    }
}
