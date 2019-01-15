package com.example.demouser.finalproject;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


import java.sql.Timestamp;
import java.util.Date;


@Entity(tableName="task_table")
public class Task {
    // Variables for views
    @PrimaryKey(autoGenerate=true)
    private int id;
    @ColumnInfo(name="task_name")
    private String taskName;
    @ColumnInfo(name="in_progress")
    private boolean inProgress;
    @ColumnInfo(name="due_date")
    private String dueDate;
    @ColumnInfo(name="time_worked")
    private double timeWorked;


    // Constructor
    public Task(String taskName, String dueDate){
        this.taskName = taskName;
        this.inProgress = false;
        this.dueDate = dueDate;
    }

    // Getters and Setters

    public double getTimeWorked() {
        return timeWorked;
    }
    public void setTimeWorked(double timeWorked){
        this.timeWorked=timeWorked;
    }

    public String getTaskName(){
        return taskName;
    }

    public void setTaskName(String s){
        this.taskName = s;
    }

    public boolean getInProgress(){
        return inProgress;
    }

    public void setInProgress(boolean b){
        this.inProgress = b;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDueDate(){
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

}
