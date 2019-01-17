package com.example.demouser.finalproject;

import android.arch.lifecycle.LiveData;
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
    private String userNameString;
    private LiveData<Integer> userCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "login create");
        setContentView(R.layout.activity_login_page);
    }



    Observer<Integer> observer = new Observer<Integer>() {
        @Override
        public void onChanged(@Nullable Integer integer) {
            final Intent intent = new Intent(LoginPage.this, MainActivity.class);
            EditText userNameText = (EditText) findViewById(R.id.loginInput);
            intent.putExtra(USER_KEY, userNameString);
            userNameString = userNameText.getText().toString();
            final User user = new User(userNameString);
            final UserRepository userRepository = new UserRepository(getApplication(), LoginPage.this);

            if (integer != 0) {
                userExists = true;
            }

            if (userExists) {
                Log.d(TAG, "user exists");
                Log.d(TAG, "starting activity from login to main, user already exists");
                userCount.removeObserver(observer);
                startActivity(intent);
            } else {
                Log.d(TAG, "no user");
                // remove the observer
                userCount.removeObserver(observer);
                userRepository.insert(user);

                //startActivity(intent);
            }
        }

    };

    public void saveButton(View view){
        Log.d(TAG, "save button");
        EditText userNameText = (EditText) findViewById(R.id.loginInput);

        userNameString = userNameText.getText().toString();
        UserRepository userRepository = new UserRepository(getApplication(), LoginPage.this);
        User user = new User(userNameString);

        userCount = userRepository.getUserCount(user.getUserName());
        userCount.observe(LoginPage.this, observer);


    }

    public String getUserName(){
        return userNameString;
    }
}
