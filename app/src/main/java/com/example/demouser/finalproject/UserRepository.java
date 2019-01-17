package com.example.demouser.finalproject;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.widget.TextView;


public class UserRepository {
    private UserDao userDao;
    private int count;
    private Context context;

    public UserRepository(Application application, Context context){
        TaskDatabase db = TaskDatabase.getDatabase(application);
        userDao = db.userDao();
        this.context = context;
//        count = userDao.getCount();

    }

    void insert(User user){
        new insertAsyncUser(userDao).execute(user);
    }

    LiveData<Integer> getPoints(String userName){
        LiveData<Integer> points = userDao.getPoints(userName);
        return points;
        //        return new
    }

    LiveData<String> getCharName(String userName){
        LiveData<String> charName = userDao.getCharName(userName);
        return charName;
    }

    void setCharName(User user){new setCharNameAsyncUser(userDao).execute(user);}

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

    private class insertAsyncUser extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncUserDao;

        insertAsyncUser(UserDao dao) {
            mAsyncUserDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncUserDao.insert(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MainActivity mainActivity = (MainActivity) UserRepository.this.context;
            mainActivity.displayPoints();

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
    private static class setCharNameAsyncUser extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncUserDao;

        setCharNameAsyncUser(UserDao dao) {
            mAsyncUserDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncUserDao.setCharName(params[0].getCharName(), params[0].getUserName());
            return null;
        }
    }

}
