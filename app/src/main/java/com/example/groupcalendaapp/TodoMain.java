package com.example.groupcalendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;



public class TodoMain extends AppCompatActivity {

  //  private ArrayList<String> tasks;
  //  private ArrayAdapter<String> tasksAdapter;
    private ListView taskListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_main);
        Button newTaskButton;

        taskListView = findViewById(R.id.taskListView);
        newTaskButton = findViewById(R.id.newTaskButton);

        newTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newTaskIntent = new Intent(TodoMain.this, TaskDetails.class);
                startActivity(newTaskIntent);
            }
        });
        setTaskAdapter();




    }

    private void setTaskAdapter() {
        TaskAdapter taskAdapter = new TaskAdapter(getApplicationContext(), Task.taskArrayList);
        taskListView.setAdapter(taskAdapter);
    }







}