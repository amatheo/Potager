package com.matheoauer.views;

import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.config.Vegetable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FooterView extends JPanel {
    public FooterView() {
        List<Vegetable> allVegetables = GardenConfigLoader.getInstance().getVegetables();
        this.setLayout(new GridLayout(1, allVegetables.size()));
        setSize(new Dimension(800, 100));

        for (Vegetable vegetable : allVegetables) {
            JLabel label = new JLabel(vegetable.getName());
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
