package com.example.demouser.finalproject;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.os.AsyncTask;


public class UserRepository {
    private UserDao userDao;
    private int count;

    public UserRepository(Application application){
        TaskDatabase db = TaskDatabase.getDatabase(application);
        userDao = db.userDao();
//        count = userDao.getCount();

    }

    void insert(User user){
        new insertAsyncUser(userDao).execute(user);
    }

    int getPoints(String userName){
        int points = userDao.getPoints(userName);
        return points;
//        return new
    }

    void setPoints(User user){
        new setPointsAsyncUser(userDao).execute(user);
    }

   /* int getCount(){
        return new getCountAsyncUser(userDao).execute();
    }

    private static class getCountAsyncUser extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncUserDao;

        getCountAsyncUser(UserDao dao) {
            mAsyncUserDao = dao;
        }

        @Override
        protected int doInBackground(final User... params) {
            return mAsyncUserDao.getCount();
            //return null;
        }
    }*/

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
