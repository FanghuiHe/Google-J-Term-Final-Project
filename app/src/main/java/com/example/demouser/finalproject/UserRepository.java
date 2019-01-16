package com.example.demouser.finalproject;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(Application application){
        TaskDatabase db = TaskDatabase.getDatabase(application);
        userDao = db.userDao();
    }

    void insert(User user){
        new insertAsyncUser(userDao).execute(user);
    }

    int getPoints(User user){
        int points = user.getPoints();
        return points;
    }

    void setPoints(User user){
        new setPointsAsyncUser(userDao).execute(user);
    }

    private static class insertAsyncUser extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncUserDao;

        insertAsyncUser(UserDao dao) {
            mAsyncUserDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncUserDao.insert(params[0]);
            return null;
        }
    }
    private static class setPointsAsyncUser extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncUserDao;

        setPointsAsyncUser(UserDao dao) {
            mAsyncUserDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncUserDao.setPoints(params[0].getPoints(), params[0].getUserName());
            return null;
        }
    }

}
