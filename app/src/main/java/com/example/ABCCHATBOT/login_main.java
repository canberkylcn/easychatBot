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

public class login_main extends AppCompatActivity {

    public Button button_sign;
    public Button button_login;
    FirebaseAuth mAuth;
    EditText email, password;
    ProgressBar progressBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        progressBar = findViewById(R.id.progressBar);
        email = findViewById(R.id.user_Email);
        password = findViewById(R.id.user_password);
        mAuth = FirebaseAuth.getInstance();
        button_login = findViewById(R.id.buttonLogin);
        button_sign = findViewById(R.id.button_signup);
        button_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign_up = new Intent(login_main.this,signup.class);
                startActivity(sign_up);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String str_Email = email.getText().toString();
                String str_password = password.getText().toString();

                if (TextUtils.isEmpty(str_Email) || TextUtils.isEmpty(str_password)){
                    Toast.makeText(login_main.this, "please fill all blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    mAuth.signInWithEmailAndPassword(str_Email, str_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(login_main.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(login_main.this, "Incorrect password or email", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

    }
}