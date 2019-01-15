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

    @Query("SELECT * FROM task_table ORDER BY due_date ASC")
    LiveData<List<Task>> getTasksByDateAsc();

    @Query("SELECT * FROM task_table ORDER BY due_date DESC")
    LiveData<List<Task>> getTasksByDateDesc();

    @Query("SELECT * FROM task_table ORDER BY task_name ASC")
    LiveData<List<Task>> getTasksByNameAsc();

    @Query("SELECT * FROM task_table ORDER BY task_name DESC")
    LiveData<List<Task>> getTasksByNameDesc();

}