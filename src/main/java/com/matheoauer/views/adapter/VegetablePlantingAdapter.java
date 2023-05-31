package com.matheoauer.views.adapter;

import com.matheoauer.models.Vegetable;
import com.matheoauer.views.View;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VegetablePlantingAdapter extends MouseAdapter {

    private final View view;

    public VegetablePlantingAdapter(View viewParent) {
        this.view = viewParent;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        Vegetable vegetable = new Vegetable(((JLabel) e.getSource()).getText());
        view.setVegetableSelected(vegetable);
    }
}
