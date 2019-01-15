package com.example.demouser.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddTask extends AppCompatActivity {


    private String TASKNAME_KEY = "Task Name";
    private String DATE_KEY = "Due Date";
    private String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    public void cancelB(View view){
        Intent cIntent = new Intent();
        setResult(RESULT_CANCELED, cIntent);
        finish();
    }

    public void createTask(View view){
        Intent createIntent = new Intent();
        EditText taskPrompt = (EditText) findViewById(R.id.taskPrompt);
        EditText dueDate = (EditText) findViewById(R.id.datePrompt);
        createIntent.putExtra(TASKNAME_KEY, taskPrompt.getText().toString());
        createIntent.putExtra(DATE_KEY, dueDate.getText().toString());

        Log.d(TAG, taskPrompt.getText().toString());
        Log.d(TAG, dueDate.getText().toString());

        setResult(RESULT_OK, createIntent);
        finish();

    }


}
