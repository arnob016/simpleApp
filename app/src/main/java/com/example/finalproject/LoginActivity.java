package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout emailwrapper, pwwrapper;
    String passemail;
    EditText uemail, upw;
    Button signup, login;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        signup = findViewById(R.id.userSignup);
        login = findViewById(R.id.userLogin);
        uemail = findViewById(R.id.userEmail);
        upw = findViewById(R.id.userPassword);
        emailwrapper = findViewById(R.id.UserEmailWrapper);
        pwwrapper = findViewById(R.id.UserPasswordWrapper);

        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuth.getCurrentUser() != null) {
                    Toast.makeText(LoginActivity.this, "You're Already logged in - redirecting to homepage.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, Homepage.class);
                    startActivity(intent);
                } else {
                    String email = uemail.getText().toString().trim();
                    String password = upw.getText().toString().trim();

                    if (email.isEmpty()) {
                        emailwrapper.setError("Enter email");
                        emailwrapper.requestFocus();
                        return;
                    }
                    if (password.isEmpty()) {
                        pwwrapper.setError("Enter password!!!");
                        pwwrapper.requestFocus();
                        return;
                    }

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LoginActivity.this, Homepage.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Welcome "+currentFirebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                                passemail = currentFirebaseUser.getEmail();

                            } else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuth.getCurrentUser() != null) {
                    Toast.makeText(LoginActivity.this, "You're Already logged in - redirecting to homepage.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, Homepage.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                    startActivity(intent);
            }
            }
        });
    }
}