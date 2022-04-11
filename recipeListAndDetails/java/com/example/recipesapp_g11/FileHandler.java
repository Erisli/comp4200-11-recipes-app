package com.example.recipesapp_g11;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler {

    public static void writeToFile(Context context, ArrayList<String> arrayItems, String fileName){
        try{
            FileOutputStream fileOutputStream=context.openFileOutput(fileName, Context.MODE_PRIVATE);//open file
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);//output stream
            objectOutputStream.writeObject(arrayItems);//write object to file
            objectOutputStream.close();//close file
        }catch(FileNotFoundException e){//exception
            e.printStackTrace();
        }catch(IOException e){//exception
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readFromFile(Context context,String fileName){
        ArrayList array= new ArrayList<String>();
        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);//open file
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);//input stream
            array=  (ArrayList<String>) objectInputStream.readObject();//read from file
        }catch(FileNotFoundException e){//exception
            e.printStackTrace();
        }catch(IOException e){//exception
            e.printStackTrace();
        }catch (ClassNotFoundException e){//exception
            e.printStackTrace();
        }

        return array;//return arraylist
    }

    public static void writeToFileInt(Context context, ArrayList<Integer> arrayItems, String fileName){
        try{
            FileOutputStream fileOutputStream=context.openFileOutput(fileName, Context.MODE_PRIVATE);//open file
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);//output stream
            objectOutputStream.writeObject(arrayItems);//write object to file
            objectOutputStream.close();//close file
        }catch(FileNotFoundException e){//exception
            e.printStackTrace();
        }catch(IOException e){//exception
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> readFromFileInt(Context context,String fileName){
        ArrayList array= new ArrayList<Integer>();
        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);//open file
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);//input stream
            array=  (ArrayList<Integer>) objectInputStream.readObject();//read from file
        }catch(FileNotFoundException e){//exception
            e.printStackTrace();
        }catch(IOException e){//exception
            e.printStackTrace();
        }catch (ClassNotFoundException e){//exception
            e.printStackTrace();
        }

        return array;//return arraylist
    }
}

