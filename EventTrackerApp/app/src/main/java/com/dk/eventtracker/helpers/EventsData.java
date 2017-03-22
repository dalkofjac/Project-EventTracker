package com.dk.eventtracker.helpers;

import com.dk.database.Event;

import java.util.List;

/**
 * Created by Dalibor on 21.3.2017..
 */

public class EventsData {

    public static void getHolidaysData(List<Event> eventList) {
        Event event = new Event(1, 1, "Božić", "25/12/2017");
        eventList.add(event);
        //event.save();

        event = new Event(2, 1, "Nova godina", "01/01/2018");
        eventList.add(event);
        //event.save();

        event = new Event(3, 1, "Uskrs", "16/04/2017");
        eventList.add(event);
        //event.save();
    }

    public static void getBirthdaysData(List<Event> eventList) {
        Event event = new Event(4, 2, "Mama", "25/11/2017");
        eventList.add(event);
        //event.save();

        event = new Event(5, 2, "Ja", "16/07/2017");
        eventList.add(event);
        //event.save();

        event = new Event(6, 2, "Tata", "05/06/2017");
        eventList.add(event);
        //event.save();
    }
}
