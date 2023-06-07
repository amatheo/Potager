package com.matheoauer.models;

import java.io.Serializable;
import java.util.Observable;

public class Weather extends Observable implements Serializable {

    public float[] dayTemperature = new float[24];
    public float[] daySoilHumidity = new float[24];
}
