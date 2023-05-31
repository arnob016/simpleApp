package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class fragger1 extends Fragment {
    Button cart,add1,add2;
    int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragger1, container, false);

        add1 = view.findViewById(R.id.addtocart);
        add2 = view.findViewById(R.id.addtocart2);

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i= 1;
            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i= 2;
            }
        });






        return view;
    }
}