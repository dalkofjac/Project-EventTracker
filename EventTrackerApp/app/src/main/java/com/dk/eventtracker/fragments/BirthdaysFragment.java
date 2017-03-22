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
import com.dk.eventtracker.R;
import com.dk.eventtracker.adapters.BirthdaysAdapter;
import com.dk.eventtracker.helpers.EventsData;
import com.dk.eventtracker.logic.EventListSorter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Dalibor on 21.3.2017..
 */

public class BirthdaysFragment extends Fragment {
    private List<Event> eventList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BirthdaysAdapter mAdapter;
    private EventListSorter els = new EventListSorter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_birthdays,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Rođendani");

        recyclerView = (RecyclerView) getView().findViewById(R.id.main_recycler);

        if(eventList.isEmpty()) {
            EventsData.getBirthdaysData(eventList);
            els.attachYears(eventList);
            els.sortTheList(eventList);
        }


        mAdapter = new BirthdaysAdapter(eventList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }
}
