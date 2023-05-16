package com.matheoauer.config.sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AtlasLoader {

    private final List<Image> spriteArray;
    private final int spriteWidth;
    private final int spriteHeight;
    private final float scale;

    public AtlasLoader(InputStream atlasIs, int spriteWidth, int spriteHeight, float scale) {
        spriteArray = new ArrayList<>();
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.scale = scale;
        this.loadSpriteArray(atlasIs);
    }

    public void loadSpriteArray(InputStream atlasIs) {
        try {
            BufferedImage image = ImageIO.read(atlasIs); // chargement de l'image globale

            int width = image.getWidth();
            int height = image.getHeight();

            // on découpe l'image globale en sous-images
            for (int y = 0; y < height; y += spriteHeight) {
                for (int x = 0; x < width; x += spriteWidth) {
                    BufferedImage subImage = image.getSubimage(x, y, spriteWidth, spriteHeight);
                    Image scaledInstance = subImage.getScaledInstance((int) (spriteWidth*scale), (int) (spriteHeight*scale), 0);
                    spriteArray.add(scaledInstance);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Image getSpriteImage(int index) {
        return spriteArray.get(index);
    }
}
