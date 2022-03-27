package com.example.comp4200_11_recipes_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfile extends AppCompatActivity {
    TextView TextUserName;
    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        listView = (ListView) findViewById(R.id.listview);
        String[] featuresArray = {"-  Favourite","-  Todo List"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.features_listview, featuresArray);

        TextUserName = (TextView) findViewById(R.id.TextUserName);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");

        TextUserName.setText(userName);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if(position == 0){
                    Intent intent = new Intent(view.getContext(), UserRecipesList.class);
                    intent.putExtra("request", "fav");
                    startActivity(intent);
                }else if(position == 1){
                    Intent intent = new Intent(view.getContext(), UserRecipesList.class);
                    intent.putExtra("request", "todo");
                    startActivity(intent);
                }

            }

        });
        /*favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserRecipesList.class);
                intent.putExtra("request", "fav");
                startActivity(intent);

            }
        });

        todoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserRecipesList.class);
                intent.putExtra("request", "todo");
                startActivity(intent);

            }
        });*/

    }

}
