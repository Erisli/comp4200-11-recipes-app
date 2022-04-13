package com.example.comp4200_11_recipes_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    Button home,profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        home = findViewById(R.id.btn_list);
        profile=findViewById(R.id.btn_profile);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goto_list = new Intent(HomePage.this, RecipeList.class);
                startActivity(goto_list);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goto_profile = new Intent(HomePage.this, UserProfile.class);
                Intent intent = getIntent();
                String userName = intent.getStringExtra("userName");
                goto_profile.putExtra("userName", userName);
                startActivity(goto_profile);
            }
        });
    }


}
