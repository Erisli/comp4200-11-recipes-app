package com.example.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    RecyclerAdapter adapter;
    ArrayList<String> recipeImages=new ArrayList<>();
    ArrayList<String> recipeTitles=new ArrayList<>();
    ArrayList<String> recipeDietary=new ArrayList<>();
    ArrayList<Integer> recipeServings=new ArrayList<>();
    ArrayList<Integer> recipePreparation = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String key=getResources().getString(R.string.recipes_key);
        recycler=findViewById(R.id.recyclerView);
        RequestQueue queue= Volley.newRequestQueue(this);
        String url="https://api.spoonacular.com/recipes/informationBulk?ids=715538,716429&apiKey="+key;

        JsonArrayRequest request= new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i=0;i< response.length();i++){
                                JSONObject recipe = response.getJSONObject(i);
                                Log.d("title", recipe.getString("title"));
                                recipeTitles.add(recipe.getString("title"));
                                recipeImages.add(recipe.getString("image"));
                                recipeServings.add(recipe.getInt("servings"));
                                recipePreparation.add(recipe.getInt("readyInMinutes"));
                                String dietary = getDietaryInfo(recipe);
                                recipeDietary.add(dietary);
                            }
                            Log.d("itemCount:",String.valueOf(recipeTitles.size()));
                            adapter=new RecyclerAdapter(MainActivity.this,recipeImages,recipeTitles,recipeDietary,recipeServings,recipePreparation);
                            recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            recycler.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
             });

        queue.add(request);

    }

    public String getDietaryInfo(JSONObject recipe) throws JSONException {
        String dietary="";
        if (recipe.getBoolean("vegetarian")) {
            dietary = "vegetarian: yes";
        }else{
            dietary = "vegetarian: no";
        }
        if (recipe.getBoolean("vegan")) {
            dietary += ", vegan: yes";
        }else{
            dietary += ", vegan: no";
        }
        if (recipe.getBoolean("glutenFree")) {
            dietary += ", gluten free: yes";
        }else{
            dietary += ", gluten free: no";
        }
        if (recipe.getBoolean("dairyFree")) {
            dietary += ", dairy free:yes";
        }else{
            dietary += ", dairy free:no";
        }
        return dietary;
    }
}