package com.dk.eventtracker.adapters;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dk.database.Event;
import com.dk.eventtracker.R;
import com.dk.eventtracker.fragments.HolidayDetailsFragment;
import com.dk.eventtracker.helpers.FragmentStarter;

import java.util.List;

/**
 * Created by Dalibor on 22.3.2017..
 */

public class HolidaysAdapter extends RecyclerView.Adapter<HolidaysAdapter.HolidaysViewHolder> {
    private List<Event> eventList;
    Context context;

    public static class HolidaysViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView name;
        public TextView date;

        private Context context;

        public HolidaysViewHolder(View view ,Context context) {
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

            HolidayDetailsFragment hdf = new HolidayDetailsFragment();
            hdf.setArguments(args);
            FragmentStarter.StartNewFragment(hdf, ((Activity)context), 2);

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public HolidaysAdapter(List<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @Override
    public HolidaysAdapter.HolidaysViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item,parent,false);
        return new HolidaysAdapter.HolidaysViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(HolidaysAdapter.HolidaysViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.name.setText(event.getName());
        holder.date.setText(event.getDate());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
