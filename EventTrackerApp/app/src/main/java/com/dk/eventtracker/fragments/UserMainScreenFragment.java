package com.dk.eventtracker.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dk.eventtracker.R;
import com.dk.eventtracker.helpers.FragmentStarter;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dalibor on 19.4.2017..
 */

public class UserMainScreenFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main_screen_user,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Event Tracker");
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

    @OnClick(R.id.button_ms_other)
    public void onButtonMsOtherClicked(){
        OtherEventsFragment oef = new OtherEventsFragment();
        FragmentStarter.StartNewFragment(oef, getActivity(), 1);
    }

    @OnClick(R.id.button_ms_personal)
    public void onButtonMsPersonalClicked(){
        PersonalEventsFragment pef = new PersonalEventsFragment();
        FragmentStarter.StartNewFragment(pef, getActivity(), 1);
    }
}
