package com.example.demouser.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity {
//  the key for character name info
    private String CHARNAME_KEY = "charName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }
//  handler for save button in the setting page. Transfer character name back to the main page
    public void saveButton(View view){
        Intent intent = new Intent();
        EditText charNameText = (EditText) findViewById(R.id.charNameInput);
        intent.putExtra(CHARNAME_KEY, charNameText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();

    }
//  handler for cancel button
    public void cancelButton(View view){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }




}
