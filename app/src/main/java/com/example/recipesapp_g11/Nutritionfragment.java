package com.example.recipesapp_g11;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Nutritionfragment extends Fragment {



    public Nutritionfragment() {
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
        View v =inflater.inflate(R.layout.fragment_nutritionfragment, container, false);

        TextView textView = v.findViewById(R.id.nutritiontexttext_id);
        textView.setText(valuessaver.nutritionlist);

        return v;
    }
}