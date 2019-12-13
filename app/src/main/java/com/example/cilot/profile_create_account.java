package com.example.cilot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class profile_create_account extends AppCompatActivity {

    EditText etFN, etLN, etUsername, etEmail, etPassword;
    Button btnBack_create_account, btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_create_account);

        etFN = findViewById(R.id.etFN);
        etLN = findViewById(R.id.etLN);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnBack_create_account = findViewById(R.id.btnBack_create_account);
        btnCreate = findViewById(R.id.btnCreate);

        btnBack_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_create_account.this, com.example.cilot.profile_login.class);
                startActivity(intent);

            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_create_account.this, com.example.cilot.profile_edit_account.class);
                startActivity(intent);
            }
        });
    }

    public void createAccount() {

    }
}
