package com.dk.eventtracker.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.dk.eventtracker.R;
import com.dk.eventtracker.webservices.UserAdder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dalibor on 3.11.2017..
 */

public class RegisterFragment extends Fragment {
    @BindView(R.id.editText_reg_name)
    public EditText regName;

    @BindView(R.id.editText_reg_surname)
    public EditText regSurname;

    @BindView(R.id.editText_reg_email)
    public EditText regEmail;

    @BindView(R.id.editText_reg_username)
    public EditText regUsername;

    @BindView(R.id.editText_reg_password)
    public EditText regPassword;

    private String fragmentTitle;
    private String goodReg;
    private String badReg;
    private String userNameExists;
    private String errorMsg;
    private String serverResponse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_registration,container,false);
        ButterKnife.bind(this, view);

        fragmentTitle = getResources().getString(R.string.drawer_registration);
        goodReg = getResources().getString(R.string.toast_good_reg);
        badReg = getResources().getString(R.string.toast_bad_reg);
        userNameExists = getResources().getString(R.string.toast_user_exists_reg);
        errorMsg = getResources().getString(R.string.toast_error_reg);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(fragmentTitle);
    }
    @OnClick(R.id.button_register)
    public void onRegisterButtonClicked(){
        if(inputCheck() == true){
            sendData();
            if(serverResponse.equals("pass")){
                getActivity().onBackPressed();
                Toast.makeText(this.getActivity().getApplicationContext(),goodReg,Toast.LENGTH_LONG).show();
            }
            else if(serverResponse.equals("stop")){
                Toast.makeText(this.getActivity().getApplicationContext(),userNameExists,Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this.getActivity().getApplicationContext(),errorMsg,Toast.LENGTH_LONG).show();
            }
        }
        else{
            regName.setText("");
            regSurname.setText("");
            regEmail.setText("");
            regUsername.setText("");
            regPassword.setText("");
            Toast.makeText(this.getActivity().getApplicationContext(),badReg,Toast.LENGTH_LONG).show();
        }

    }
    private boolean inputCheck(){
        if(regName.getText().length()< 2
                || regSurname.getText().length()< 2
                || regEmail.getText().length() < 5
                || regUsername.getText().length() < 3
                || regPassword.getText().length() < 3){
            return false;
        }
        else if(regName.getText().length()> 50
                || regSurname.getText().length()> 50
                || regEmail.getText().length()> 50
                || regUsername.getText().length()> 20
                || regPassword.getText().length()> 20){
            return false;
        }
        else if(checkForIllegalChars()==false){
            return false;
        }
        else {
            return true;
        }
    }
    private void sendData(){
        UserAdder userAdder = new UserAdder(regName.getText().toString(),
                regSurname.getText().toString(), regEmail.getText().toString(),
                regUsername.getText().toString(), regPassword.getText().toString());
        try{
            serverResponse = userAdder.execute().get().toString();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private boolean checkForIllegalChars(){
        String[] badChars = new String[]{" ", "/", "#", "="};
        for(int i=0;i<badChars.length;i++){
            if(regUsername.getText().toString().contains(badChars[i])|| regPassword.getText().toString().contains(badChars[i])||
                    regName.getText().toString().contains(badChars[i])|| regSurname.getText().toString().contains(badChars[i])||
                    regEmail.getText().toString().contains(badChars[i])){
                return false;
            }
        }
        if(!regEmail.getText().toString().contains("@")){
            return false;
        }
        return true;
    }
}
