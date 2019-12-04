package com.example.cilot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class profile_edit_account extends AppCompatActivity {

    EditText etFN, etLN, etEmail, etPassword;
    Button btnBack_edit_account, btnPlus, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_account);

        etFN = findViewById(R.id.etFN);
        etLN = findViewById(R.id.etLN);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnBack_edit_account = findViewById(R.id.btnBack_edit_account);
        btnPlus = findViewById(R.id.btnPlus);
        btnLogout = findViewById(R.id.btnLogout);

        btnBack_edit_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //takes you to the hamburger menu
                Intent intent = new Intent(profile_edit_account.this, com.example.cilot.MainActivity.class);
                startActivity(intent);
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_edit_account.this, com.example.cilot.profile_icons.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_edit_account.this, com.example.cilot.profile_login.class);
                startActivity(intent);
            }
        });
    }


}
