package com.matheoauer.runnables;

import com.matheoauer.config.GardenConfigLoader;
import com.matheoauer.models.Garden;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class Scheduler extends Observable implements Runnable {

    private static Scheduler instance;
    private final Date date;
    private final long sleepTimeMs;
    System.Logger LOGGER = System.getLogger("Scheduler");
    List<Simulator> tasks;
    private Garden garden;
    private int timeMulti;

    private Scheduler(Date startDate, long sleepTimeMs, int timeMulti) {
        this.date = startDate;
        this.tasks = new ArrayList<>();
        this.sleepTimeMs = sleepTimeMs;
        this.timeMulti = timeMulti;
    }

    public static Scheduler getInstance() {
        if (instance == null) {
            long sleepTimeMs = (long) GardenConfigLoader.getInstance().getGardenConfiguration().getSimulation().getTime_sleep();
            int timeMulti = GardenConfigLoader.getInstance().getGardenConfiguration().getSimulation().getTime_multiplier();
            instance = new Scheduler(new Date(), sleepTimeMs, timeMulti);
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

    public void setTimeMulti(int timeMulti) {
        this.timeMulti = timeMulti;
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
            String info = "Scheduler tick !\n";
            info += "Simulation Date : " + date.toString() + "\n";
            info += "Time multiplier : " + timeMulti + "\n";
            info += "Tasks : " + tasks.size() + "\n";

            LOGGER.log(System.Logger.Level.INFO, info);
            for (Simulator task : tasks) {
                task.simulate(date);
            }

            try {
                Thread.sleep(sleepTimeMs);
                date.setTime(date.getTime() + (sleepTimeMs * timeMulti));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
