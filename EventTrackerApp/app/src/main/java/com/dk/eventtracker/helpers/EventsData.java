package com.dk.eventtracker.helpers;

import com.dk.database.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Dalibor on 21.3.2017..
 */

public class EventsData {

    public static void getHolidaysData(List<Event> eventList) {
        Event event = new Event(1, 1, "Božić", "25/12");
        eventList.add(event);
        event.save();

        event = new Event(2, 1, "Nova godina", "01/01");
        eventList.add(event);
        event.save();

        event = new Event(3, 1, "Uskrs", "16/04");
        eventList.add(event);
        event.save();
        event = new Event(4, 1, "Sveta tri kralja", "06/01");
        eventList.add(event);
        event.save();
    }

    public static void getBirthdaysData(List<Event> eventList) {
        Event event = new Event(5, 2, "Mama", "25/11");
        eventList.add(event);
        event.save();

        event = new Event(6, 2, "Ja", "16/07");
        eventList.add(event);
        event.save();

        event = new Event(7, 2, "Tata", "07/06");
        eventList.add(event);
        event.save();

        event = new Event(8, 2, "Dajana", "22/10");
        eventList.add(event);
        event.save();

        event = new Event(9, 2, "Ronaldinho", "21/03");
        eventList.add(event);
        event.save();
    }
}
