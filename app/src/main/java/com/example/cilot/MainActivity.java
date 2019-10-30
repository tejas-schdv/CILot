package com.example.cilot;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        radioGroup = findViewById(R.id.poll);
//        textView = findViewById(R.id.status);
//
//        Button submit = findViewById(R.id.submit);
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int radioId = radioGroup.getCheckedRadioButtonId();
//
//                radioButton = findViewById(radioId);
//
//                textView.setText("Your choice: " + radioButton.getText());
//            }
//        });

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




    }
//    public void checkButton(View v) {
//        int radioId = radioGroup.getCheckedRadioButtonId();
//
//        radioButton = findViewById(radioId);
//
//        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
//                Toast.LENGTH_SHORT).show();
//    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.lot_a1:
                Bundle bundleA1 = new Bundle();
                bundleA1.putString("params", "A1");

                LotReportSheetDialog bottomSheetA1 = new LotReportSheetDialog();
                bottomSheetA1.setArguments(bundleA1);
                bottomSheetA1.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.lot_a2:
                Bundle bundleA2 = new Bundle();
                bundleA2.putString("params", "A2");

                LotReportSheetDialog bottomSheetA2 = new LotReportSheetDialog();
                bottomSheetA2.setArguments(bundleA2);
                bottomSheetA2.show(getSupportFragmentManager(), "exampleBottomSheet");

                break;
            case R.id.lot_a3:
                Bundle bundleA3 = new Bundle();
                bundleA3.putString("params", "A3");

                LotReportSheetDialog bottomSheetA3 = new LotReportSheetDialog();
                bottomSheetA3.setArguments(bundleA3);
                bottomSheetA3.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.profile:
                Intent intent = new Intent(MainActivity.this, com.example.cilot.profile_login.class);
                startActivity(intent);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

