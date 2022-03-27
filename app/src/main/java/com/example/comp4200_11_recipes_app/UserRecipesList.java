package com.example.comp4200_11_recipes_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserRecipesList extends AppCompatActivity {
    Button back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_recipeslist);

        back = (Button) findViewById(R.id.button);
        Intent intent = getIntent();
        String request = intent.getStringExtra("request");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(request.equals("fav")){
            Toast.makeText(this, "Favourite recipes displayed", Toast.LENGTH_SHORT).show();
            //TODO connect to database
        }else if(request.equals("todo")){
            Toast.makeText(this, "Todo recipes displayed", Toast.LENGTH_SHORT).show();
            //TODO connect to database
        }else{
            Toast.makeText(this, "Invalid request", Toast.LENGTH_SHORT).show();
        }

        //TODO recycler view and card

    }
}
