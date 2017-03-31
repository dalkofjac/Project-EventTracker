package com.dk.eventtracker.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dk.eventtracker.R;
import com.dk.eventtracker.adapters.UpcomingEventsAdapter;
import com.dk.eventtracker.helpers.FragmentStarter;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dalibor on 20.3.2017..
 */

public class MainScreenFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main_screen,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Početna");
    }

    @OnClick(R.id.button_ms_holiday)
    public void onButtonMsHolidayClicked(){
        HolidaysFragment hf = new HolidaysFragment();
        FragmentStarter.StartNewFragment(hf, getActivity(), 1);
    }

    @OnClick(R.id.button_ms_birthday)
    public void onButtonMsBirthdayClicked(){
        BirthdaysFragment bf = new BirthdaysFragment();
        FragmentStarter.StartNewFragment(bf, getActivity(), 1);
    }

    @OnClick(R.id.button_ms_upcoming)
    public void onButtonMsUpcomingClicked(){
        UpcomingEventsFragment aef = new UpcomingEventsFragment();
        FragmentStarter.StartNewFragment(aef, getActivity(), 1);
    }
}
