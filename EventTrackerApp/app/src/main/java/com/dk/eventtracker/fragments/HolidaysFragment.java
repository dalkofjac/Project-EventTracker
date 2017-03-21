package com.dk.eventtracker.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dk.eventtracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dalibor on 20.3.2017..
 */

public class HolidaysFragment extends Fragment {
    @BindView(R.id.textView_timer)
    TextView textTimer;

    private static final String FORMAT = "%02d:%02d:%02d";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_holidays,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Blagdani");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date endDate = sdf.parse("25/12/2017");
            Date startDate = new Date();

            long diffInMs = endDate.getTime() - startDate.getTime();

            new CountDownTimer(diffInMs, 1000) {

                public void onTick(long millisUntilFinished) {

                    textTimer.setText(""+String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {
                    textTimer.setText("Upravo!!!");
                }
            }.start();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
