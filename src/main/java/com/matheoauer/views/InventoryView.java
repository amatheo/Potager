package com.matheoauer.views;

import com.matheoauer.SpriteEnum;
import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.models.Inventory;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public class InventoryView extends JPanel implements Observer {

    private final HashMap<String, JLabel> inventory;
    private final JLabel labelMoney = new JLabel("Argent : 0");

    public InventoryView(HashMap<String, Integer> inventory) {
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        this.inventory = new HashMap<>();
        this.setLayout(new GridLayout(inventory.size() + 1, 1));

        for (String vegetableName : inventory.keySet()) {
            JLabel label = new JLabel("x" + inventory.get(vegetableName));
            SpriteEnum spriteEnum = SpriteEnum.valueOf(vegetableName.toUpperCase());
            Image iconImage = GardenConfigLoader.getInstance().getAtlasLoader().getSpriteImage(spriteEnum.getAtlasIndex());
            label.setIcon(new ImageIcon(iconImage.getScaledInstance(30, 30, 0)));
            this.inventory.put(vegetableName, label);
            add(label);
        }
        add(labelMoney);
        this.setPreferredSize(new Dimension(140, 0));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(o instanceof Inventory inventory)) {
            Logger.getGlobal().warning("The object is not an inventory");
            return;
        }

        for (String vegetableName : inventory.getInventory().keySet()) {
            JLabel label = this.inventory.get(vegetableName);
            if (label != null) {
                label.setText("x" + inventory.getInventory().get(vegetableName));
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedNumber = decimalFormat.format(inventory.getMoney());
        labelMoney.setText("Argent : " + formattedNumber + "€");
    }
}
