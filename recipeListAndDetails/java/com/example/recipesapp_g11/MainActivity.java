package com.example.recipesapp_g11;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

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

    SearchView search;
    Button backButton,btnFilter;
    int recipeCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String key="";
        recycler=findViewById(R.id.recyclerView);
        search=findViewById(R.id.searchView);
        backButton=findViewById(R.id.backButton);
        btnFilter=findViewById(R.id.btnFilter);
        backButton.setEnabled(false);
        btnFilter.setEnabled(true);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFilterOptions();
            }
        });

        if((new File("/data/data/com.example.recipes/files/recipeimages.dat")).exists()){
            setRecyclerItems();
            recipeCount=recipeTitles.size();
            setRecyclerAdapter();
            Log.d("File Exists", "true");
        }else {
            //     String url="https://run.mocky.io/v3/f498cb94-7c8e-4a23-9170-ed94684d0d71";
         String url="https://api.spoonacular.com/recipes/informationBulk?ids=715538,716429,715540,715541,715539,716430,716431,716432,716433,716434,716435,716436,716437,716439,716440,716441,716442,716443,716444,716445&apiKey="+key;
            RequestQueue queue= Volley.newRequestQueue(this);
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                               recipeImages=new ArrayList<>();
                               recipeTitles=new ArrayList<>();
                               recipeDietary=new ArrayList<>();
                               recipeServings=new ArrayList<>();
                               recipePreparation = new ArrayList<>();
                               recipeIngredient = new ArrayList<>();
                               recipeInstruction = new ArrayList<>();
                               recipeGlutenFree= new ArrayList<>();
                               recipeDiaryFree= new ArrayList<>();
                               recipeVegan= new ArrayList<>();
                               recipeVegetarian= new ArrayList<>();

                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject recipe = response.getJSONObject(i);
                                    JSONArray jsonArray = recipe.getJSONArray("extendedIngredients");
                                    String ingredients="";
                                    for(int j=0; j<jsonArray.length(); j++){
                                        JSONObject ingredient = jsonArray.getJSONObject(j);
                                        ingredients+=ingredient.getString("name");
                                        if(j!=jsonArray.length()-1){
                                            ingredients+=", ";
                                        }
                                    }
                                    recipeIngredient.add(ingredients);
                                    recipeTitles.add(recipe.getString("title"));
                                    recipeInstruction.add(recipe.getString("instructions"));
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
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(recipeTitles.size()<recipeCount) {
                    clearItems();
                    setRecyclerItems();
                }

                for(int i=0;i<recipeTitles.size();i++){
                    if(recipeTitles.get(i).toLowerCase().indexOf(s.toLowerCase())!=-1){
                        continue;
                    }else {
                        recipeImages.remove(i);
                        recipeTitles.remove(i);
                        recipeDietary.remove(i);
                        recipeServings.remove(i);
                        recipePreparation.remove(i);
                        i--;
                    }
                }

                if(recipeTitles.size()!=0){
                    backButton.setAlpha(1.0f);
                    backButton.setEnabled(true);
                }

                adapter.notifyDataSetChanged();
                search.setQuery("",false);
                search.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearItems();
                setRecyclerItems();
                adapter.notifyDataSetChanged();
                backButton.setAlpha(0.0f);
                backButton.setEnabled(false);
                btnFilter.setVisibility(View.VISIBLE);
                btnFilter.setEnabled(true);
            }
        });
    }

    public void setRecyclerItems() {
        recipeImages.addAll(FileHandler.readFromFile(MainActivity.this,"recipeimages.dat"));
        recipeTitles.addAll(FileHandler.readFromFile(MainActivity.this,"recipetitles.dat"));
        recipeDietary.addAll(FileHandler.readFromFile(MainActivity.this,"recipedietary.dat"));
        recipeInstruction.addAll(FileHandler.readFromFile(MainActivity.this,"recipeInstruction.dat"));

        recipeServings.addAll(FileHandler.readFromFileInt(MainActivity.this,"recipeservings.dat"));
        recipePreparation.addAll(FileHandler.readFromFileInt(MainActivity.this,"recipepreparation.dat"));

        recipeVegan.addAll(FileHandler.readFromFileInt(MainActivity.this,"recipeVegan.dat"));
        recipeVegetarian.addAll(FileHandler.readFromFileInt(MainActivity.this,"recipeVegetarian.dat"));
        recipeGlutenFree.addAll(FileHandler.readFromFileInt(MainActivity.this,"recipeGlutenFree.dat"));
        recipeDiaryFree.addAll(FileHandler.readFromFileInt(MainActivity.this,"recipeDiaryFree.dat"));


    }

    public void clearItems() {
        recipeImages.removeAll(recipeImages);
        recipeTitles.removeAll(recipeTitles);
        recipeDietary.removeAll(recipeDietary);
        recipeServings.removeAll(recipeServings);
        recipePreparation.removeAll(recipePreparation);
        recipeInstruction.removeAll(recipeInstruction);

        recipeGlutenFree.removeAll(recipeDiaryFree);
        recipeDiaryFree.removeAll(recipeDiaryFree);
        recipeVegetarian.removeAll(recipeVegetarian);
        recipeVegan.removeAll(recipeVegan);
    }

    public void saveItems(){
        FileHandler.writeToFile(MainActivity.this, recipeImages, "recipeimages.dat");
        FileHandler.writeToFile(MainActivity.this, recipeTitles, "recipetitles.dat");
        FileHandler.writeToFile(MainActivity.this, recipeDietary, "recipedietary.dat");
        FileHandler.writeToFile(MainActivity.this, recipeInstruction, "recipeInstruction.dat");

        FileHandler.writeToFileInt(MainActivity.this, recipeServings, "recipeservings.dat");
        FileHandler.writeToFileInt(MainActivity.this, recipePreparation, "recipepreparation.dat");

        FileHandler.writeToFileInt(MainActivity.this, recipeGlutenFree, "recipeGlutenFree.dat");
        FileHandler.writeToFileInt(MainActivity.this, recipeDiaryFree, "recipeDiaryFree.dat");
        FileHandler.writeToFileInt(MainActivity.this, recipeVegan, "recipeVegan.dat");
        FileHandler.writeToFileInt(MainActivity.this, recipeVegetarian, "recipeVegetarian.dat");
    }

    public void setRecyclerAdapter(){
        adapter = new RecyclerAdapter(MainActivity.this, recipeImages, recipeTitles, recipeDietary, recipeServings, recipePreparation,recipeIngredient,recipeInstruction);
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recycler.setAdapter(adapter);
    }
    public String getDietaryInfo(JSONObject recipe) throws JSONException {
        String dietary="";
        if (recipe.getBoolean("vegetarian")) {
            dietary = "vegetarian: yes";
            recipeVegetarian.add(1);
        }else{
            dietary = "vegetarian: no";
            recipeVegetarian.add(0);
        }
        if (recipe.getBoolean("vegan")) {
            dietary += ", vegan: yes";
            recipeVegan.add(1);
        }else{
            dietary += ", vegan: no";
            recipeVegan.add(0);
        }
        if (recipe.getBoolean("glutenFree")) {
            recipeGlutenFree.add(1);
            dietary += ", gluten free: yes";
        }else{
            dietary += ", gluten free: no";
            recipeGlutenFree.add(0);
        }
        if (recipe.getBoolean("dairyFree")) {
            dietary += ", dairy free:yes";
            recipeDiaryFree.add(1);
        }else{
            dietary += ", dairy free:no";
            recipeDiaryFree.add(0);
        }
        return dietary;
    }

    private void showFilterOptions(){
        String[] colors = {"Gluten Free", "Dairy Free", "Vegan", "Vegetarian"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a filter option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(recipeTitles.size()<recipeCount) {
                    clearItems();
                    setRecyclerItems();
                }

                if(which==0){
                    for(int i=0;i<recipeGlutenFree.size();i++){
                        if(recipeGlutenFree.get(i)==1){
                            continue;
                        }else {
                            recipeImages.remove(i);
                            recipeTitles.remove(i);
                            recipeDietary.remove(i);
                            recipeServings.remove(i);
                            recipePreparation.remove(i);
                            recipeGlutenFree.remove(i);
                            recipeInstruction.remove(i);
                            recipeDiaryFree.remove(i);
                            recipeVegan.remove(i);
                            recipeVegetarian.remove(i);
                            i--;
                        }
                    }
                }
                else  if(which==1){
                    for(int i=0;i<recipeDiaryFree.size();i++){
                        if(recipeDiaryFree.get(i)==1){
                            continue;
                        }else {
                            recipeImages.remove(i);
                            recipeTitles.remove(i);
                            recipeDietary.remove(i);
                            recipeServings.remove(i);
                            recipePreparation.remove(i);
                            recipeInstruction.remove(i);
                            recipeGlutenFree.remove(i);
                            recipeDiaryFree.remove(i);
                            recipeVegan.remove(i);
                            recipeVegetarian.remove(i);
                            i--;
                        }
                    }
                }
                else  if(which==2){
                    for(int i=0;i<recipeVegan.size();i++){
                        if(recipeVegan.get(i)==1){
                            continue;
                        }else {
                            recipeImages.remove(i);
                            recipeTitles.remove(i);
                            recipeDietary.remove(i);
                            recipeServings.remove(i);
                            recipePreparation.remove(i);
                            recipeInstruction.remove(i);
                            recipeGlutenFree.remove(i);
                            recipeDiaryFree.remove(i);
                            recipeVegan.remove(i);
                            recipeVegetarian.remove(i);
                            i--;
                        }
                    }
                }

                else  if(which==3){
                    for(int i=0;i<recipeVegetarian.size();i++){
                        if(recipeVegetarian.get(i)==1){
                            continue;
                        }else {
                            recipeImages.remove(i);
                            recipeTitles.remove(i);
                            recipeDietary.remove(i);
                            recipeServings.remove(i);
                            recipePreparation.remove(i);
                            recipeInstruction.remove(i);
                            recipeGlutenFree.remove(i);
                            recipeDiaryFree.remove(i);
                            recipeVegan.remove(i);
                            recipeVegetarian.remove(i);
                            i--;
                        }
                    }
                }




                if(recipeTitles.size()!=0){
                    backButton.setAlpha(1.0f);
                    backButton.setEnabled(true);

                    btnFilter.setVisibility(View.INVISIBLE);
                    btnFilter.setEnabled(false);

                }

                adapter.notifyDataSetChanged();
                search.setQuery("",false);
                search.clearFocus();



            }
        });
        builder.show();
    }
}