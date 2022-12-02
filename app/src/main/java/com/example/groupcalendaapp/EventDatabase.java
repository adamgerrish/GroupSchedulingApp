package com.example.groupcalendaapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class EventDatabase extends SQLiteOpenHelper{

    static int DATABASE_VERSION = 9;
    static String DATABASE_NAME = "Events";
    final static String COUNTER = "Counter";
    private static EventDatabase edb;

    final static String KEY_ID = "id";
    final static String KEY_MESSAGE = "message";
    final static String KEY_DATE = "date";
    final static String KEY_TIME = "time";
    final static String KEY_DELETED = "deleted";
    final static String TABLE_NAME = "Events";

    final static String CREATE_DB = "create table "+ TABLE_NAME+
            " ( "+ KEY_ID+" integer primary key autoincrement, "+
            KEY_MESSAGE + " text not null, "+
            KEY_DATE + " text not null, "+
            KEY_TIME + " text not null, "+
            KEY_DELETED + " text not null);";

    EventDatabase(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        final String DROP = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(DROP);
        onCreate(db);
    }

    public static EventDatabase instanceOfDb (Context ctx){
        if (edb == null){
            edb = new EventDatabase(ctx);
        }
        return edb;
    }

    public void addEventDb(Event event){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, event.getId());
        contentValues.put(KEY_MESSAGE, event.getName());
        contentValues.put(KEY_DATE, getStringDate(event.getDate()));
        contentValues.put(KEY_TIME, getStringTime(event.getTime()));
        contentValues.put(KEY_DELETED, event.getDeleted());


        db.insert(TABLE_NAME,null,contentValues);
    }

    public void populateEvents(){
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            if (result.getCount() != 0){
                while (result.moveToNext()){

                    int id = result.getInt(0);
                    String title = result.getString(1);
                    String stringDate = result.getString(2);
                    String stringTime = result.getString(3);
                    int deleted = result.getInt(4);

                    LocalDate date = getLocalDate(stringDate);
                    LocalTime time = getLocalTime(stringTime);

                    Event event = new Event(id,title,date,time,deleted);
                    Event.eventsList.add(event);


                }
            }
        }
    }
    public void updateEventDb(Event event){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, event.getId());
        contentValues.put(KEY_MESSAGE, event.getName());
        contentValues.put(KEY_DATE, getStringDate(event.getDate()));
        contentValues.put(KEY_TIME, getStringTime(event.getTime()));
        contentValues.put(KEY_DELETED, event.getDeleted());

        db.update(TABLE_NAME,contentValues,KEY_ID+" =? ", new String[]{String.valueOf(event.getId())});
    }

    private String getStringDate(LocalDate date){
        if (date == null){
            return null;
        } else {
            return CalUtils.formatDate(date);
        }
    }

    private String getStringTime(LocalTime time){
        if (time == null) {
            return null;
        } else {
            return CalUtils.formatTime(time);
        }
    }

    private LocalTime getLocalTime(String time){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        if (time == null) {
            return null;
        } else {
            LocalTime localTime = LocalTime.parse(time, dateTimeFormatter);
            return localTime;
        }
    }

    private LocalDate getLocalDate(String date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        if (date == null) {
            return null;
        } else {
            LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
            return localDate;
        }
    }
}
