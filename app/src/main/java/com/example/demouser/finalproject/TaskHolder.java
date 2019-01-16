package com.example.demouser.finalproject;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;


public class TaskHolder extends RecyclerView.ViewHolder{
    private TextView taskName;
    private Button StartStopButton;
    private Button DoneButton;
    private Task task;
    private String LOG = "log";
    private Activity mainActivity;
    private long tStart;
    private long tEnd;
    private double tElapsed;

    private int currentPoints;
//    private User user;


    View.OnClickListener doneListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doneButton(v);
        }
    };

    View.OnClickListener startStopListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startStopButton(v);
        }
    };

    public TaskHolder(View itemView){
        super(itemView);
        taskName = (TextView) itemView.findViewById(R.id.taskName);
        StartStopButton = (Button) itemView.findViewById(R.id.start_stop);
        DoneButton = (Button) itemView.findViewById(R.id.done);
        DoneButton.setOnClickListener(doneListener);
        StartStopButton.setOnClickListener(startStopListener);



    }

    public Task getTask(){
        return task;
    }

    public void bindTask(Task task){
        this.task = task;
//        this.user = user;
        this.tElapsed = task.getTimeWorked();
        taskName.setText(task.getTaskName());

        if (task.getInProgress()){
            StartStopButton.setText(R.string.stop);
            // change background color

        }else{
            StartStopButton.setText(R.string.start);
            // change background color
        }

    }


    public void startStopButton(View view) {

        if (!task.getInProgress()){
            tStart = System.currentTimeMillis();
            StartStopButton.setText(R.string.stop);

            // change background color
            task.setInProgress(true);
            int green = -16711936;

            ((View) view.getParent().getParent()).setBackgroundColor(green);
            Log.i(LOG,"true");

        }else{
            tEnd = System.currentTimeMillis();
            tElapsed += (tEnd - tStart);
            Activity activity = (Activity) view.getContext();
            TaskRepository taskRepository = new TaskRepository(activity.getApplication());
            task.setTimeWorked(tElapsed);
            taskRepository.setTime(task);
            Log.i(LOG,String.valueOf(tElapsed));

            StartStopButton.setText(R.string.start);
            // change background color
            task.setInProgress(false);
            int blue = -16711681;
            ((View) view.getParent().getParent()).setBackgroundColor(blue);
            Log.i(LOG,"false");
        }


    }

    public void doneButton(View view){
        Activity activity = (Activity) view.getContext();
        TaskRepository taskRepository = new TaskRepository(activity.getApplication());
        UserRepository userRepository = new UserRepository(activity.getApplication(), activity);
        if (task.getInProgress()) {
            startStopButton(((View)view.getParent()).findViewById(R.id.start_stop));
        }
        currentPoints = ((MainActivity) activity).getCurrentPoints();
        User user = new User(task.getUserName(), currentPoints + 10);
        userRepository.setPoints(user);

        Log.i(LOG, String.valueOf(taskRepository.getTime(task)));
        taskRepository.delete(task);

    }






}
