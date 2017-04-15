package com.dk.eventtracker.webservices;

import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


import java.io.IOException;

/**
 * Created by Dalibor on 15.4.2017..
 */

public class ReceiveUserData extends AsyncTask<String,Void,String> {
    String userId;
    String answear;

    public ReceiveUserData(String userId) {
        this.userId = userId;
    }

    private String getUserInfo(){
        String res = "";
        try {

            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("userId", userId)
                    .build();

            Request request = new Request.Builder()
                    .url("http://eventtracker.000webhostapp.com/userData.php")
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            res = response.body().string();

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            answear = getUserInfo();
        }catch (Exception e){
            e.printStackTrace();
        }
        return answear;
    }
}
