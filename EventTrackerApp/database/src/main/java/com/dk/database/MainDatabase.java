package com.dk.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Dalibor on 23.3.2017..
 */

@Database(name = MainDatabase.NAME, version = MainDatabase.VERSION)
public class MainDatabase {
    public static final String NAME = "EventTrackerDatabase";
    public static final int VERSION = 1;
}
