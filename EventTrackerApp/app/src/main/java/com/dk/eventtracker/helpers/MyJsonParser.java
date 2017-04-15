package com.dk.eventtracker.helpers;

import com.dk.database.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dalibor on 15.4.2017..
 */

public class MyJsonParser {
    public static User parseUserInfo(String jsonString){
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
}
