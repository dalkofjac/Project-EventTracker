package com.dk.eventtracker.webservices;

import android.os.AsyncTask;

import com.dk.database.Event;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Dalibor on 21.4.2017..
 */

public class EventAdder extends AsyncTask<String,Void,Void> {
    final String queryId = "3";
    String userId;
    Event chosenEvent = new Event();

    public EventAdder(String userId, Event event) {
        this.userId = userId;
        this.chosenEvent = event;
    }

    private void addNewEvent(){
        String eventId = String.valueOf(chosenEvent.getId());
        String eventType = String.valueOf(chosenEvent.getType());
        String eventName = chosenEvent.getName();
        String eventDate = chosenEvent.getDate();
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("queryId", queryId)
                    .add("userId", userId)
                    .add("eventId", eventId)
                    .add("eventType", eventType)
                    .add("eventName", eventName)
                    .add("eventDate", eventDate)
                    .build();

            Request request = new Request.Builder()
                    .url("http://eventtracker.000webhostapp.com/eventsQuery.php")
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            addNewEvent();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
