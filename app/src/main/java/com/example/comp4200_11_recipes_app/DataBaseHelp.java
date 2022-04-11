package com.example.comp4200_11_recipes_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelp extends SQLiteOpenHelper {


    public DataBaseHelp(@Nullable Context context) {
        super(context, "project_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE account (_id Varchar(10) primary key, pwd Varchar(20))";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS account";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public long add(String id,String data){
        SQLiteDatabase sqldata = this.getWritableDatabase();
        ContentValues conn = new ContentValues();
        conn.put("_id",id);
        conn.put("pwd",data);
        return sqldata.insert("account",null,conn);
    }

    public Cursor checklogin(String id){
        SQLiteDatabase sqldata = this.getWritableDatabase();
        Cursor cursor = sqldata.rawQuery("SELECT pwd FROM account Where _id= ?" , new String[]{id});
        return cursor;
    }
}
