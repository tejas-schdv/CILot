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

//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new LotReportSheetDialog()).commit();
                LotReportSheetDialog bottomSheet = new LotReportSheetDialog();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.lot_a2:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //new A2Fragrment()).commit();
                A2Fragrment bottomSheetA2 = new A2Fragrment();
                bottomSheetA2.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.lot_a3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new A3Fragrment()).commit();
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

