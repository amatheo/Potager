package com.matheoauer;

import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.models.Garden;
import com.matheoauer.runnables.Scheduler;
import com.matheoauer.runnables.GrowthSimulator;
import com.matheoauer.views.View;

public class Main {
    public static void main(String[] args) {

        int height = GardenConfigLoader.getInstance().getGardenConfiguration().getHeight();
        int width = GardenConfigLoader.getInstance().getGardenConfiguration().getWidth();

        Garden garden = new Garden(width, height);
        Scheduler scheduler = Scheduler.getInstance();
        scheduler.setGarden(garden);

        View view = new View();
        garden.addObserver(view);


        GrowthSimulator growthSimulator = new GrowthSimulator();
        scheduler.addTask(growthSimulator);

        view.setVisible(true);
        scheduler.start();
    }
}