package com.example.demouser.finalproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
    private ArrayList<Task> tasks;

    public TaskAdapter(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_layout,parent,false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder taskHolder, int i) {
        taskHolder.bindTask(tasks.get(i));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


}
