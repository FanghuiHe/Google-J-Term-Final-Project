package com.example.demouser.finalproject;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.support.annotation.IntegerRes;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Query("SELECT COUNT(*) FROM user_table WHERE userName=:userName")
    LiveData<Integer> getUserCount(String userName);

    @Query("SELECT points FROM user_table WHERE userName=:userName")
    LiveData<Integer> getPoints(String userName);

    @Query("UPDATE user_table SET points=:points WHERE userName=:userName")
    void setPoints(int points, String userName);

    @Query("UPDATE user_table SET char_name=:charName WHERE userName=:userName")
    void setCharName(String charName, String userName);

    @Query("SELECT char_name FROM user_table WHERE userName=:userName")
    LiveData<String> getCharName(String userName);

    @Query("SELECT COUNT(*) FROM user_table")
    int getCount();
}
