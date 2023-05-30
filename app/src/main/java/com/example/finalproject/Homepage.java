package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;


public class Homepage extends AppCompatActivity {
    ViewFlipper bannerimg;
    Button logout;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        bannerimg = findViewById(R.id.bannerimg);

        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.signout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(Homepage.this, MainActivity.class);
                startActivity(intent);
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