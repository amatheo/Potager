package com.matheoauer;

import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.models.Case;
import com.matheoauer.models.Garden;
import com.matheoauer.runnables.GrowthSimulator;
import com.matheoauer.runnables.Scheduler;
import com.matheoauer.runnables.SoilWatering;
import com.matheoauer.runnables.WeatherSimulator;
import com.matheoauer.views.View;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main {
    public static final String pathUrl = "../../garden.save";

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            int height = GardenConfigLoader.getInstance().getGardenConfiguration().getHeight();
            int width = GardenConfigLoader.getInstance().getGardenConfiguration().getWidth();

            Garden garden = new Garden(width, height);

            try {
                // Création d'un flux d'entrée depuis le fichier
                FileInputStream fichierEntree = new FileInputStream(pathUrl);
                ObjectInputStream objetEntree = new ObjectInputStream(fichierEntree);

                // Lecture de la HashMap désérialisée à partir du fichier
                Garden g = (Garden) objetEntree.readObject();
                garden.weather = g.weather;
                garden.inventory = g.inventory;
                for (int i = 0; i < g.getWidth(); i++) {
                    for (int j = 0; j < g.getHeight(); j++) {
                        Case c = new Case(g.getCase(i, j));
                        garden.setCase(i, j, c);
                    }
                }

                // Fermeture des flux
                objetEntree.close();
                fichierEntree.close();

            } catch (Exception ignored) {
                ignored.printStackTrace();
                garden.build();
            }

            Scheduler scheduler = Scheduler.getInstance();
            scheduler.setGarden(garden);
            View view = new View(garden);
            view.build(garden);
            scheduler.addObserver(view);

            GrowthSimulator growthSimulator = new GrowthSimulator();
            WeatherSimulator weatherSimulator = new WeatherSimulator();
            SoilWatering soilWatering = new SoilWatering();
            scheduler.addTask(weatherSimulator);
            scheduler.addTask(growthSimulator);
            scheduler.addTask(soilWatering);

            view.setVisible(true);
            scheduler.start();
        });
    }
}