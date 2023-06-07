package com.matheoauer.views;

import com.matheoauer.Main;
import com.matheoauer.SpriteEnum;
import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.models.Case;
import com.matheoauer.models.Garden;
import com.matheoauer.models.Vegetable;
import com.matheoauer.runnables.Scheduler;
import com.matheoauer.views.adapter.CaseMouseAdapter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {


    private final int height;
    private final int width;
    private final CaseView[][] caseViews;
    private final Garden garden;
    private Vegetable vegetableSelected;

    public View(Garden garden) {
        this.garden = garden;
        this.height = GardenConfigLoader.getInstance().getGardenConfiguration().getHeight();
        this.width = GardenConfigLoader.getInstance().getGardenConfiguration().getWidth();
        this.caseViews = new CaseView[width][height];
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                saveData();
            }
        });
    }

    public void build(Garden garden) {

        this.setTitle("View");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            URL resource = getClass().getClassLoader().getResource("ambiant_music.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(resource.toURI()));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JComponent pan = new JPanel(new GridLayout(width, height));

        Border blackline = BorderFactory.createLineBorder(Color.black, 1);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Case caseModel = garden.getCase(i, j);
                Image iconImage = null;
                if (caseModel.getVegetable() != null) {
                    SpriteEnum spriteEnum = SpriteEnum.valueOf(caseModel.getVegetable().getName().toUpperCase());
                    iconImage = GardenConfigLoader.getInstance().getAtlasLoader().getSpriteImage(spriteEnum.getAtlasIndex());
                }
                CaseView caseView = new CaseView(iconImage, i, j);
                caseModel.addObserver(caseView);

                caseView.addMouseListener(new CaseMouseAdapter(caseView, this));
                caseView.setBorder(blackline);

                caseViews[i][j] = caseView;
                pan.add(caseView);
                this.caseViews[i][j].update(caseModel, null);
            }
        }

        InventoryView inventoryView = new InventoryView(garden.getInventory().getInventory());
        garden.getInventory().addObserver(inventoryView);
        pan.setBorder(blackline);
        add(pan);
        add(new FooterView(this), BorderLayout.SOUTH);
        add(inventoryView, BorderLayout.EAST);
    }

    public Vegetable getVegetableSelected() {
        return vegetableSelected;
    }

    public void setVegetableSelected(Vegetable vegetableSelected) {
        this.vegetableSelected = vegetableSelected;
    }

    public Garden getGarden() {
        return garden;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Scheduler scheduler) {
            Date date = scheduler.getDate();
            setTitle("Simulation Date - " + date);
        }
    }

    private void saveData() {
        try {
            // Création d'un flux d'entrée depuis le fichier
            FileOutputStream fichierSortie = new FileOutputStream(Main.pathUrl);
            ObjectOutputStream objetSortie = new ObjectOutputStream(fichierSortie);

            // Écriture de la HashMap sérialisée dans le fichier
            objetSortie.writeObject(this.garden);

            // Fermeture des flux
            objetSortie.close();
            fichierSortie.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
