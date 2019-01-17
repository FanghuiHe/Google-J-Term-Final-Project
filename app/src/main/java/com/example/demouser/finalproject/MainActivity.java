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
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // declaration of global variables for main activity class
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private TaskAdapter adapter = new TaskAdapter();
    private User user;
    private int currentPoints;

    // log tags:
    private String TAG = "tag";

    // request codes:
    private int REQUEST_TASK = 2;
    private int REQUEST_CHARNAME = 3;
    private int REQUEST_USER = 4;

    /**
     * On create method contains all code that will execute when the app is run
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        UserRepository userRepository = new UserRepository(getApplication(), this);

        Log.d(TAG, "in on result");
        String userString = getIntent().getStringExtra("user");

        // Display the user name
        TextView userNameDisplay = (TextView) findViewById(R.id.userNameDisplay);
        userNameDisplay.setText(userString);

        // new user
        user = new User(userString);

        TaskRepository taskRepository = new TaskRepository(getApplication(), userString);
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
        displayPoints();
        displayCharName();


    }

    public String getUserName(){
        return user.getUserName();
    }

    public void displayPoints(){
        UserRepository userRepository = new UserRepository(getApplication(), this);
        userRepository.getPoints(user.getUserName()).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if(integer==null){
                    return;
                }
                TextView pointView = (TextView) findViewById(R.id.points);
                pointView.setText(String.valueOf(integer));

                currentPoints = integer;
                ImageView charImage = (ImageView) findViewById(R.id.imageView);

                if(currentPoints <= 10){
                    charImage.setImageResource(R.drawable.baby_simba);
                }
                if(currentPoints > 20){
                    charImage.setImageResource(R.drawable.kid_simba);
                }
                if(currentPoints > 30){
                    charImage.setImageResource(R.drawable.teen_simba);
                }
                if(currentPoints > 40){
                    charImage.setImageResource(R.drawable.grown_simba);
                }

            }

        });


    }
    /*
        *observes the LiveData for charName adn updated view
     */
    public void displayCharName(){
        UserRepository userRepository = new UserRepository(getApplication(), this);
        userRepository.getCharName(user.getUserName()).observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String charName) {
                TextView pointView = (TextView) findViewById(R.id.charNameDisplay);
                pointView.setText(String.valueOf(charName));
            }

        });


    }

    // getter for current points
    public int getCurrentPoints(){
        return currentPoints;
    }



    // settings button information
    public void settingB(View view){
        Intent intent = new Intent(this, Settings.class);
        Log.d(TAG, "start activity for result: from main to settings");
        startActivityForResult(intent, REQUEST_CHARNAME);

    }

    // plus button information
    public void plusB(View view){
        Intent intent = new Intent(this, AddTask.class);
        Log.d(TAG, "start activity for result: from main to plus button");
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
                TaskRepository taskRepository = new TaskRepository(getApplication(), user.getUserName());
                // create the new task with the given information
                Task task = new Task(taskName,dueDate,user.getUserName());
                taskRepository.insert(task);

            }

        }else if(requestCode == REQUEST_CHARNAME){
            if (resultCode == RESULT_OK){
                // get results character name from settings page
                String charName = data.getStringExtra("charName");
                user.setCharName(charName);
                //store charName in database in table user_table
                UserRepository userRepository = new UserRepository(getApplication(), this);
                userRepository.setCharName(user);
                Log.d(TAG, charName);
            }
        }


    }

}
