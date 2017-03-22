package com.dk.eventtracker.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dk.eventtracker.R;
import com.dk.eventtracker.logic.TimerSetter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dalibor on 22.3.2017..
 */

public class BirthdayDetailsFragment extends Fragment {
    @BindView(R.id.textView_bd_name)
    TextView textName;

    @BindView(R.id.textView_timer_bd)
    TextView textTimer;

    TimerSetter timerSetter;

    String eventName;
    String eventDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_birthday_details,container,false);
        ButterKnife.bind(this, view);
        timerSetter = new TimerSetter();
        eventName = getArguments().getString("EVENT_NAME");
        eventDate = getArguments().getString("EVENT_DATE");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Vrijeme do događaja");

        textName.setText(eventName);
        timerSetter.setTimer(eventDate, textTimer);
    }
}
