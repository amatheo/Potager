package com.matheoauer.views;

import com.matheoauer.SpriteEnum;
import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.config.VegetableConf;
import com.matheoauer.views.adapter.VegetablePlantingAdapter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FooterView extends JPanel {

    private final View viewParent;

    public FooterView(View viewParent) {
        this.viewParent = viewParent;
        List<VegetableConf> allVegetableConfs = GardenConfigLoader.getInstance().getVegetables();
        this.setLayout(new GridLayout(1, allVegetableConfs.size()));

        for (VegetableConf vegetableConf : allVegetableConfs) {

            JLabel label = new JLabel(vegetableConf.getName());
            SpriteEnum spriteEnum = SpriteEnum.valueOf(vegetableConf.getName().toUpperCase());
            Image iconImage = GardenConfigLoader.getInstance().getAtlasLoader().getSpriteImage(spriteEnum.getAtlasIndex());

            label.setIcon(new ImageIcon(iconImage.getScaledInstance(30, 30, 0)));

            VegetablePlantingAdapter vegetablePlantingAdapter = new VegetablePlantingAdapter(viewParent);
            label.addMouseListener(vegetablePlantingAdapter);
            add(label);
        }
    }
}
