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

public class HolidayDetailsFragment extends Fragment {
    @BindView(R.id.textView_hd_name)
    TextView textName;

    @BindView(R.id.textView_hd_date)
    TextView textDate;

    @BindView(R.id.textView_timer_hd)
    TextView textTimer;

    @BindView(R.id.textView_days_hd)
    TextView textDays;

    TimerSetter timerSetter;

    String eventName;
    String eventDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_holiday_details,container,false);
        ButterKnife.bind(this, view);
        timerSetter = new TimerSetter();
        eventName = getArguments().getString("EVENT_NAME");
        eventDate = getArguments().getString("EVENT_DATE");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Vrijeme do blagdana");

        textName.setText(eventName);
        textDate.setText(eventDate);
        textDays.setText(""+ timerSetter.calculateDays(eventDate));
        timerSetter.setTimer(eventDate, textTimer);
    }
}
