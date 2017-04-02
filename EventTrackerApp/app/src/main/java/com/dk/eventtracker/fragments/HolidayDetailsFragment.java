package com.dk.eventtracker.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dk.database.Event;
import com.dk.eventtracker.R;
import com.dk.eventtracker.logic.TimerSetter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dalibor on 22.3.2017..
 */

public class HolidayDetailsFragment extends Fragment {
    @BindView(R.id.textView_hd_name)
    TextView textName;

    @BindView(R.id.textView_hd_date)
    TextView textDate;

    @BindView(R.id.textView_timer_hd)
    TextView textTimer;

    @BindView(R.id.textView_days_hd)
    TextView textDays;

    TimerSetter timerSetter;

    String eventName;
    String eventDate;

    String removalQuestion;
    String remove;
    String cancel;

    AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_holiday_details,container,false);
        ButterKnife.bind(this, view);
        removalQuestion = view.getContext().getString(R.string.removal_question);
        remove = view.getContext().getString(R.string.remove);
        cancel = view.getContext().getString(R.string.cancel);
        alertDialog = new AlertDialog.Builder(view.getContext()).create();
        timerSetter = new TimerSetter();
        eventName = getArguments().getString("EVENT_NAME");
        eventDate = getArguments().getString("EVENT_DATE");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Vrijeme do blagdana");

        textName.setText(eventName);
        textDate.setText(eventDate);
        textDays.setText(""+ timerSetter.calculateDays(eventDate));
        timerSetter.setTimer(eventDate, textTimer);
    }

    @OnClick(R.id.button_hd_del)
    public void onButtonHdDelClicked(){
        alertDialog.setTitle(removalQuestion);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Event event = Event.getSpecificByName(eventName);
                event.delete();
                alertDialog.dismiss();
                getActivity().onBackPressed();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }
}
