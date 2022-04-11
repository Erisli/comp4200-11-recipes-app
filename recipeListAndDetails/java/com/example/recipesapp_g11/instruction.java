package com.example.recipesapp_g11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


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