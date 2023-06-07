package com.matheoauer.models;

import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.config.VegetableConf;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.logging.Logger;

public class Inventory extends Observable implements Serializable {
    private HashMap<String, Integer> inventory;

    public Inventory(){
        List<VegetableConf> allVegetableConfs = GardenConfigLoader.getInstance().getVegetables();
        this.inventory = new HashMap<>();
        for(VegetableConf vegetableConf : allVegetableConfs){
            this.inventory.put(vegetableConf.getName().toUpperCase(), 0);
        }
    }

    /**
     * Get the inventory
     *
     * @return the inventory
     */
    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    public void addToInventory(String vegetableName){
        vegetableName = vegetableName.toUpperCase();
        if(!this.inventory.containsKey(vegetableName)){
            Logger.getGlobal().warning("This vegetable doesn't exist");
            return;
        }

        this.inventory.put(vegetableName, this.inventory.get(vegetableName) + 1);
        this.setChanged();
        this.notifyObservers();
    }
}