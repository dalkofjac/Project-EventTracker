package com.dk.database;

import java.util.Date;

/**
 * Created by Dalibor on 21.3.2017..
 */

public class Event {
    int id;
    int type; //type 1 = holiday, type 2 = birthday, type 3 = other
    String name;
    String date;

    public Event() {
    }

    public Event(int id, int type, String name, String date) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
