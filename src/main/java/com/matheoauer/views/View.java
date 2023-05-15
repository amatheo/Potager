package com.matheoauer.views;

import com.matheoauer.SpriteEnum;
import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.models.Case;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public class View extends JFrame implements Observer {


    private final int height;
    private final int width;

    private final CaseView[][] caseViews;
    private JComponent pan;

    public View() {
        this.height = GardenConfigLoader.getInstance().getGardenConfiguration().getHeight();
        this.width = GardenConfigLoader.getInstance().getGardenConfiguration().getWidth();
        this.caseViews = new CaseView[width][height];
        this.build();
        this.setVisible(true);
    }

    private void build() {

        this.setTitle("View");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pan = new JPanel(new GridLayout(width, height));

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Image icon = GardenConfigLoader.getInstance().getAtlasLoader().getSpriteImage(SpriteEnum.APPLE.getAtlasIndex());
                CaseView caseView = new CaseView(new ImageIcon(icon), i, j);
                caseViews[i][j] = caseView;
                pan.add(caseView);
            }
        }
        add(pan);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(arg instanceof Case c)) {
            Logger.getGlobal().warning("The object is not a case");
            return;
        }
        int x = c.getX();
        int y = c.getY();
    }
}
