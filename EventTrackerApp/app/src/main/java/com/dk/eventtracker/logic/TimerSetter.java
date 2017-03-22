package com.dk.eventtracker.logic;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dalibor on 22.3.2017..
 */

public class TimerSetter {
    private static final String FORMAT = "%02d:%02d:%02d";

    public void setTimer(String eventDate, final TextView textTimer){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            Date endDate = sdf.parse(eventDate);
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

                    textTimer.setText("Upravo!");
                }
            }.start();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
