package com.matheoauer.views;

import com.matheoauer.SpriteEnum;
import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.config.VegetableConf;
import com.matheoauer.models.Vegetable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FooterView extends JPanel {

    private View viewParent;

    public FooterView(View viewParent) {
        this.viewParent = viewParent;
        List<VegetableConf> allVegetableConfs = GardenConfigLoader.getInstance().getVegetables();
        this.setLayout(new GridLayout(1, allVegetableConfs.size()));

        for (VegetableConf vegetableConf : allVegetableConfs) {
            JLabel label = new JLabel(vegetableConf.getName());
            SpriteEnum spriteEnum = SpriteEnum.valueOf(vegetableConf.getName().toUpperCase());
            Image iconImage = GardenConfigLoader.getInstance().getAtlasLoader().getSpriteImage(spriteEnum.getAtlasIndex());
            label.setIcon(new ImageIcon(iconImage.getScaledInstance(30, 30, 0)));
            label.addMouseListener(new DragAndDropListener());
            add(label);
        }
    }


    private class DragAndDropListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            System.out.println(((JLabel) e.getSource()).getText());
            Vegetable vegetable = new Vegetable(((JLabel) e.getSource()).getText());
            viewParent.setVegetableSelected(vegetable);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
        }
    }

}
