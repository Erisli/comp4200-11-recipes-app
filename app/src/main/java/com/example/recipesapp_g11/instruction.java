package com.example.recipesapp_g11;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class instruction extends Fragment {

    TextView textView;

    public instruction() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate return
                View v =inflater.inflate(R.layout.fragment_instruction, container, false);
                textView = v.findViewById(R.id.instructuiontxt_id);
                textView.setText(valuessaver.instructionlist);
       return v;

    }
}