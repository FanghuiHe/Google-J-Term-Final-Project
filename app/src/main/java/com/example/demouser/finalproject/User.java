package com.example.demouser.finalproject;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey @NonNull
    private String userName;
    @ColumnInfo(name="char_name")
    private String charName;
    @ColumnInfo(name="points")
    private int points;

    public User(String userName){
        this.userName = userName;
        this.charName = "Simba";
        this.points = 0;
    }

    @Ignore
    public User(String userName, int points){
        this.userName = userName;
        this.points = points;

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCharName(){
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
