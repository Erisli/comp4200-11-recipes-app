package com.example.recipesapp_g11;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ingredientfragment extends Fragment {


    RecyclerView recyclerView;
    ingradienrecycleradapter adapter;
    ArrayList<String> namelist=new ArrayList<>();
    ArrayList<String> vollist=new ArrayList<>();
    public ingredientfragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ingredientfragment, container, false);
         recyclerView = v.findViewById(R.id.ingradient_recycclerid);
        JSONArray jsonArray = valuessaver.ingarray;
            for(int j = 0; j < jsonArray.length(); j++) {


                    JSONObject jsonobject = null;
                    try {
                        jsonobject = jsonArray.getJSONObject(j);
                        String title = jsonobject.getString("name");
                        namelist.add(title);
                        String vol = jsonobject.getString("original");
                        vollist.add(vol);
                        Log.d("ingradientfrag",title);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            Log.d("fragsize", String.valueOf(namelist.size()));
        adapter=new ingradienrecycleradapter(getContext(),namelist,vollist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(adapter);
        return v;
    }
}