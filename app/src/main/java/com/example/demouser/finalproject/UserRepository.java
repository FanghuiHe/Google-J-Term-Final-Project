package com.example.demouser.finalproject;

import android.app.Application;
import android.arch.lifecycle.LiveData;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(Application application){
        TaskDatabase db = TaskDatabase.getDatabase(application);
        userDao = db.userDao();
    }

    int getPoints(User user){
        int points = user.getPoints();
        return points;
    }

}
