package com.example.groupcalendaapp;

import static com.example.groupcalendaapp.CalUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.groupcalendaapp.CalUtils.daysInWeek;

public class WeekView extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYrTxt;
    private RecyclerView calRecyclerView;
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        initWidgets();
        setWeekView();
    }
    private void initWidgets() {
        calRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYrTxt = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);
    }
    private void setWeekView() {
        monthYrTxt.setText(monthYearFromDate(CalUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeek(CalUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days,this::onItemClick);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calRecyclerView.setLayoutManager(layoutManager);
        calRecyclerView.setAdapter(calendarAdapter);
        setEventAdapter();

    }

    public void previousWeekAction(View view) {
        CalUtils.selectedDate = CalUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }
    public void nextWeekAction(View view) {
        CalUtils.selectedDate = CalUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date){
        CalUtils.selectedDate = date;
        setWeekView();
    }



    @Override
    protected void onResume(){
        super.onResume();
        setEventAdapter();
    }

    private void setEventAdapter() {
         ArrayList<Event> dailyEvent = Event.eventsDate(CalUtils.selectedDate);
         EventAdapter eventAdapter = new EventAdapter (getApplicationContext(), dailyEvent);
         eventListView.setAdapter(eventAdapter);
    }
    public void newEventAction(View view) {
        startActivity(new Intent (this,EditEvent.class));
    }


}