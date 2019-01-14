package com.example.demouser.finalproject;

public class Task {
    // Variables for views
    private String taskName;
    private boolean onProgress;

    // Constructor
    public Task(String taskName){
        this.taskName = taskName;
        this.onProgress = false;
    }

    // Getters and Setters
    public String getTask(){
        return taskName;
    }
    public void setTaskName(String s){
        this.taskName = s;
    }

    public boolean getProgress(){
        return onProgress;
    }

    public void setProgress(boolean b){
        this.onProgress = b;
    }



}
