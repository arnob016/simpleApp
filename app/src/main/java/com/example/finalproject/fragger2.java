package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class fragger2 extends Fragment {
    Button add1;
    int i;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_fragger2, container, false);

        add1 = view.findViewById(R.id.addtocart3);

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i= 1;
            }
        });
    return view;
    }
}