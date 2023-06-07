package com.matheoauer.models;

import java.util.Observable;

public class Weather extends Observable {

    public float[] dayTemperature = new float[24];
    public float[] daySoilHumidity = new float[24];
}
