package com.example.groupcalendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;

public class EditEvent extends AppCompatActivity {
    private EditText eventNameEditText;
    private TextView eventTimeTextView, eventDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        initWidgets();
        time =LocalTime.now();
        eventDateTextView.setText("Date: "+CalUtils.formatDate(CalUtils.selectedDate));
        eventTimeTextView.setText("Time: "+CalUtils.formatTime(time));



    }

    private LocalTime time;

    private void initWidgets() {
        eventNameEditText = findViewById(R.id.eventNameEditText);
        eventDateTextView =findViewById(R.id.eventDateTextView);
        eventTimeTextView = findViewById(R.id.eventTimeTextView);

    }

    public void saveEventAction(View view) {
        String eventName = eventNameEditText.getText().toString();
        Event newEvent  = new Event(eventName, CalUtils.selectedDate,time);
        Event.eventsList.add(newEvent);
        finish();
    }

}