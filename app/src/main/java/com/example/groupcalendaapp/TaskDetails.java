package com.example.groupcalendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TaskDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    EditText titleDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        spinner = findViewById(R.id.spinner_difficulty);
        titleDesc = findViewById(R.id.taskName);



        String[]difficulty = {"Easy","Medium", "Hard"};

        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, difficulty);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

        Button saveTaskButton = findViewById(R.id.saveTaskButton);

        saveTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = Task.taskArrayList.size();
                String taskName = "cook";
                String difficulty_item = "hard";
                Task newTask = new Task(id, taskName, difficulty_item);
                Task.taskArrayList.add(newTask);
                finish();

            }
        });


    }





    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       // String difficulty_item = adapterView.getItemAtPosition(i).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }




}