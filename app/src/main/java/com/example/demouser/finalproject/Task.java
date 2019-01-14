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
    private int dueDate;

    // Constructor
    public Task(String taskName, int dueDate){
        this.taskName = taskName;
        this.inProgress = false;
        this.dueDate = dueDate;
    }

    // Getters and Setters
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
    public int getDueDate(){
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }
}
