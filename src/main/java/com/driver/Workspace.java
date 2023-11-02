package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int count = !calendar.isEmpty() ? 1 : 0;
        Collections.sort(calendar);
        LocalTime pStartTime = calendar.get(0).getStartTime();//previous start time
        LocalTime pEndTime = calendar.get(0).getEndTime();//previous end time
        for(int i = 1; i<calendar.size();++i){
            LocalTime cStartTime = calendar.get(i).getStartTime();//current start time
            LocalTime cEndTime = calendar.get(i).getEndTime();//current end time
            //when overlapping
            if(pEndTime.compareTo(cStartTime)>=0){
                if(cEndTime.compareTo(pEndTime)<0){
                    pStartTime = cStartTime;
                    pEndTime = cEndTime;
                }
            }
            else{
                //if not overlap then count++
                pStartTime = cStartTime;
                pEndTime = cEndTime;
                count++;
            }
        }
        return count;
    }
}
