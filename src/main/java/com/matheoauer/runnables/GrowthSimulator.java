package com.matheoauer.runnables;

import com.matheoauer.models.Case;

public class GrowthSimulator extends Simulator {

    @Override
    public void run() {
        for (int i = 0; i < this.garden.getWidth(); i++) {
            for (int j = 0; j < this.garden.getHeight(); j++) {
                if (this.garden.getCase(i, j).getVegetable() != null) {

                    Case caseModel = this.garden.getCase(i, j);

                    long milisDelta = Scheduler.getInstance().getDeltaTime();
                    this.garden.getCase(i, j).getVegetable().grow(0.1f);
                }
            }
        }
    }
}
