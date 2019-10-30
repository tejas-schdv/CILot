package com.example.cilot;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout drawer;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_a1 = findViewById(R.id.button_a1);
        Button button_a2 = findViewById(R.id.button_a2);
        Button button_a3 = findViewById(R.id.button_a3);
        Button button_a4 = findViewById(R.id.button_a4);
        Button button_a5 = findViewById(R.id.button_a5);
        Button button_a6 = findViewById(R.id.button_a6);
        Button button_a7 = findViewById(R.id.button_a7);
        Button button_a8 = findViewById(R.id.button_a8);
        Button button_a9 = findViewById(R.id.button_a9);
        Button button_a10 = findViewById(R.id.button_a10);
        Button button_a11 = findViewById(R.id.button_a11);

        button_a1.setOnClickListener(this);
        button_a2.setOnClickListener(this);
        button_a3.setOnClickListener(this);
        button_a4.setOnClickListener(this);
        button_a5.setOnClickListener(this);
        button_a6.setOnClickListener(this);
        button_a7.setOnClickListener(this);
        button_a8.setOnClickListener(this);
        button_a9.setOnClickListener(this);
        button_a10.setOnClickListener(this);
        button_a11.setOnClickListener(this);

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
            case R.id.lot_a7:
                A7Fragment bottomSheetA7 = new A7Fragment();
                bottomSheetA7.show(getSupportFragmentManager(),"exampleBottomSheet");
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.button_a1:
                Bundle bundleA1 = new Bundle();
                bundleA1.putString("params","A1");
                LotReportSheetDialog bottomSheetA1 = new LotReportSheetDialog();
                bottomSheetA1.setArguments(bundleA1);
                bottomSheetA1.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.button_a2:
                Bundle bundleA2 = new Bundle();
                bundleA2.putString("params","A2");
                LotReportSheetDialog bottomSheetA2 = new LotReportSheetDialog();
                bottomSheetA2.setArguments(bundleA2);
                bottomSheetA2.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.button_a3:
                Bundle bundleA3 = new Bundle();
                bundleA3.putString("params","A3");
                LotReportSheetDialog bottomSheetA3 = new LotReportSheetDialog();
                bottomSheetA3.setArguments(bundleA3);
                bottomSheetA3.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.button_a4:
                Bundle bundleA4 = new Bundle();
                bundleA4.putString("params","A4");
                LotReportSheetDialog bottomSheetA4 = new LotReportSheetDialog();
                bottomSheetA4.setArguments(bundleA4);
                bottomSheetA4.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.button_a5:
                Bundle bundleA5 = new Bundle();
                bundleA5.putString("params","A5");
                LotReportSheetDialog bottomSheetA5 = new LotReportSheetDialog();
                bottomSheetA5.setArguments(bundleA5);
                bottomSheetA5.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.button_a6:
                Bundle bundleA6 = new Bundle();
                bundleA6.putString("params","A6");
                LotReportSheetDialog bottomSheetA6 = new LotReportSheetDialog();
                bottomSheetA6.setArguments(bundleA6);
                bottomSheetA6.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.button_a7:
                Bundle bundleA7 = new Bundle();
                bundleA7.putString("params","A7");
                LotReportSheetDialog bottomSheetA7 = new LotReportSheetDialog();
                bottomSheetA7.setArguments(bundleA7);
                bottomSheetA7.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.button_a8:
                Bundle bundleA8 = new Bundle();
                bundleA8.putString("params","A8");
                LotReportSheetDialog bottomSheetA8 = new LotReportSheetDialog();
                bottomSheetA8.setArguments(bundleA8);
                bottomSheetA8.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.button_a9:
                Bundle bundleA9 = new Bundle();
                bundleA9.putString("params","A9");
                LotReportSheetDialog bottomSheetA9 = new LotReportSheetDialog();
                bottomSheetA9.setArguments(bundleA9);
                bottomSheetA9.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.button_a10:
                Bundle bundleA10 = new Bundle();
                bundleA10.putString("params","A10");
                LotReportSheetDialog bottomSheetA10 = new LotReportSheetDialog();
                bottomSheetA10.setArguments(bundleA10);
                bottomSheetA10.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.button_a11:
                Bundle bundleA11 = new Bundle();
                bundleA11.putString("params","A11");
                LotReportSheetDialog bottomSheetA11 = new LotReportSheetDialog();
                bottomSheetA11.setArguments(bundleA11);
                bottomSheetA11.show(getSupportFragmentManager(), "exampleBottomSheet");
                break;
        }


    }


    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
