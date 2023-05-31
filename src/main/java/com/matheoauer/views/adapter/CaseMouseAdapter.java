package com.matheoauer.views.adapter;

import com.matheoauer.models.Case;
import com.matheoauer.views.CaseView;
import com.matheoauer.views.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CaseMouseAdapter extends MouseAdapter {

    private final CaseView caseView;
    private final View viewParent;

    public CaseMouseAdapter(CaseView caseView, View viewParent) {
        this.caseView = caseView;
        this.viewParent = viewParent;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        if (this.viewParent.getVegetableSelected() != null) {
            Case myCase = this.viewParent.getGarden().getCase(this.caseView.getI(), this.caseView.getJ());
            myCase.setVegetable(this.viewParent.getVegetableSelected());
            this.viewParent.setVegetableSelected(null);
        }
    }
}
