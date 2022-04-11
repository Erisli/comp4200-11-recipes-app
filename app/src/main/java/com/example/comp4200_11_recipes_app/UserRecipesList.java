package com.example.comp4200_11_recipes_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

public class UserRecipesList extends AppCompatActivity {
    RecyclerView recycler;
    RecyclerAdapter adapter;

    ArrayList<String> recipeImages=new ArrayList<>();
    ArrayList<String> recipeTitles=new ArrayList<>();
    ArrayList<String> recipeDietary=new ArrayList<>();
    ArrayList<Integer> recipeServings=new ArrayList<>();
    ArrayList<Integer> recipePreparation = new ArrayList<>();
    ArrayList<String> recipeIngredient= new ArrayList<>();
    ArrayList<String> recipeInstruction= new ArrayList<>();
    ArrayList<Integer> recipeGlutenFree= new ArrayList<>();
    ArrayList<Integer> recipeDiaryFree= new ArrayList<>();
    ArrayList<Integer> recipeVegan= new ArrayList<>();
    ArrayList<Integer> recipeVegetarian= new ArrayList<>();

    Button backButton;
    int recipeCount=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_recipeslist);

        /*back = (Button) findViewById(R.id.button);
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
        */

        final String key=getResources().getString(R.string.recipes_key);
        recycler=findViewById(R.id.recyclerView);
        backButton=findViewById(R.id.button);
        backButton.setEnabled(false);

        if((new File("/data/data/com.example.recipes/files/recipeimages.dat")).exists()){
            setRecyclerItems();
            recipeCount=recipeTitles.size();
            setRecyclerAdapter();
            Log.d("File Exists", "true");
        }else {
            String url="https://api.spoonacular.com/recipes/informationBulk?ids=715538,716429,715540,715541,715539,716430,716431,716432,716433,716434,716435,716436,716437,716439,716440,716441,716442,716443,716444,716445&apiKey="+key;
            RequestQueue queue= Volley.newRequestQueue(this);
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject recipe = response.getJSONObject(i);
                                    recipeTitles.add(recipe.getString("title"));
                                    recipeImages.add(recipe.getString("image"));
                                    recipeServings.add(recipe.getInt("servings"));
                                    recipePreparation.add(recipe.getInt("readyInMinutes"));
                                    String dietary = getDietaryInfo(recipe);
                                    recipeDietary.add(dietary);
                                }
                                recipeCount=recipeTitles.size();
                                setRecyclerAdapter();
                                saveItems();
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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearItems();
                setRecyclerItems();
                adapter.notifyDataSetChanged();
                backButton.setAlpha(0.0f);
                backButton.setEnabled(false);
            }
        });
    }

    public void setRecyclerItems() {
        recipeImages.addAll(FileHandler.readFromFile(UserRecipesList.this,"recipeimages.dat"));
        recipeTitles.addAll(FileHandler.readFromFile(UserRecipesList.this,"recipetitles.dat"));
        recipeDietary.addAll(FileHandler.readFromFile(UserRecipesList.this,"recipedietary.dat"));
        recipeServings.addAll(FileHandler.readFromFileInt(UserRecipesList.this,"recipeservings.dat"));
        recipePreparation.addAll(FileHandler.readFromFileInt(UserRecipesList.this,"recipepreparation.dat"));
    }

    public void clearItems() {
        recipeImages.removeAll(recipeImages);
        recipeTitles.removeAll(recipeTitles);
        recipeDietary.removeAll(recipeDietary);
        recipeServings.removeAll(recipeServings);
        recipePreparation.removeAll(recipePreparation);
    }

    public void saveItems(){
        FileHandler.writeToFile(UserRecipesList.this, recipeImages, "recipeimages.dat");
        FileHandler.writeToFile(UserRecipesList.this, recipeTitles, "recipetitles.dat");
        FileHandler.writeToFile(UserRecipesList.this, recipeDietary, "recipedietary.dat");
        FileHandler.writeToFileInt(UserRecipesList.this, recipeServings, "recipeservings.dat");
        FileHandler.writeToFileInt(UserRecipesList.this, recipePreparation, "recipepreparation.dat");
    }

    public void setRecyclerAdapter(){
        adapter = new RecyclerAdapter(UserRecipesList.this, recipeImages, recipeTitles, recipeDietary, recipeServings, recipePreparation,recipeIngredient,recipeInstruction);
        recycler.setLayoutManager(new LinearLayoutManager(UserRecipesList.this));
        recycler.setAdapter(adapter);
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
