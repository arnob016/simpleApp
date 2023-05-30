package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    Button signin, rsignup;
    EditText ufname, ulname, upassword, uconfpassword, uemail;
    TextInputLayout ufnwrapper,ulnwrapper, uemwrapper, upwwrapper, uconfpwwrapper;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        signin = findViewById(R.id.userSignin);
        rsignup = findViewById(R.id.ruserSignup);
        ufname = findViewById(R.id.userFirstName);
        ulname = findViewById(R.id.userLastName);
        uemail = findViewById(R.id.userEmailAddress);
        upassword = findViewById(R.id.userPassword);
        uconfpassword = findViewById(R.id.confpassword);


        ufnwrapper = findViewById(R.id.userFirstNameWrapper);
        ulnwrapper = findViewById(R.id.userLastNameWrapper);
        uemwrapper = findViewById(R.id.userEmailAddressWrapper);
        upwwrapper = findViewById(R.id.PasswordWrapper);
        uconfpwwrapper = findViewById(R.id.confPasswordWrapper);
        rsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getCurrentUser()!= null){
                    Toast.makeText(SignupActivity.this, "You're Already logged in - redirecting to homepage.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, Homepage.class);
                    startActivity(intent);
                }else {


                    String firstname = ufname.getText().toString().trim();
                    String lastname = ulname.getText().toString().trim();
                    String email = uemail.getText().toString().trim();
                    String password = upassword.getText().toString().trim();
                    String confpassword = uconfpassword.getText().toString().trim();


                    if (firstname.isEmpty()) {
                        ufnwrapper.setError("Enter Firstname");
                        ufnwrapper.requestFocus();
                        return;
                    }
                    if (lastname.isEmpty()) {
                        ulnwrapper.setError("Enter Lastname");
                        ulnwrapper.requestFocus();
                        return;
                    }
                    if (email.isEmpty()) {
                        uemail.setError("Enter email");
                        uemail.requestFocus();
                        return;
                    }
                    if (password.isEmpty()) {
                        upwwrapper.setError("Enter Password");
                        upwwrapper.requestFocus();
                        return;
                    }
                    if (!password.equals(confpassword)) {
                        uconfpwwrapper.setError("Password didn't match :( Comeon bro! ");
                        uconfpwwrapper.requestFocus();
                        return;
                    }
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User user = new User(firstname, lastname, email);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignupActivity.this, "New user created! (welcome to my app)", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SignupActivity.this, Homepage.class);
                                            startActivity(intent);

                                        } else {
                                            Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                        });
                            }else{
                                Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }




        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuth.getCurrentUser() != null) {
                    Toast.makeText(SignupActivity.this, "You're Already logged in - redirecting to homepage.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, Homepage.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
