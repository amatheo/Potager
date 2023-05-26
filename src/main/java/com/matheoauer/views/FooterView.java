package com.matheoauer.views;

import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.config.VegetableConf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FooterView extends JPanel {
    public FooterView() {
        List<VegetableConf> allVegetableConfs = GardenConfigLoader.getInstance().getVegetables();
        this.setLayout(new GridLayout(1, allVegetableConfs.size()));
        setSize(new Dimension(800, 100));

        for (VegetableConf vegetableConf : allVegetableConfs) {
            JLabel label = new JLabel(vegetableConf.getName());
            label.addMouseListener(new DragAndDropListener());
            add(label);
        }

    }


    private class DragAndDropListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            System.out.println(((JLabel) e.getSource()).getText());
        }

        public void mouseReleased(MouseEvent e) {
            Container container = e.getComponent().getParent();
            Component droppedLabel = container.getComponentAt(e.getX(), e.getY());

            if (droppedLabel instanceof JLabel targetLabel) {
                targetLabel.setBackground(Color.GREEN);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
        }
    }

}
