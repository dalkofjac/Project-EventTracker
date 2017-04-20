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
import com.dk.eventtracker.fragments.PersonalEventsDetailsFragment;
import com.dk.eventtracker.helpers.FragmentStarter;

import java.util.List;

/**
 * Created by Dalibor on 20.4.2017..
 */

public class PersonalEventsAdapter extends RecyclerView.Adapter<PersonalEventsAdapter.PersonalEventsViewHolder> {
    private List<Event> eventList;
    Context context;

    public static class PersonalEventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView name;
        public TextView date;

        private Context context;

        public PersonalEventsViewHolder(View view ,Context context) {
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

            PersonalEventsDetailsFragment pedf = new PersonalEventsDetailsFragment();
            pedf.setArguments(args);
            FragmentStarter.StartNewFragment(pedf, ((Activity)context), 2);

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public PersonalEventsAdapter(List<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @Override
    public PersonalEventsAdapter.PersonalEventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item,parent,false);
        return new PersonalEventsAdapter.PersonalEventsViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(PersonalEventsAdapter.PersonalEventsViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.name.setText(event.getName());
        holder.date.setText(event.getDate());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
