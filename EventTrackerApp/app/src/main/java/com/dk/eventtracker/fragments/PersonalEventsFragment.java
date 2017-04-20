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
import com.dk.eventtracker.R;
import com.dk.eventtracker.adapters.PersonalEventsAdapter;
import com.dk.eventtracker.helpers.FragmentStarter;
import com.dk.eventtracker.helpers.MyJsonParser;
import com.dk.eventtracker.logic.EventListSorter;
import com.dk.eventtracker.webservices.ReceiveEventData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dalibor on 20.4.2017..
 */

public class PersonalEventsFragment extends Fragment {
    private List<Event> eventList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PersonalEventsAdapter mAdapter;
    private EventListSorter els = new EventListSorter();

    @BindView(R.id.fab_event)
    public FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_events, container, false);
        ButterKnife.bind(this, view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putInt("EVENT_TYPE", 4);

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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Privatni događaji");

        recyclerView = (RecyclerView) getView().findViewById(R.id.main_recycler_5);

        requestData();

        mAdapter = new PersonalEventsAdapter(eventList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    public void requestData() {
        String ans = "";
        ReceiveEventData red = new ReceiveEventData(getActivity().getIntent().getStringExtra("USER_ID"));
        try{
            ans = red.execute().get().toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        eventList = MyJsonParser.ParseEventsInfo(ans);

        els.attachYears(eventList);
        els.sortTheList(eventList);
    }
}
