package com.matheoauer.views;

import com.matheoauer.SpriteEnum;
import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.models.Case;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public class CaseView extends JLabel implements Observer {

    private final int i;
    private final int j;
    public JLabel label;
    private Image iconImage;
    private int alpha;

    public CaseView(Image image, int i, int j) {
        super("", JLabel.CENTER);
        this.iconImage = image;
        this.i = i;
        this.j = j;
        this.label = new JLabel();
        this.add(label);
        this.setOpaque(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(o instanceof Case caseModel)) {
            Logger.getGlobal().warning("The object is not a case");
            return;
        }
        if (caseModel.getVegetable() == null) {
            this.iconImage = null;
            updateIcon(0, 0);
        } else {
            String vegName = caseModel.getVegetable().getName();
            SpriteEnum spriteEnum = SpriteEnum.valueOf(vegName.toUpperCase());
            this.iconImage = GardenConfigLoader.getInstance().getAtlasLoader().getSpriteImage(spriteEnum.getAtlasIndex());
            updateIcon(caseModel.getVegetable().getGrowth(), caseModel.getVegetable().getDecay());
        }

        if (caseModel.getSoil() != null) {
            updateBackground(caseModel.getSoil().getHumidity());
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
        // Invert the humidity to have a brown color when the humidity is high
        humidity = 1 - humidity;
        int alpha = (int) (humidity * 255);
        Color color = new Color(139, 69, 19, alpha);

        // Blend the color with the background color white
        int red = (color.getRed());
        int green = (color.getGreen());
        int blue = (color.getBlue());
        color = new Color(red, green, blue, alpha);

        this.setBackground(color);
    }

    /**
     * Update the icon of the case. The more the vegetable is grown, the more the icon is big.
     *
     * @param growth the growth of the vegetable. Between 0 and 1.
     */
    private void updateIcon(float growth, float decay) {
        if (iconImage == null) {
            this.setIcon(null);
            return;
        }
        if (growth < 0 || growth > 1) {
            throw new IllegalArgumentException("The growth must be between 0 and 1");
        }
        if (growth == 0) {
            this.setIcon(null);
            return;
        }

        int height = (int) (growth * this.getHeight());
        int width = (int) (growth * this.getWidth());
        if (width <= 0 || height <= 0) {
            this.setIcon(null);
            return;
        }

        BufferedImage baseImage = new BufferedImage(iconImage.getWidth(null), iconImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = baseImage.createGraphics();
        g2d.drawImage(iconImage, 0, 0, null);
        g2d.dispose();

        for (int y = 0; y < baseImage.getHeight(); y++) {
            for (int x = 0; x < baseImage.getWidth(); x++) {
                int rgb = baseImage.getRGB(x, y);

                int alpha = (rgb >> 24) & 0xFF; // Composante de transparence
                int red = (rgb >> 16) & 0xFF; // Composante rouge
                int green = (rgb >> 8) & 0xFF; // Composante verte
                int blue = rgb & 0xFF; // Composante bleue

                // Mélange des composantes rouge et bleue pour créer du violet
                red = (int) (red * (1 - decay));
                blue = (int) (blue * (1 - decay));
                
                int newRGB = (alpha << 24) | (red << 16) | (green << 8) | blue;
                baseImage.setRGB(x, y, newRGB);
            }
        }

        Image filteredImage = baseImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        this.setIcon(new ImageIcon(filteredImage));
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

}
