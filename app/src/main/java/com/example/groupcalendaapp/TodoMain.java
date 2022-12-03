package com.example.groupcalendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoMain extends AppCompatActivity {

    private ArrayList<String> tasks;
    private ArrayAdapter<String> tasksAdapter;
    private ListView taskListView;
    private Button taskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_main);

        taskListView = findViewById(R.id.taskListView);
        taskButton = findViewById(R.id.taskButton);
        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        tasks = new ArrayList<>();
        tasksAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, tasks);
        taskListView.setAdapter(tasksAdapter);
        setUpListViewListener();
    }

    private void setUpListViewListener() {
        taskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Task Removed", Toast.LENGTH_LONG).show();

                tasks.remove(i);
                tasksAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
    private void addItem(View view){
        EditText input = findViewById(R.id.taskName);
        String taskText = input.getText().toString();

        if (!(taskText).equals("")){
            tasksAdapter.add(taskText);
            input.setText("");
        }else{
            Toast.makeText(getApplicationContext(), "Please enter a task", Toast.LENGTH_LONG).show();
        }
    }



}