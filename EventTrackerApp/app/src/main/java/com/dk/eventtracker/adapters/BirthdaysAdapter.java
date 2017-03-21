package com.dk.eventtracker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dk.database.Event;
import com.dk.eventtracker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dalibor on 21.3.2017..
 */

public class BirthdaysAdapter extends RecyclerView.Adapter<BirthdaysAdapter.MyViewHolder> {
    private List<Event> eventList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView date;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.textView_event_name);
            date = (TextView) view.findViewById(R.id.textView_event_date);
        }
    }


    public BirthdaysAdapter(List<Event> eventList, Context context) {
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.name.setText(event.getName());
        holder.date.setText(event.getDate());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
