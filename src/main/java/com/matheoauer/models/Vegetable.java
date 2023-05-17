package com.matheoauer.models;

import java.util.Date;
import java.util.Observable;

public class Vegetable extends Observable {
    private final String name;
    private Date plantedAt;

    /**
     * The growth of the vegetable, between 0 and 1
     * 0 means the vegetable is just planted
     * 1 means the vegetable is ready to be harvested
     */
    private float growth;

    /**
     * The rotting of the vegetable, between 0 and 1
     * 0 means the vegetable is not rotting
     * 1 means the vegetable is completely rotten
     */
    private float decay;

    /**
     * Create a vegetable with a name and a growth of 0
     */
    public Vegetable(String name) {
        this.name = name;
    }

    public Vegetable(String name, float growth, float decay) {
        this(name);
        if (growth < 0 || growth > 1) {
            throw new IllegalArgumentException("The growth must be between 0 and 1");
        }
        if (decay < 0 || decay > 1) {
            throw new IllegalArgumentException("The decay must be between 0 and 1");
        }
        this.decay = decay;
        this.growth = growth;
    }


    public String getName() {
        return name;
    }

    public boolean isHarvestable() {
        return growth == 1;
    }

    public void grow(float growth) {
        this.growth += growth;
        if (this.growth > 1) {
            this.growth = 1;
        }
        this.setChanged();
        this.notifyObservers();
    }

    public float getGrowth() {
        return this.growth;
    }

    public Date getPlantedAt() {
        return this.plantedAt;
    }

    public void setPlantedAt(Date plantedAt) {
        this.plantedAt = plantedAt;
        this.setChanged();
        this.notifyObservers();
    }

    public void decay(float decay) {
        this.decay += decay;
        if (this.decay > 1) {
            this.decay = 1;
        }
        this.setChanged();
        this.notifyObservers();
    }

    public float getDecay() {
        return this.decay;
    }
}
