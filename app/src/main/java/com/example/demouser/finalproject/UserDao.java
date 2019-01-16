package com.example.demouser.finalproject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Query("SELECT points FROM user_table WHERE userName=:userName")
    int getPoints(String userName);

    @Query("UPDATE user_table SET points=:points WHERE userName=:userName")
    void setPoints(int points, String userName);

    @Query("SELECT COUNT(*) FROM user_table")
    int getCount();
}
