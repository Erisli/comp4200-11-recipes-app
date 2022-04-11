package com.example.project_4200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    EditText id,password;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        id = findViewById(R.id.up_name);
        password = findViewById(R.id.up_pwd);
        signup = findViewById(R.id.sign_btn);

        DataBaseHelp dbHelp = new DataBaseHelp(this);
        dbHelp.getReadableDatabase();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String get_id = id.getText().toString();
                String get_pwd = password.getText().toString();
                String check=null;
                long l = dbHelp.add(get_id,get_pwd);
                if(get_id.equals("") || get_pwd.equals("")){
                    Toast.makeText(signup.this, "please enter the info", Toast.LENGTH_SHORT).show();
                }else{
                    if(l<0){
                        Toast.makeText(getApplicationContext(), "already have account", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent goto_main = new Intent(signup.this, MainActivity.class);
                        startActivity(goto_main);
                        Toast.makeText(getApplicationContext(), "Sign up success", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}