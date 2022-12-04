package com.example.groupcalendaapp;

import java.util.ArrayList;
import java.util.Date;

public class Task {
    public static ArrayList<Task> taskArrayList= new ArrayList<>();
    private int id;
    private String task_desc;
    private String difficulty;
    private Date deleted;

    public Task(int id, String task_desc, String difficulty, Date deleted) {
        this.id = id;
        this.task_desc = task_desc;
        this.difficulty = difficulty;
        this.deleted = deleted;
    }

    public Task(int id, String task_desc, String difficulty) {
        this.id = id;
        this.task_desc = task_desc;
        this.difficulty = difficulty;
        deleted = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
