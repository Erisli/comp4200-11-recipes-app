package com.example.comp4200_11_recipes_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfile extends AppCompatActivity {
    TextView TextUserName;
    TextView favourite;
    TextView todoList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        TextUserName = (TextView) findViewById(R.id.TextUserName);
        favourite = (TextView) findViewById(R.id.favourite);
        todoList = (TextView) findViewById(R.id.todoList);


        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");

        TextUserName.setText(userName);

    }

}
