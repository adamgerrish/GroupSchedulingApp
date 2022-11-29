package com.example.groupcalendaapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

        public EventAdapter(@NonNull Context context, List<Event> events){
            super(context, 0, events);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent ){
            Event event = getItem(position);

            if (convertView==null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell,parent,false);
                TextView eventCellTextView = convertView.findViewById(R.id.eventCellTextView);
                String eventTitle = event.getName()+" "+CalUtils.formatTime(event.getTime());
                eventCellTextView.setText(eventTitle);

            }
            return convertView;
        }


    }

