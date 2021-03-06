package com.dk.eventtracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.dk.eventtracker.webservices.LoginChecker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    String login_name, login_pass;

    @BindView(R.id.editText_username)
    EditText username;

    @BindView(R.id.editText_password)
    EditText password;

    private String loginResponseGood;
    private String loginResponseBad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginResponseGood = getResources().getString(R.string.login_good);
        loginResponseBad = getResources().getString(R.string.login_bad);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.button_login)
    public void onLoginButtonClicked(){
        String response = "";
        login_name = username.getText().toString();
        login_pass = password.getText().toString();

        LoginChecker loginChecker = new LoginChecker(login_name, login_pass);
        try{
            response = loginChecker.execute().get().toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        if(response.matches("")){
            Toast.makeText(this, loginResponseBad, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, loginResponseGood, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,UserActivity.class);
            intent.putExtra("USER_ID", response);
            this.startActivity(intent);
            this.finish();
        }
    }
}
