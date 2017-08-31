package com.dk.eventtracker.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dk.eventtracker.R;

import butterknife.ButterKnife;

/**
 * Created by Dalibor on 22.3.2017..
 */

public class AboutAppFragment extends Fragment {
    private String fragmentTitle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_about_app,container,false);
        ButterKnife.bind(this, view);

        fragmentTitle = getResources().getString(R.string.action_about);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(fragmentTitle);
    }
}
