package com.example.demouser.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> tasks = new ArrayList<Task>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView taskList = (RecyclerView) findViewById(R.id.taskList);
        taskList.setLayoutManager(new LinearLayoutManager((this)));

        tasks.add(new Task("task1"));
        tasks.add(new Task("task2"));
        tasks.add(new Task("task3"));
        tasks.add(new Task("task4"));
        tasks.add(new Task("task5"));
        tasks.add(new Task("sdgsigdfkasgdsfbdsjkgfkudsfkjdshkfg"));

        taskList.setAdapter(new TaskAdapter(tasks));
    }


}
