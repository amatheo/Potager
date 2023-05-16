package com.matheoauer;

import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.models.Garden;
import com.matheoauer.runnables.GrowthSimulator;
import com.matheoauer.runnables.Scheduler;
import com.matheoauer.runnables.SoilWatering;
import com.matheoauer.views.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            int height = GardenConfigLoader.getInstance().getGardenConfiguration().getHeight();
            int width = GardenConfigLoader.getInstance().getGardenConfiguration().getWidth();

            Garden garden = new Garden(width, height);
            Scheduler scheduler = Scheduler.getInstance();
            scheduler.setGarden(garden);

            View view = new View(garden);

            GrowthSimulator growthSimulator = new GrowthSimulator();
            SoilWatering soilWatering = new SoilWatering();
            scheduler.addTask(growthSimulator);
            scheduler.addTask(soilWatering);

            view.setVisible(true);
            scheduler.start();
        });
    }
}