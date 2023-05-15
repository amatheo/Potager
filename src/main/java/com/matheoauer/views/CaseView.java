package com.matheoauer.views;

import javax.swing.*;

public class CaseView extends JPanel {

    private JLabel label;
    private ImageIcon icon;
    private int x;
    private int y;

    public CaseView(ImageIcon icon, int x, int y) {
        super();
        this.icon = icon;
        this.x = x;
        this.y = y;
        this.label = new JLabel(icon);
        this.add(label);

    }
}
