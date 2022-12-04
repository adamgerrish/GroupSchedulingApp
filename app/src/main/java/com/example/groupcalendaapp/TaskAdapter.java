package com.example.groupcalendaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(@NonNull Context context, List<Task> tasks) {
        super(context, 0, tasks);

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView2, @NonNull ViewGroup parent) {

        Task task = getItem(position);
        
        if (convertView2 == null)
            convertView2 = LayoutInflater.from(getContext()).inflate(R.layout.task_cell, parent, false);

        TextView task_desc = convertView2.findViewById(R.id.cell_task);
        TextView difficulty = convertView2.findViewById((R.id.cell_difficulty));

        task_desc.setText(task.getTask_desc());
        difficulty.setText(task.getDifficulty());



        return convertView2;

    }
}
