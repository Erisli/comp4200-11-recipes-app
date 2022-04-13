package com.example.comp4200_11_recipes_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfile extends AppCompatActivity {
    TextView TextUserName;
    EditText editPwd;
    Button btnChange;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        DataBaseHelp dbHelp = new DataBaseHelp(this);
        dbHelp.getReadableDatabase();

        TextUserName = (TextView) findViewById(R.id.TextUserName);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        editPwd = findViewById(R.id.editPwd);
        btnChange = findViewById(R.id.btn_change);
        TextUserName.setText(userName);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = editPwd.getText().toString();
                if(!pwd.equals("")) {
                    if(dbHelp.changepwd(userName,pwd) != 0){
                        Toast.makeText(UserProfile.this, "Password successfully changed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(UserProfile.this, "Empty password!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
