package com.example.groupcalendaapp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {
    public static ArrayList<Event> eventsList = new ArrayList<>();
    public static String EXTRA = "eventEdit";

    public static ArrayList<Event> eventsDate(LocalDate date){
        ArrayList<Event> events = new ArrayList<>();

        for (Event event : eventsList){
            if(event.getDeleted() == 0 && event.getDate().equals(date))
                events.add(event);
        }
        return  events;
    }

    private int id;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private int deleted;

    public Event(int id, String name, LocalDate date, LocalTime time, int deleted){
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.deleted = 0;
    }

    public static Event getEventForID(int passedID) {

        for (Event event : eventsList){
            if (event.getId() == passedID){
                return event;
            }
        }
        return null;
    }

    public static ArrayList<Event> nonDeleted(){
        ArrayList<Event> nonDeleted = new ArrayList<>();
        for (Event event : eventsList){
            if (event.getDeleted() == 1 && event.getDate() == CalUtils.selectedDate){
                nonDeleted.add(event);
            }
        }
        return nonDeleted;
    }

    public int getId() {
        return id;
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
    public int getDeleted() {
        return deleted;
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
    public void setDeleted(int deleted){
        this.deleted = deleted;
    }
}

