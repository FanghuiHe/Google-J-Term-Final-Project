package com.example.demouser.finalproject;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> tasks = new ArrayList<Task>();
    private TaskAdapter adapter = new TaskAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskRepository taskRepository = new TaskRepository(getApplication());
        taskRepository.getTasks().observe(
                this,
                new Observer<List<Task>>() {
                    @Override
                    public void onChanged(@Nullable List<Task> tasks) {
                        adapter.setTasks(tasks);
                    }
                }
        );
        /**taskRepository.insert(new Task("task1",0));
         taskRepository.insert(new Task("task2",1));
         taskRepository.insert(new Task("task3",0));
         taskRepository.insert(new Task("task4",1));
         taskRepository.insert(new Task("task5",0));
         taskRepository.insert(new Task("task6",1));
         taskRepository.insert(new Task("task7",0));
         taskRepository.insert(new Task("task8",1));
        taskRepository.insert(new Task("task9",0));
        finish();*/


        RecyclerView taskList = (RecyclerView) findViewById(R.id.taskList);
        taskList.setLayoutManager(new LinearLayoutManager((this)));
        /**tasks.add(new Task("task1",0));
        tasks.add(new Task("task2", 1));
        tasks.add(new Task("task3",0));
        tasks.add(new Task("task4", 1));
        tasks.add(new Task("task5", 0));
        tasks.add(new Task("sdgsigdfkasgdsfbdsjkgfkudsfkjdshkfg", 1));*/
        taskList.setAdapter(adapter);

    }


}
