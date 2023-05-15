package com.matheoauer.views;

import javax.swing.*;

public class CaseView extends JPanel {

    private final JLabel label;
    private final ImageIcon icon;
    private final int x;
    private final int y;

    public CaseView(ImageIcon icon, int x, int y) {
        super();
        this.icon = icon;
        this.x = x;
        this.y = y;
        this.label = new JLabel(icon);
        this.add(label);

    }
}
