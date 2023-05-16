package com.matheoauer.views;

import com.matheoauer.SpriteEnum;
import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.models.Case;
import com.matheoauer.models.Garden;
import com.matheoauer.views.adapter.CaseMouseAdapter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {


    private final int height;
    private final int width;

    private final CaseView[][] caseViews;

    public View(Garden garden) {
        this.height = GardenConfigLoader.getInstance().getGardenConfiguration().getHeight();
        this.width = GardenConfigLoader.getInstance().getGardenConfiguration().getWidth();
        this.caseViews = new CaseView[width][height];
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
        this.build(garden);
    }

    private void build(Garden garden) {

        this.setTitle("View");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent pan = new JPanel(new GridLayout(width, height));

        Border blackline = BorderFactory.createLineBorder(Color.black, 1);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Case caseModel = garden.getCase(i, j);

                Image iconImage = GardenConfigLoader.getInstance().getAtlasLoader().getSpriteImage(SpriteEnum.APPLE.getAtlasIndex());
                CaseView caseView = new CaseView(iconImage, i, j);
                caseModel.addObserver(caseView);

                caseView.addMouseListener(new CaseMouseAdapter(caseView));
                caseView.setBorder(blackline);

                caseViews[i][j] = caseView;
                pan.add(caseView);
            }
        }
        pan.setBorder(blackline);
        add(pan);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
