package com.dk.eventtracker.helpers;

import com.dk.database.Event;
import com.dk.database.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dalibor on 15.4.2017..
 */

public class MyJsonParser {
    public static User ParseUserInfo(String jsonString){
        User currentUser = new User();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("user");
            JSONObject JO = jsonArray.getJSONObject(0);
            currentUser.setId(Integer.parseInt(JO.getString("id")));
            currentUser.setName(JO.getString("name"));
            currentUser.setSurname(JO.getString("surname"));
            currentUser.setEmail(JO.getString("email"));
        }catch(JSONException jse){
            jse.printStackTrace();
        }
        return currentUser;
    }
    public static List<Event> ParseEventsInfo(String jsonString){
        List<Event> eventsList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("event");
            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject JO = jsonArray.getJSONObject(i);
                Event currentEvent = new Event();
                currentEvent.setId(Integer.parseInt(JO.getString("id")));
                currentEvent.setType(Integer.parseInt(JO.getString("type")));
                currentEvent.setName(JO.getString("name"));
                currentEvent.setDate(JO.getString("date"));
                eventsList.add(currentEvent);
            }
        }catch(JSONException jse){
            jse.printStackTrace();
        }
        return eventsList;
    }
}
