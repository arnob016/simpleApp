package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Homepage extends AppCompatActivity {
    MainActivity main;
    User user;
    ViewFlipper bannerimg;
    String ufname1;
    Button logout;
    RadioGroup rg;
    FirebaseAuth mAuth;
    TextView unameshow;
    private DatabaseReference mDatabase;

    void loadFragment(Fragment fn){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragger, fn).commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        String nameuser = FirebaseAuth.getInstance().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        bannerimg = findViewById(R.id.bannerimg);
        rg = findViewById(R.id.rdgrp);
        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.signout);
        unameshow = findViewById(R.id.userfname);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(Homepage.this, MainActivity.class);
                startActivity(intent);
            }
        });


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==1){
                    loadFragment(new fragger1());
                }
                else if(i==3){
                    loadFragment(new fragger3());
                }
                else if(i==2){
                    loadFragment(new fragger2());
                }
            }
        });





        int slider[]={
                R.drawable.banner1,
                R.drawable.banner2,
                R.drawable.banner3
        };
        for(int slide:slider){
            flipperr(slide);
        }

    }

    public void flipperr(int image){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(image);
        bannerimg.addView(imageView);
        bannerimg.setFlipInterval(4000);
        bannerimg.setAutoStart(true);
        bannerimg.setInAnimation(this, android.R.anim.fade_in);
        bannerimg.setOutAnimation(this, android.R.anim.fade_out);
    }
}