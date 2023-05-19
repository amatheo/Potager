package com.matheoauer.runnables;

import com.matheoauer.models.Garden;

import java.util.Date;

public abstract class Simulator implements Runnable {
    Date oldDate;
    Date newDate;
    Garden garden;

    public Simulator() {
    }

    public void simulate(Date date) {
        this.oldDate = this.newDate;
        this.newDate = date;
        this.run();
    }
}
