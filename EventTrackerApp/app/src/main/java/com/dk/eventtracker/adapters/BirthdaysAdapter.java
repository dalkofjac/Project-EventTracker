package com.dk.eventtracker.adapters;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dk.database.Event;
import com.dk.eventtracker.R;
import com.dk.eventtracker.fragments.BirthdayDetailsFragment;
import com.dk.eventtracker.fragments.HolidaysFragment;
import com.dk.eventtracker.helpers.FragmentStarter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dalibor on 21.3.2017..
 */

public class BirthdaysAdapter extends RecyclerView.Adapter<BirthdaysAdapter.BirthdaysViewHolder> {
    private List<Event> eventList;
    Context context;

    public static class BirthdaysViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView name;
        public TextView date;

        private Context context;

        public BirthdaysViewHolder(View view ,Context context) {
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

            BirthdayDetailsFragment bdf = new BirthdayDetailsFragment();
            bdf.setArguments(args);
            FragmentStarter.StartNewFragment(bdf, ((Activity)context), 2);
        }

        @Override
        public boolean onLongClick(View v) { return false; }
    }

    public BirthdaysAdapter(List<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @Override
    public BirthdaysViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item,parent,false);
        return new BirthdaysViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(BirthdaysViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.name.setText(event.getName());
        holder.date.setText(event.getDate());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
