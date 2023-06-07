package com.matheoauer.runnables;

import com.matheoauer.config.GardenConfigLoader;

import java.util.Random;

public class PriceSimulator extends Simulator{
    @Override
    public void run() {
        double price = 0;
        Random random = new Random();
        for (String key : this.garden.getInventory().getInventory().keySet()) {
            if (GardenConfigLoader.getInstance().findVegetable(key).isPresent())
                price += (0.85 + (random.nextDouble() * 0.3)) * this.garden.getInventory().getInventory().get(key) * GardenConfigLoader.getInstance().findVegetable(key).get().getPrice();
        }
        this.garden.getInventory().setMoney(price);
    }
}
