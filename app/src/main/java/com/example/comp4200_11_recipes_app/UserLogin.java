package com.example.comp4200_11_recipes_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserLogin extends AppCompatActivity {
    EditText view_username, view_password;
    String testUsername = "example", testPassword = "123";
    String username, password;
    Button btn_login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        view_username = findViewById(R.id.edit_username);
        view_password = findViewById(R.id.edit_password);
        btn_login = findViewById(R.id.button_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean good = getAccount();
                if(good){
                    //TODO start UserProfile activity, pass username
                    Intent intent = new Intent(v.getContext(), UserProfile.class);
                    intent.putExtra("userName", username);
                    startActivity(intent);
                }else{
                    Toast.makeText(v.getContext(), "Incorrect password or username entered!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected boolean getAccount(){
        username = view_username.getText().toString();
        password = view_password.getText().toString();

        //TODO if username in database, check password
        if(username.equals(testUsername)){
            if(password.equals(testPassword)){
                return true;
            }else{
                return false;
            }
        }
        //TODO if username not in database,
        else{
            //create account with current password
            return true;
        }
    }
}
