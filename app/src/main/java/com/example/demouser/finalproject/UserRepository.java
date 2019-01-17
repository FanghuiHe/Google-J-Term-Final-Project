package com.example.demouser.finalproject;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;


public class UserRepository {
    private UserDao userDao;
    private int count;
    private Context context;
    private String USER_KEY = "user";
    private String TAG = "tag";



    public UserRepository(Application application, Context context){
        TaskDatabase db = TaskDatabase.getDatabase(application);
        userDao = db.userDao();
        this.context = context;
//        count = userDao.getCount();

    }

    void insert(User user){
        new insertAsyncUser(userDao, user.getUserName()).execute(user);
    }

    LiveData<Integer> getPoints(String userName){
        LiveData<Integer> points = userDao.getPoints(userName);
        return points;
    }

    LiveData<String> getCharName(String userName){
        LiveData<String> charName = userDao.getCharName(userName);
        return charName;
    }

    LiveData<Integer> getUserCount(String userName){
        LiveData<Integer> userCount = userDao.getUserCount(userName);
        return userCount;
    }

    void setCharName(User user){new setCharNameAsyncUser(userDao).execute(user);}

    void setPoints(User user){
        new setPointsAsyncUser(userDao).execute(user);
    }

    private class insertAsyncUser extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncUserDao;
        private String userName;

        insertAsyncUser(UserDao dao, String userName) {
            mAsyncUserDao = dao;
            this.userName = userName;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncUserDao.insert(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            LoginPage loginPage = (LoginPage) UserRepository.this.context;

            Intent intent = new Intent(loginPage, MainActivity.class);
            intent.putExtra(USER_KEY, userName);
            Log.d(TAG, "start activity from login to main after adding user");
            loginPage.startActivity(intent);

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
