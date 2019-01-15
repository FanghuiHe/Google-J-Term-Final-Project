package com.example.demouser.finalproject;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT time_worked FROM task_table WHERE id=:id")
    double getTimeWorked(int id);

    @Query("UPDATE task_table SET time_worked = :time WHERE id=:id")
    void setTime(double time, int id);

//    @Query("DELETE * FROM task_table WHERE id=:id ")
//    LiveData<List<Task>> deleteTask(int id);

    @Query("SELECT * FROM task_table ORDER BY due_date ASC")
    LiveData<List<Task>> getTasksByDateAsc();

    @Query("SELECT * FROM task_table ORDER BY due_date DESC")
    LiveData<List<Task>> getTasksByDateDesc();

    @Query("SELECT * FROM task_table ORDER BY task_name ASC")
    LiveData<List<Task>> getTasksByNameAsc();

    @Query("SELECT * FROM task_table ORDER BY task_name DESC")
    LiveData<List<Task>> getTasksByNameDesc();

}
