package com.dk.eventtracker.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.dk.database.Event;
import com.dk.eventtracker.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dalibor on 27.3.2017..
 */

public class AddNewEventFragment extends Fragment {
    private int eventType;

    @BindView(R.id.editText_add_event_name)
    EditText newEventName;

    @BindView(R.id.editText_add_event_date)
    EditText newEventDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_add_new_event,container,false);
        ButterKnife.bind(this, view);

        eventType = getArguments().getInt("EVENT_TYPE");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(eventType == 1) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Dodaj blagdan");
        }
        else if (eventType == 2) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Dodaj rođendan");
        }
        else{
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Dodaj događaj");
        }
    }

    @OnClick(R.id.button_add_event)
    public void AddEventButtonPressed(){
        String eventName = newEventName.getText().toString();
        String eventDate = newEventDate.getText().toString();

        try {
            if (eventType == 1 && dateCheck(eventDate) == true && availableNameCheck(eventName) == true) {
                Event event = new Event(1, eventName, eventDate);
                event.save();
                getActivity().onBackPressed();

                Toast.makeText(getActivity(), "Uspješno dodan blagdan", Toast.LENGTH_SHORT).show();
            }
            if (eventType == 2 && dateCheck(eventDate) == true && availableNameCheck(eventName) == true) {
                Event event = new Event(2, eventName, eventDate);
                event.save();
                getActivity().onBackPressed();

                Toast.makeText(getActivity(), "Uspješno dodan rođendan", Toast.LENGTH_SHORT).show();
            }
            if (eventType >= 3 && dateCheck(eventDate) == true && availableNameCheck(eventName) == true) {
                Event event = new Event(3, eventName, eventDate);
                event.save();
                getActivity().onBackPressed();

                Toast.makeText(getActivity(), "Uspješno dodan događaj", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getActivity(), "Nevaljan unos!", Toast.LENGTH_SHORT).show();
        }
        // #TODO Napraviti odvojene editTextove kod upisa datuma
    }
    private boolean dateCheck(String date){
        String days = "";
        String months = "";
        days = date.substring(0, date.indexOf("/"));
        months = date.substring(date.indexOf("/")+1);

        if(date.length() > 5){
            Toast.makeText(getActivity(), "Krivi format datuma! (dd/mm)", Toast.LENGTH_LONG).show();
            return false;
        }

        if(Integer.parseInt(days)>31 || Integer.parseInt(months)>12){
            Toast.makeText(getActivity(), "Nepostojeći datum!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private boolean availableNameCheck(String name){
        List<Event> eventList = Event.getAll();
        for(int i=0;i<eventList.size(); i++){
            if(eventList.get(i).getName().matches(name)){
                Toast.makeText(getActivity(), "Naziv već postoji!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
