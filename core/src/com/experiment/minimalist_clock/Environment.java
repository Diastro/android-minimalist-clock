package com.experiment.minimalist_clock;

import java.util.Calendar;

/**
 * Created by David on 1/7/2015.
 */
public class Environment {
    private Calendar calendar;
    private int hours;
    private int minutes;
    private int seconds;
    private int miliseconds;

    public Environment(){
    }

    public void update(){
        this.calendar = Calendar.getInstance();
        this.hours = this.calendar.get(Calendar.HOUR);
        this.minutes = this.calendar.get(Calendar.MINUTE);
        this.seconds = this.calendar.get(Calendar.SECOND);
        this.miliseconds = this.calendar.get(Calendar.MILLISECOND);
    }

    public int getHours(){
        return this.hours;
    }

    public int getMinutes(){
        return this.minutes;
    }

    public int getSeconds(){
        return this.seconds;
    }

    public int getMiliseconds(){
        return this.miliseconds;
    }
}
