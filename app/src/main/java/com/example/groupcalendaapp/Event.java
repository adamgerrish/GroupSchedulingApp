package com.example.groupcalendaapp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {
    public static ArrayList<Event> eventsList = new ArrayList<>();

    public static ArrayList<Event> eventDate(LocalDate date){
        ArrayList<Event> events = new ArrayList<>();

        for (Event event : eventsList){
            if(event.getDate().equals(date))
                events.add(event);
        }
        return  events;
    }

    private String name;
    private LocalDate date;
    private LocalTime time;

    public Event(String name, LocalDate date, LocalTime time){
        this.name = name;
        this.date = date;
        this.time = time;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getName(){
        return name;
    }
    public LocalTime getTime(){
        return time;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setTime(LocalTime time){
        this.time = time;
    }
}

