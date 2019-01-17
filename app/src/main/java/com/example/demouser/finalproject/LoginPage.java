package com.example.demouser.finalproject;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    private String USER_KEY= "user";
    private boolean userExists = false;
    private String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "login create");
        setContentView(R.layout.activity_login_page);
    }

    public void saveButton(View view){
        Log.d(TAG, "save button");
        Intent intent = new Intent();
        EditText userNameText = (EditText) findViewById(R.id.loginInput);
        String userNameString = userNameText.getText().toString();
        User user = new User(userNameString);
        intent.putExtra(USER_KEY, userNameString);
        setResult(RESULT_OK, intent);

        UserRepository userRepository = new UserRepository(getApplication(), this);
        userRepository.getUserCount(user.getUserName()).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if(integer != 0) {
                    userExists = true;
                }
            }

        });

        if(userExists){
            Log.d(TAG, "user exists");
            finish();
        }else{
            Log.d(TAG, "no user");
            userRepository.insert(user);
        }

    }
}
