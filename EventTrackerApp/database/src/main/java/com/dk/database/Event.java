package com.dk.database;

import java.util.Date;

/**
 * Created by Dalibor on 21.3.2017..
 */

public class Event {
    int id;
    String name;
    String date;

    public Event() {
    }

    public Event(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
