package com.example.cilot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class profile_icons extends AppCompatActivity {

    Button btnBack_icons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_icons);

        btnBack_icons = findViewById(R.id.btnBack_icons);

        btnBack_icons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_icons.this, com.example.cilot.profile_edit_account.class);
                startActivity(intent);
            }
        });
    }
}
