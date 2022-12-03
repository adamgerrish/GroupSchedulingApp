package com.example.groupcalendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EditEvent extends AppCompatActivity {
    private EditText eventNameEditText;
    private TextView eventTimeTextView, eventDateTextView;
    private Button pickTimeBtn;
    private LocalTime time;
    private String editedTime;
    private Event selected;
    private Button deleteButton;

    TimePicker picker;

    public EditEvent() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        initWidgets();
        time =LocalTime.now();
        eventDateTextView.setText("Date: "+CalUtils.formatDate(CalUtils.selectedDate));
        eventTimeTextView.setText("Time: "+CalUtils.formatTime(time));
        checkForEdit();
        pickTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour, minute;
                //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh-mm-ss-a");

                String sHour,sMinute;
                String am_pm;
                if (Build.VERSION.SDK_INT >= 23 ){
                    hour = picker.getHour();
                    minute = picker.getMinute();
                }
                else{
                    hour = picker.getCurrentHour();
                    minute = picker.getCurrentMinute();
                }
                if(hour > 12) {
                    am_pm = "PM";
                    hour = hour - 12;
                }
                else
                {
                    am_pm="AM";
                }
                sHour = String.valueOf(hour);
                sMinute = String.valueOf(minute);

                saveEventCustomTime(sHour,sMinute,am_pm);
//                if (sHour.length() == 1){
//                    sHour = "0"+sHour;
//                }
//                if (sMinute.length() == 1){
//                    sMinute = "0"+sMinute;
//                }
//                editedTime = (sHour +"-"+ sMinute+"-00-"+am_pm);
//                LocalTime localTime = LocalTime.parse(editedTime, dateTimeFormatter);
//                System.out.println(editedTime);
//                System.out.println(localTime);


//                String eventName = eventNameEditText.getText().toString();
//                eventTimeTextView.setText(editedTime);
//                int id = Event.eventsList.size();
//                Event newEvent  = new Event(id,eventName, CalUtils.selectedDate,localTime);
//                Event.eventsList.add(newEvent);
//                finish();
            }

        });


    }

    //    public String timeToString(LocalTime time){
//
//    }
    private void initWidgets() {
        pickTimeBtn = findViewById(R.id.changeTime);
        eventNameEditText = findViewById(R.id.eventNameEditText);
        eventDateTextView =findViewById(R.id.eventDateTextView);
        eventTimeTextView = findViewById(R.id.eventTimeTextView);
        deleteButton = findViewById(R.id.delete);
        picker=(TimePicker)findViewById(R.id.timePicker1);
        picker.setIs24HourView(true);

    }

    private void checkForEdit() {
        Intent previousIntent = getIntent();
        int passedID = previousIntent.getIntExtra(Event.EXTRA, -1);
        selected = Event.getEventForID(passedID);

        if (selected != null){
            eventNameEditText.setText(selected.getName());
            eventDateTextView.setText(selected.getDate().toString());
            eventTimeTextView.setText(selected.getTime().toString());
        } else {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void saveEventAction(View view) {
        //EventDatabase edb = EventDatabase.instanceOfDb(this);
        int id = Event.eventsList.size();
        String eventName = eventNameEditText.getText().toString();
        Event newEvent  = new Event(id,eventName, CalUtils.selectedDate,time,0);
        Event.eventsList.add(newEvent);
        //edb.addEventDb(newEvent);
        finish();
    }

    public void saveEventCustomTime(String sHour,String sMinute, String am_pm) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh-mm-ss-a");
        if (sHour.length() == 1) {
            sHour = "0" + sHour;
        }
        if (sMinute.length() == 1) {
            sMinute = "0" + sMinute;
        }
        editedTime = (sHour + "-" + sMinute + "-00-" + am_pm);
        LocalTime localTime = LocalTime.parse(editedTime, dateTimeFormatter);

        EventDatabase edb = EventDatabase.instanceOfDb(this);
        String eventName = eventNameEditText.getText().toString();

        if(selected == null) {
            int id = Event.eventsList.size();
            Event newEvent = new Event(id, eventName, CalUtils.selectedDate, localTime,0);
            Event.eventsList.add(newEvent);
            edb.addEventDb(newEvent);
            CharSequence text = "Event Added";
            Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
            toast.show();
        } else {
            selected.setName(eventName);
            selected.setTime(localTime);
            edb.updateEventDb(selected);
            CharSequence text = "Event Edited";
            Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
            toast.show();
        }

        finish();
    }
    public void deleteEvent(View view){
        selected.setDeleted(1);
        EventDatabase edb = EventDatabase.instanceOfDb(this);
        edb.updateEventDb(selected);
        finish();
        CharSequence text = "Event Deleted";
        Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();
    }
}