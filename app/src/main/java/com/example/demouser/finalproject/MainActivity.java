package com.example.demouser.finalproject;

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

//    private static TaskRepository taskRepository = new TaskRepository(getApplication());

    // Maha: comment

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
//                taskRepository.deleteTask(task.getId());
            }
        }

    }

    public void doneButton(View view){
//        MainActivity mainActivity = MainActivity.this;
        TaskDatabase db = TaskDatabase.getDatabase(getApplication());
        TaskDao taskDao = db.taskDao();
//        tasks = taskDao.getTasksByDateAsc();
//        TaskRepository taskRepository = new TaskRepository(getApplication());
        TaskHolder taskHolder = (TaskHolder) view.getParent();
        Task task = taskHolder.getTask();
        taskDao.delete(task);
    }

}
