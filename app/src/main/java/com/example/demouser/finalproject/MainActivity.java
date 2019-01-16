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

    private ArrayList<Task> tasks = new ArrayList<Task>();
    private TaskAdapter adapter = new TaskAdapter();
    private int REQUEST_TASK = 2;
    private String TAG = "tag";
    private User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserRepository userRepository = new UserRepository(getApplication());

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

    public void plusB(View view){
        Intent intent = new Intent(this, AddTask.class);
        startActivityForResult(intent, REQUEST_TASK);

    }





    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_TASK){
            if(resultCode == RESULT_OK){
                // get results
                String taskName = data.getStringExtra("Task Name");
                String dueDate = data.getStringExtra("Due Date");
                Log.d(TAG, taskName);
                Log.d(TAG, dueDate);
//                Task task = new Task(taskName,dueDate);
                TaskRepository taskRepository = new TaskRepository(getApplication());
                Task task = new Task(taskName,dueDate);
                taskRepository.insert(task);
                //taskRepository.delete(task);
            }
        }

    }

    /**public TaskRepository getTaskRepository() {
     return taskRepository;
     }
     public void setTaskRepository(TaskRepository taskRepository){
     this.taskRepository=taskRepository;
     }*/
}
