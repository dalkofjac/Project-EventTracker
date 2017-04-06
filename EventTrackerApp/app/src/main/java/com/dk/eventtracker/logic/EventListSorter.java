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
        Calendar cal=Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        Date todayDate = convertStringToDate(getTodaysDate(), 1);
        for(int i=0; i<targetedEventList.size();i++){
            Event event = targetedEventList.get(i);
            Date eventDate = convertStringToDate(event.getDate()+"/"+currentYear, 1);

            if(eventDate.before(todayDate)){
                int newCurrentYear = currentYear+1;
                targetedEventList.get(i).setDate(event.getDate()+"/"+newCurrentYear);
            }
            else{
                targetedEventList.get(i).setDate(event.getDate()+"/"+currentYear);
            }
        }
    }
    public String getTodaysDate(){
        String tDate = "";
        Calendar cal = Calendar.getInstance();
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        int currentYear = cal.get(Calendar.YEAR);
        if(currentDay < 10){
            tDate = tDate + "0" + currentDay + "/";
        }
        else{
            tDate = tDate + currentDay + "/";
        }
        if(currentMonth < 10){
            tDate = tDate + "0" + currentMonth;
        }
        else{
            tDate = tDate + currentMonth;
        }
        tDate = tDate + "/" + currentYear;
        return tDate;
    }
}
