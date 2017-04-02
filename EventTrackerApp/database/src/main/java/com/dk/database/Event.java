package com.dk.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by Dalibor on 21.3.2017..
 */

@Table(database = MainDatabase.class)
public class Event extends BaseModel{
    @PrimaryKey(autoincrement=true)
    @Column int id;
    @Column int type; //type 1 = holiday, type 2 = birthday, type 3 = other
    @Column String name;
    @Column String date;

    public Event() {
    }

    public Event(int type, String name, String date) {
        this.type = type;
        this.name = name;
        this.date = date;
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

    public static List<Event> getAll(){
        return SQLite.select().from(Event.class).queryList();
    }

    public static Event getSpecific(int id) {
        return SQLite.select().from(Event.class).where(Event_Table.id.eq(id)).querySingle();
    }

    public static Event getSpecificByName(String name){
        return SQLite.select().from(Event.class).where(Event_Table.name.eq(name)).querySingle();
    }

    public static List<Event> getAllHolidays(){
        return SQLite.select().from(Event.class).where(Event_Table.type.eq(1)).queryList();
    }

    public static List<Event> getAllBirthdays(){
        return SQLite.select().from(Event.class).where(Event_Table.type.eq(2)).queryList();
    }
}
