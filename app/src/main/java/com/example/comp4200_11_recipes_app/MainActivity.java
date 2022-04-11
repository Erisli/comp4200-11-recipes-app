package com.example.comp4200_11_recipes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login,signup;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login_btn);
        username = findViewById(R.id.up_name);
        password = findViewById(R.id.up_pwd);
        signup = findViewById(R.id.sign_btn);

        DataBaseHelp dbHelp = new DataBaseHelp(this);
        dbHelp.getReadableDatabase();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goto_sign = new Intent(MainActivity.this, UserSignup.class);
                startActivity(goto_sign);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = username.getText().toString();
                String pwd = password.getText().toString();

                if(!id.equals("") || !pwd.equals("")) {
                    Cursor cursor = dbHelp.checklogin(id);
                    if (cursor.moveToNext() && cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        if (pwd.equals(cursor.getString(0))) {//when success to login here go new activity.
                            Intent goto_newpage = new Intent(MainActivity.this, HomePage.class);//newpage is new activity name
                            goto_newpage.putExtra("username", id);
                            startActivity(goto_newpage);
                        } else {
                            Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                        }
                        cursor.close();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Please enter the info", Toast.LENGTH_SHORT).show();
                }

                /*try{//database use the java to connect school database
                    Class.forName("com.mysql.jdbc.Driver");
                    Log.d("Zhao", "Driver loaded success");
                }catch(ClassNotFoundException e){
                    Log.d("Zhao", "Driver loaded fail");
                    e.printStackTrace() ;
                }
                //if want to run on school system use the localhost
                String url = "jdbc:mysql://myweb.cs.uwindsor.ca:2222/zhao16q_andrio" ;
                String username = "zhao16q_project" ;
                String password = "123456" ;
                Connection con= null;
                Statement stmt  = null;

                try{
                    con = DriverManager.getConnection(url , username , password ) ;
                    Log.d("Zhao", "success to connect");

                    stmt  = con.createStatement();

                    String sql = "select * from user_info";
                    ResultSet rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        Log.d("zhao",rs.getInt(1)+"\t");
                        Log.d("zhao",rs.getInt(2)+"\t");
                        Log.d("zhao",rs.getInt(3)+"\t");
                    }


                }catch(SQLException se){
                    Log.d("Zhao", "fail to connect");
                    se.printStackTrace() ;
                }*/

            }
        });
    }
}