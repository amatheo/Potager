package com.matheoauer.runnables;

import com.matheoauer.config.VegetableConf;
import com.matheoauer.models.Case;

public class GrowthSimulator extends Simulator {

    @Override
    public void run() {
        for (int i = 0; i < this.garden.getWidth(); i++) {
            for (int j = 0; j < this.garden.getHeight(); j++) {

                if (this.garden.getCase(i, j) == null || this.garden.getCase(i, j).getVegetable() == null) {
                    continue;
                }
                Case caseModel = this.garden.getCase(i, j);
                VegetableConf vegetableConf = caseModel.getVegetable().getVegetableConf();

                // The grow rate is for a day (86400000 milliseconds)
                double growrate = vegetableConf.getGrowRate();
                long milisDelta = Scheduler.getInstance().getDeltaTime();

                double daysDelta = milisDelta / 86400000.0;
                double grow = growrate * daysDelta;
                caseModel.getVegetable().grow((float) grow);
            }
        }
    }
}
