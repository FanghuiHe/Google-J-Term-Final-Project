package com.example.demouser.finalproject;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // declaration of global variables for main activity class
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private TaskAdapter adapter = new TaskAdapter();
    private User user;

    // log tags:
    private String TAG = "tag";

    // request codes:
    private int REQUEST_TASK = 2;
    private int REQUEST_CHARNAME = 3;

    /**
     * On create method contains all code that will execute when the app is run
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserRepository userRepository = new UserRepository(getApplication());

        // new user
        user = new User("Henry");
        Log.d(TAG, user.getCharName());

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
        RecyclerView taskList = (RecyclerView) findViewById(R.id.taskList);
        taskList.setLayoutManager(new LinearLayoutManager((this)));

        taskList.setAdapter(adapter);

    }

    // settings button information
    public void settingB(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivityForResult(intent, REQUEST_CHARNAME);

    }

    // plus button information
    public void plusB(View view){
        Intent intent = new Intent(this, AddTask.class);
        startActivityForResult(intent, REQUEST_TASK);

    }

    // getting results from settings and adding tasks
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_TASK){
            if(resultCode == RESULT_OK){
                // task name
                String taskName = data.getStringExtra("Task Name");
                // due date
                String dueDate = data.getStringExtra("Due Date");
                // logs
                Log.d(TAG, taskName);
                Log.d(TAG, dueDate);
                // add the task to the task repository
                TaskRepository taskRepository = new TaskRepository(getApplication());
                // create the new task with the given information
                Task task = new Task(taskName,dueDate);
                taskRepository.insert(task);

            }

        }else if(requestCode == REQUEST_CHARNAME){
            if (resultCode == RESULT_OK){
                // get results character name from settings page
                String charName = data.getStringExtra("charName");
                Log.d(TAG, charName);
                
            }
        }

    }

}
