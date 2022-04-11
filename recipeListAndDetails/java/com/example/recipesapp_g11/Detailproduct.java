package com.example.recipesapp_g11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Detailproduct extends AppCompatActivity {
    Button serving , ingradient , direction , nutrition;
    LinearLayout A,B,D;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_detailproduct);
        serving = findViewById(R.id.servingbtnid);
        direction = findViewById(R.id.directionbtnid);
        ingradient = findViewById(R.id.ingradientbtnid);
        A =findViewById(R.id.ly1);
        B =findViewById(R.id.ly2);
        D =findViewById(R.id.ly4);
        Fragment fragment = new ingredientfragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.detailefragment_id, fragment).commitAllowingStateLoss();
        A.setBackgroundColor(getResources().getColor(R.color.white));

       serving.setOnClickListener(new View.OnClickListener() {



           public void onClick(View v) {
               changecolur();
               Fragment fragment = new servingfragment();
               FragmentManager fragmentManager = getSupportFragmentManager();
               fragmentManager.beginTransaction().replace(R.id.detailefragment_id, fragment).commitAllowingStateLoss();


               D.setBackgroundColor(getResources().getColor(R.color.white));

           }
       });
        direction.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                changecolur();
                Fragment fragment = new instruction();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.detailefragment_id, fragment).commitAllowingStateLoss();
                B.setBackgroundColor(getResources().getColor(R.color.white));

            }

        });
        ingradient.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                changecolur();
                Fragment fragment = new ingredientfragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.detailefragment_id, fragment).commitAllowingStateLoss();
                A.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

    }

    private void changecolur()
    {
        A.setBackgroundColor(getResources().getColor(R.color.purple_700));
        B.setBackgroundColor(getResources().getColor(R.color.purple_700));
        D.setBackgroundColor(getResources().getColor(R.color.purple_700));


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}