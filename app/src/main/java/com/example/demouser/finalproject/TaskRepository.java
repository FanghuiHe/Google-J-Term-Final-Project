package com.example.demouser.finalproject;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> tasks;
    private double timeWorkedOn;
    private String userName;

//((MainActivity) (application.getApplicationContext())

    public TaskRepository(Application application, String userName) {
        TaskDatabase db = TaskDatabase.getDatabase(application);
        taskDao = db.taskDao();
        this.userName = userName;
        tasks = taskDao.getTasksByDateAsc(userName);

    }

    LiveData<List<Task>> getTasks() {
        return tasks;
    }

    public void setTime(Task task){
        new setTimeAsyncTask(taskDao).execute(task);
    }

    double getTime(Task task){
        timeWorkedOn = task.getTimeWorked();
        return timeWorkedOn;
    }

    public void insert(Task task) {
        new insertAsyncTask(taskDao).execute(task);
    }

    public void delete(Task task) {
        new deleteAsyncTask(taskDao).execute(task);
    }


    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao mAsyncTaskDao;

        insertAsyncTask(TaskDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao mAsyncTaskDao;

        deleteAsyncTask(TaskDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }


    }

    private static class setTimeAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao mAsyncTaskDao;

        setTimeAsyncTask(TaskDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.setTime(params[0].getTimeWorked(),params[0].getId());
            return null;
        }
    }



}
