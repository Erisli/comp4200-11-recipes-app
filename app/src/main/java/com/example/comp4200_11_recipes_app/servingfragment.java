package com.example.comp4200_11_recipes_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class servingfragment extends Fragment {



    public servingfragment() {
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
         View v =inflater.inflate(R.layout.fragment_servingfragment, container, false);
        TextView s = v.findViewById(R.id.summarryidtxt);
        s.setText(valuessaver.sevinglist+" Servings");
        return v;
    }
}