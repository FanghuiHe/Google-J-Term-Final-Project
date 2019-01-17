package com.example.demouser.finalproject;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


import java.sql.Timestamp;
import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

//@Entity(foreignKeys = @ForeignKey(entity = User.class,parentColumns = "userName",childColumns = "user_name"))
@Entity(tableName = "task_table")
@ForeignKey(entity = User.class,parentColumns = "userName",childColumns = "user_name", onDelete = CASCADE)
//@Table(name = "author")
//@SecondaryTable(name = "author_details")
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
    @ColumnInfo(name = "user_name")
    private String userName;

    // Constructor
    public Task(String taskName, String dueDate, String userName){
        this.taskName = taskName;
        this.inProgress = false;
        this.dueDate = dueDate;
        this.timeWorked = 0;
        this.userName = userName;
    }

    // Getters and Setters

    public double getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(double timeWorked){
        this.timeWorked=timeWorked;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String user){
        this.userName = user;
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
