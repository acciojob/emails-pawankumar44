package com.driver;

import java.time.LocalTime;

public class Meeting implements Comparable<Meeting> {
    private LocalTime startTime;
    private LocalTime endTime;

    public Meeting(LocalTime startTime, LocalTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int compareTo(Meeting other){
        return this.startTime.compareTo(other.startTime);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
