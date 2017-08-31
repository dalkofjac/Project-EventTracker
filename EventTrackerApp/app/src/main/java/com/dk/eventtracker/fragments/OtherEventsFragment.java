package com.dk.eventtracker.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.dk.eventtracker.adapters.OtherEventsAdapter;
import com.dk.eventtracker.helpers.EventsData;
import com.dk.eventtracker.helpers.FragmentStarter;
import com.dk.eventtracker.logic.EventListSorter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dalibor on 4.4.2017..
 */

public class OtherEventsFragment extends Fragment {
    private List<Event> eventList = new ArrayList<>();
    private RecyclerView recyclerView;
    private OtherEventsAdapter mAdapter;
    private EventListSorter els = new EventListSorter();

    @BindView(R.id.fab_event)
    public FloatingActionButton fab;

    private String fragmentTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_other_events, container, false);
        ButterKnife.bind(this, view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putInt("EVENT_TYPE", 3);

                AddNewEventFragment anef = new AddNewEventFragment();
                anef.setArguments(args);

                FragmentStarter.StartNewFragment(anef, getActivity(), 2);
            }
        });

        fragmentTitle = getResources().getString(R.string.events_title);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(fragmentTitle);

        recyclerView = (RecyclerView) getView().findViewById(R.id.main_recycler_4);

        requestData();

        mAdapter = new OtherEventsAdapter(eventList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    public void requestData(){
        if(SQLite.select().from(Event.class).where(Event_Table.type.eq(3)).queryList().isEmpty()){
            EventsData.getOtherEventsData(eventList);
        }
        else{
            eventList = (ArrayList<Event>) Event.getAllOtherEvents();
        }
        els.attachYears(eventList);
        els.sortTheList(eventList);
    }

}
