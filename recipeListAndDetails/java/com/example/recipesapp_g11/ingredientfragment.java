package com.example.recipesapp_g11;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ingredientfragment extends Fragment {


    TextView textView;

    public ingredientfragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate return
        View v =inflater.inflate(R.layout.fragment_ingredientfragment, container, false);
        textView = v.findViewById(R.id.txtIngredient);
        textView.setText(valuessaver.ingredients);
        return v;

    }
}