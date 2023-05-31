package com.matheoauer.runnables;

import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.models.Garden;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class Scheduler extends Observable implements Runnable {

    private static Scheduler instance;
    private final Date oldDate;
    private final long sleepTimeMs;
    private final float daysToSimulate;
    System.Logger LOGGER = System.getLogger("Scheduler");
    List<Simulator> tasks;
    private Date date;
    private Garden garden;

    private Scheduler(Date startDate, long sleepTimeMs, float daysToSimulate) {
        // Warning if the daysToSimulate is inferior to an hour
        if (daysToSimulate < 1f / 24f) {
            LOGGER.log(System.Logger.Level.WARNING, "The simulation is too short, you should increase the daysToSimulate");
        }
        this.date = startDate;
        this.oldDate = startDate;
        this.tasks = new ArrayList<>();
        this.sleepTimeMs = sleepTimeMs;
        this.daysToSimulate = daysToSimulate;
    }

    public static Scheduler getInstance() {
        if (instance == null) {
            long sleepTimeMs = (long) GardenConfigLoader.getInstance().getGardenConfiguration().getSimulation().getTime_sleep();
            float daysToSimulate = GardenConfigLoader.getInstance().getGardenConfiguration().getSimulation().getDaysToSimulate();
            instance = new Scheduler(new Date(), sleepTimeMs, daysToSimulate);
        }
        return instance;
    }

    public void addTask(Simulator task) {
        task.oldDate = date;
        task.newDate = date;
        task.garden = garden;
        tasks.add(task);
    }

    public void removeTask(Simulator task) {
        task.garden = null;
        tasks.remove(task);
    }

    public Date getDate() {
        return date;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }


    @Override
    public void run() {
        while (true) {
            // Update timings
            oldDate.setTime(date.getTime());

            // Calculate deltatime
            long futureTime = (long) (sleepTimeMs + (daysToSimulate * 24 * 60 * 60 * 1000));
            date = new Date((oldDate.getTime() + futureTime));
            for (Simulator task : tasks) {
                task.simulate(date);
            }
            this.setChanged();
            this.notifyObservers();
            try {
                Thread.sleep(sleepTimeMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public long getDeltaTime() {
        return date.getTime() - oldDate.getTime();
    }
}
