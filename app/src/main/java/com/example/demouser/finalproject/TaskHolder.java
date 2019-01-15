package com.example.demouser.finalproject;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TaskHolder extends RecyclerView.ViewHolder{
    private TextView taskName;
    private Button StartStopButton;
    private Button DoneButton;
    private Task task;
    private String LOG = "log";
    private Activity mainActivity;

    View.OnClickListener doneListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doneButton(v);
        }
    };

    public TaskHolder(View itemView){
        super(itemView);
        taskName = (TextView) itemView.findViewById(R.id.taskName);
        StartStopButton = (Button) itemView.findViewById(R.id.start_stop);
        DoneButton = (Button) itemView.findViewById(R.id.done);
        DoneButton.setOnClickListener(doneListener);

    }

    public Task getTask(){
        return task;
    }

    public void bindTask(Task task){
        this.task = task;
        taskName.setText(task.getTaskName());

        if (task.getInProgress()){
            StartStopButton.setText(R.string.stop);
            // change background color

        }else{
            StartStopButton.setText(R.string.start);
            // change background color
        }

    }

    public void onClick(View view) {
        if (task.getInProgress()){
            StartStopButton.setText(R.string.stop);
            // change background color
            task.setInProgress(false);
            Log.i(LOG,"true");

        }else{
            StartStopButton.setText(R.string.start);
            // change background color
            task.setInProgress(true);
        }
    }

    public void doneButton(View view){
        Activity activity = (Activity) view.getContext();
        TaskRepository taskRepository = new TaskRepository(activity.getApplication());
        taskRepository.delete(task);
    }






}
