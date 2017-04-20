package com.dk.eventtracker.webservices;

import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Dalibor on 20.4.2017..
 */

public class ReceiveEventData extends AsyncTask<String,Void,String> {
    final String queryId = "1";
    String userId;
    String answear;

    public ReceiveEventData(String userId) { this.userId = userId; }

    private String getEventsInfo(){
        String res = "";
        try {

            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("queryId", queryId)
                    .add("userId", userId)
                    .build();

            Request request = new Request.Builder()
                    .url("http://eventtracker.000webhostapp.com/eventsQuery.php")
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
            answear = getEventsInfo();
        }catch (Exception e){
            e.printStackTrace();
        }
        return answear;
    }
}
