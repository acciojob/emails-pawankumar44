package com.driver;

import java.util.ArrayList;
import java.util.*;
import java.util.HashMap;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    TreeMap<Date,Mail> inbox ;//Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    TreeMap<Date,Mail> trash ;//Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        inbox = new TreeMap<>();
        trash = new TreeMap<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inbox.size()>=this.inboxCapacity){
            trash.put(inbox.firstKey(),inbox.get(inbox.firstKey()));
            inbox.remove(inbox.firstKey());
        }
        inbox.put(date,new Mail(date,sender,message));
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        boolean deleted = false;
        for(Date date : inbox.keySet()){
            Mail mail = inbox.get(date);
            if(mail.message.equals(message)){
                inbox.remove(date);
                deleted = true;
            }
        }
        if(!deleted){
            for(Date date : trash.keySet()){
                Mail mail = trash.get(date);
                if(mail.message.equals(message)) trash.remove(date);
            }
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if (inbox.isEmpty()) return null;
        return inbox.lastEntry().getValue().message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if (inbox.isEmpty()) return null;
        return inbox.firstEntry().getValue().message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for(Date date : inbox.keySet()){
            int startComp = date.compareTo(start);
            int endComp = date.compareTo(end);
            if(startComp == 0 || endComp == 0 || (startComp>0 && endComp<0)) count++;
        }
        return  count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
