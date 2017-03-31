package com.dk.eventtracker.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dk.database.Event;
import com.dk.database.Event_Table;
import com.dk.eventtracker.R;
import com.dk.eventtracker.adapters.HolidaysAdapter;
import com.dk.eventtracker.adapters.UpcomingEventsAdapter;
import com.dk.eventtracker.helpers.EventsData;
import com.dk.eventtracker.logic.EventListSorter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Dalibor on 31.3.2017..
 */

public class UpcomingEventsFragment extends Fragment {
    private List<Event> eventList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UpcomingEventsAdapter mAdapter;
    private EventListSorter els = new EventListSorter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_upcoming_events,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Nadolazeće");
        recyclerView = (RecyclerView) getView().findViewById(R.id.main_recycler_3);

        requestData();

        mAdapter = new UpcomingEventsAdapter(eventList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    public void requestData(){
        if(SQLite.select().from(Event.class).queryList().isEmpty()){
            EventsData.getHolidaysData(eventList);
            EventsData.getBirthdaysData(eventList);
        }
        else{
            eventList = (ArrayList<Event>) Event.getAll();
        }
        els.attachYears(eventList);
        els.sortTheList(eventList);

        while(eventList.size()!=5){
            eventList.remove(eventList.size()-1);
        }
    }
}
