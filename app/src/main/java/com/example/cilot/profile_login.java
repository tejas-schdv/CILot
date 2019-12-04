package com.example.cilot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;


public class profile_login extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnBack, btnLogin, btnCreateAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnBack = findViewById(R.id.btnBack_login);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //takes you to hamburger menu
                Intent intent = new Intent(profile_login.this, com.example.cilot.MainActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_login.this, com.example.cilot.profile_edit_account.class);
                startActivity(intent);
            }
        });

//        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(profile_login.this, com.example.cilot.profile_create_account.class);
//                startActivity(intent);
//            }
//        });


    }
}
