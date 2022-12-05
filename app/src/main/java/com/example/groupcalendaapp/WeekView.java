package com.example.groupcalendaapp;

import static com.example.groupcalendaapp.CalUtils.monthYearFromDate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_week_view);
        initWidgets();
        loadDbToMemory();
        setWeekView();
        clickableList();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this,"Welcome to HUSTLE! Click on the 3 dots for the Help menu and more", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(this,"Help Menu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this,"KNOW MORE", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                Toast.makeText(this,"Set Event", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Toast.makeText(this,"Week View", Toast.LENGTH_SHORT).show();
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }
    private void initWidgets() {
        calRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYrTxt = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);
    }

    private void loadDbToMemory(){
        EventDatabase edb = EventDatabase.instanceOfDb(this);
        edb.populateEvents();
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

    private void clickableList() {

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Event selected = (Event) eventListView.getItemAtPosition(i);
                Intent editEvent = new Intent(getApplicationContext(), EditEvent.class);
                editEvent.putExtra(Event.EXTRA,selected.getId());
                startActivity(editEvent);

            }
        });
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