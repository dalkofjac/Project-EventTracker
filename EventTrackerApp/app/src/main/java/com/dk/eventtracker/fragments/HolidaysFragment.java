package com.dk.eventtracker.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dk.database.Event;
import com.dk.eventtracker.R;
import com.dk.eventtracker.adapters.BirthdaysAdapter;
import com.dk.eventtracker.adapters.HolidaysAdapter;
import com.dk.eventtracker.helpers.EventsData;
import com.dk.eventtracker.logic.EventListSorter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dalibor on 20.3.2017..
 */

public class HolidaysFragment extends Fragment {
    private List<Event> eventList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HolidaysAdapter mAdapter;
    private EventListSorter els = new EventListSorter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_holidays,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Blagdani");

        recyclerView = (RecyclerView) getView().findViewById(R.id.main_recycler_2);

        if(eventList.isEmpty()) {
            EventsData.getHolidaysData(eventList);
            els.attachYears(eventList);
            els.sortTheList(eventList);
        }

        mAdapter = new HolidaysAdapter(eventList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

}
