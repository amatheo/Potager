package com.matheoauer.views.adapter;

import com.matheoauer.models.Case;
import com.matheoauer.views.CaseView;
import com.matheoauer.views.View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;

public class CaseMouseAdapter extends MouseAdapter {

    private final CaseView caseView;
    private final View viewParent;

    public CaseMouseAdapter(CaseView caseView, View viewParent) {
        this.caseView = caseView;
        this.viewParent = viewParent;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        Case myCase = this.viewParent.getGarden().getCase(this.caseView.getI(), this.caseView.getJ());
        if (this.viewParent.getVegetableSelected() != null) {
            myCase.setVegetable(this.viewParent.getVegetableSelected());
            this.viewParent.setVegetableSelected(null);
            try {
                URL resource = getClass().getClassLoader().getResource("plant_sound.wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(resource.toURI()));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (myCase.getVegetable() != null && myCase.getVegetable().getGrowth() == 1) {
            viewParent.getGarden().getInventory().addToInventory(myCase.getVegetable().getName());
            try {
                URL resource = getClass().getClassLoader().getResource("claimed_vegetable_sound.wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(resource.toURI()));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            myCase.setVegetable(null);
        }
    }
}
