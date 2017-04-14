package com.dk.eventtracker.webservices;

import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Dalibor on 14.4.2017..
 */

public class LoginChecker extends AsyncTask<String,Void,String> {
    String username;
    String password;

    String msg;

    public LoginChecker(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String checkLoginData(){
        String res = "";
        try {

            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("username", username)
                    .add("password", password)
                    .build();

            Request request = new Request.Builder()
                    .url("http://eventtracker.000webhostapp.com/loginCheck.php")
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
            msg = checkLoginData();
        }catch (Exception e){

        }
        return msg;
    }
}
