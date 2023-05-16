package com.matheoauer.views;

import com.matheoauer.models.Case;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public class CaseView extends JPanel implements Observer {

    private final Image iconImage;
    private final int i;
    private final int j;
    public JLabel label;

    public CaseView(Image image, int i, int j) {
        super();
        this.iconImage = image;
        this.i = j;
        this.j = j;
        this.label = new JLabel();
        this.add(label);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(o instanceof Case caseModel)) {
            Logger.getGlobal().warning("The object is not a case");
            return;
        }

        if (caseModel.getSoil() != null) {
            updateBackground(caseModel.getSoil().getHumidity());
        }
        if (caseModel.getVegetable() != null) {
            updateIcon(caseModel.getVegetable().getGrowth());
        }
    }

    /**
     * Update the background color of the case.
     * The more the soil is humid, the more the color is brown.
     *
     * @param humidity the case model
     */
    private void updateBackground(float humidity) {
        if (humidity < 0 || humidity > 1) {
            throw new IllegalArgumentException("The humidity must be between 0 and 1");
        }
        int alpha = (int) (humidity * 255);
        Color color = new Color(139, 69, 19, alpha);
        //this.setBackground(color);
    }

    /**
     * Update the icon of the case. The more the vegetable is grown, the more the icon is big.
     *
     * @param growth the growth of the vegetable. Between 0 and 1.
     */
    private void updateIcon(float growth) {
        if (growth < 0 || growth > 1) {
            throw new IllegalArgumentException("The growth must be between 0 and 1");
        }
        if (growth == 0) {
            this.label.setIcon(null);
            return;
        }

        int height = (int) (growth * this.getHeight());
        int width = (int) (growth * this.getWidth());
        if (width <= 0 || height <= 0) {
            Logger.getGlobal().warning("The width or the height is too small to be displayed, 0 instead");
            this.label.setIcon(null);
            return;
        }
        this.label.setIcon(new ImageIcon(iconImage.getScaledInstance(width, height, 0)));
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
