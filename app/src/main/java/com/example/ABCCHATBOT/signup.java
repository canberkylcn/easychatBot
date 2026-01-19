package com.example.ABCCHATBOT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    public Button back_button;
    Button sign_up;
    EditText Email,passwordUser;

    FirebaseAuth mAuth;
    ProgressBar progressBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        back_button = findViewById(R.id.button_back);
        Email = findViewById(R.id.textView2);
        passwordUser = findViewById(R.id.editTextTextPassword2);
        sign_up = findViewById(R.id.button_signupp);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_ = new Intent(signup.this, login_main.class);
                startActivity(back_);
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String str_Email = Email.getText().toString();
                String str_password = passwordUser.getText().toString();

                if (TextUtils.isEmpty(str_Email) || TextUtils.isEmpty(str_password)){
                   Toast.makeText(signup.this, "please fill all blank", Toast.LENGTH_SHORT).show();
                   return;
                }
                else
                {
                    mAuth.createUserWithEmailAndPassword(str_Email, str_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                 Toast.makeText(signup.this, "Registration completed" , Toast.LENGTH_SHORT).show();
                                 Intent goHome = new Intent(signup.this, login_main.class);
                                 startActivity(goHome);

                            }
                            else{
                                 Toast.makeText(signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}