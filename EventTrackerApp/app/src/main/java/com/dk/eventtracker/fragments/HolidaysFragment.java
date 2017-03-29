package com.dk.eventtracker.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dk.database.Event;
import com.dk.database.Event_Table;
import com.dk.eventtracker.R;
import com.dk.eventtracker.adapters.BirthdaysAdapter;
import com.dk.eventtracker.adapters.HolidaysAdapter;
import com.dk.eventtracker.helpers.EventsData;
import com.dk.eventtracker.helpers.FragmentStarter;
import com.dk.eventtracker.logic.EventListSorter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

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

    @BindView(R.id.fab_event)
    public FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_holidays,container,false);
        ButterKnife.bind(this, view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putInt("EVENT_TYPE", 1);

                AddNewEventFragment anef = new AddNewEventFragment();
                anef.setArguments(args);

                FragmentStarter.StartNewFragment(anef, getActivity(), 2);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Blagdani");

        recyclerView = (RecyclerView) getView().findViewById(R.id.main_recycler_2);

        requestData();

        mAdapter = new HolidaysAdapter(eventList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    public void requestData(){
        if(SQLite.select().from(Event.class).where(Event_Table.type.eq(1)).queryList().isEmpty()){
            EventsData.getHolidaysData(eventList);
        }
        else{
            eventList = (ArrayList<Event>) Event.getAllHolidays();
        }
        els.attachYears(eventList);
        els.sortTheList(eventList);
    }

}
