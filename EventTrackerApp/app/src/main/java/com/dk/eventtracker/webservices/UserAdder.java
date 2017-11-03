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
 * Created by Dalibor on 3.11.2017..
 */

public class UserAdder extends AsyncTask<String, Void, Void> {
    final String queryId = "#";
    String regName;
    String regSurame;
    String regEmail;
    String regUsername;
    String regPassword;

    public UserAdder(String regName, String regSurame, String regEmail, String regUsername, String regPassword) {
        this.regName = regName;
        this.regSurame = regSurame;
        this.regEmail = regEmail;
        this.regUsername = regUsername;
        this.regPassword = regPassword;
    }

    private void addNewUser(){
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("queryId", queryId)
                    .add("regName", regName)
                    .add("regSurname", regSurame)
                    .add("regEmail", regEmail)
                    .add("regUsername", regUsername)
                    .add("regPassword", regPassword)
                    .build();

            Request request = new Request.Builder()
                    .url("http://eventtracker.000webhostapp.com/userRegistration.php")
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
            addNewUser();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
