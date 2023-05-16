package com.matheoauer.views.adapter;

import com.matheoauer.views.CaseView;

import java.awt.event.MouseAdapter;
import java.util.logging.Logger;

public class CaseMouseAdapter extends MouseAdapter {

    private final CaseView caseView;

    public CaseMouseAdapter(CaseView caseView) {
        this.caseView = caseView;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        Logger.getGlobal().info("Mouse clicked on case " + caseView.getI() + " " + caseView.getJ());
    }
}
