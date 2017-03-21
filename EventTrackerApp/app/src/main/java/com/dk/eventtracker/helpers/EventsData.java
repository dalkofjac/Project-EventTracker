package com.dk.eventtracker.helpers;

import com.dk.database.Event;

import java.util.List;

/**
 * Created by Dalibor on 21.3.2017..
 */

public class EventsData {

    public static void getBirthdaysData(List<Event> eventList) {
        Event event = new Event(1, "Mama", "25/11/2017");
        eventList.add(event);
        //event.save();

        event = new Event(2, "Ja", "16/07/2017");
        eventList.add(event);
        //event.save();

        event = new Event(3, "Tata", "05/06/2017");
        eventList.add(event);
        //event.save();
    }
}
