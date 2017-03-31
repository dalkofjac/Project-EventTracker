package com.dk.eventtracker.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dk.database.Event;
import com.dk.eventtracker.R;
import com.dk.eventtracker.fragments.BirthdayDetailsFragment;
import com.dk.eventtracker.fragments.HolidayDetailsFragment;
import com.dk.eventtracker.helpers.FragmentStarter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dalibor on 31.3.2017..
 */

public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.UpcomingEventsViewHolder> {
    private List<Event> eventList;
    Context context;

    public static class UpcomingEventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView name;
        public TextView date;

        private Context context;

        public UpcomingEventsViewHolder(View view ,Context context) {
            super(view);

            this.context = context;
            name = (TextView) view.findViewById(R.id.textView_event_name);
            date = (TextView) view.findViewById(R.id.textView_event_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Bundle args = new Bundle();
            args.putString("EVENT_NAME",(String)name.getText());
            args.putString("EVENT_DATE",(String)date.getText());

            if(getEventType((String)name.getText()) == 1) {
                HolidayDetailsFragment hdf = new HolidayDetailsFragment();
                hdf.setArguments(args);
                FragmentStarter.StartNewFragment(hdf, ((Activity) context), 2);
            }
            else if(getEventType((String)name.getText()) == 2){
                BirthdayDetailsFragment bdf = new BirthdayDetailsFragment();
                bdf.setArguments(args);
                FragmentStarter.StartNewFragment(bdf, ((Activity)context), 2);
            }

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }

        public int getEventType(String eventName){
            int type = 0;
            List<Event> eventList = Event.getAll();

            for(int i=0;i<eventList.size();i++){
                if(eventList.get(i).getName().equals(eventName)){
                    type = eventList.get(i).getType();
                }
            }
            return type;
        }
    }

    public UpcomingEventsAdapter(List<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @Override
    public UpcomingEventsAdapter.UpcomingEventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item,parent,false);
        return new UpcomingEventsAdapter.UpcomingEventsViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(UpcomingEventsAdapter.UpcomingEventsViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.name.setText(event.getName());
        holder.date.setText(event.getDate());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
