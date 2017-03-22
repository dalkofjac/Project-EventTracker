package com.dk.eventtracker.logic;

import com.dk.database.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Dalibor on 22.3.2017..
 */

public class EventListSorter {
    private Date convertStringToDate(String sDate, int type){
        SimpleDateFormat dateFormat;
        if(type == 1) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        }
        else{
            dateFormat = new SimpleDateFormat("dd/MM");
        }
        Date convertedDate = new Date();
        try {
            return convertedDate = dateFormat.parse(sDate);
        }catch(ParseException e){
            return null;
        }
    }
    public void sortTheList(List<Event> eventList){
        Event eventHigh;
        Event eventLow;
        for(int i=0; i<eventList.size();i++){
            eventHigh = eventList.get(i);
            Date eventDateHigh = convertStringToDate(eventHigh.getDate(), 1);
            for(int j=0;j<i;j++){
                eventLow = eventList.get(j);
                Date eventDateLow = convertStringToDate(eventLow.getDate(), 1);
                if(eventDateLow.after(eventDateHigh)){
                    Collections.swap(eventList, i, j);
                }
            }
        }
    }
    public void attachYears(List<Event> targetedEventList){
        Date todayDate = new Date();
        Calendar cal=Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        for(int i=0; i<targetedEventList.size();i++){
            Event event = targetedEventList.get(i);
            Date eventDate = convertStringToDate(event.getDate(), 2);
            eventDate.setYear(currentYear-1900);
            if(eventDate.before(todayDate)){
                int newCurrentYear= currentYear+1;
                targetedEventList.get(i).setDate(event.getDate()+"/"+newCurrentYear);
            }
            else{
                targetedEventList.get(i).setDate(event.getDate()+"/"+currentYear);
            }
        }
    }
}
