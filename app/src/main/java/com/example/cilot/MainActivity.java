package com.example.cilot;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    public static int START_TIME = 6;
    public static int END_TIME = 18;
    public static int OPEN = 1;
    public static int MODERATE = 2;
    public static int FULL = 3;
    public static int NUMBER_OF_LOTS = 11;

    private DrawerLayout drawer;

    DatabaseReference database;

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

        Button[] mapButtons = {button_a1, button_a2, button_a3, button_a4, button_a5, button_a6,
                button_a7, button_a8, button_a9, button_a10, button_a11};

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

        //Change button colors
        String[] lotNames = {"A1", "A2", "A3", "A4", "A5", "A6","A7", "A8", "A9", "A10", "A11"};
        //CHANGE 3 to NUMBER_OF_LOTS (WE ONLY HAVE DATA FOR 3 LOTS)
        for(int k = 0; k < 3; k++)
        {
            changeButtonColors(mapButtons[k], lotNames[k]);
            mapButtons[k].setTextColor(Color.WHITE);
        }

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

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

    public void changeButtonColors(final Button button, final String lotNameParam){
        Calendar calendar = Calendar.getInstance();
        int currDay;
        final String dbDay;

        currDay = calendar.get(Calendar.DAY_OF_WEEK);
        final int currentHour = calendar.get(Calendar.HOUR_OF_DAY);



        switch(currDay)
        {
            case Calendar.SUNDAY:
                //CHANGE BACK TO CORRECT DAYS (ALL ARE MONDAY FOR TESTING PURPOSES)
                dbDay = "monday";
                break;
            case Calendar.MONDAY:
                dbDay = "monday";
                break;
            case Calendar.TUESDAY:
                dbDay = "monday";
                break;
            case Calendar.WEDNESDAY:
                dbDay = "monday";
                break;
            case Calendar.THURSDAY:
                dbDay = "monday";
                break;
            case Calendar.FRIDAY:
                dbDay = "monday";
                break;
            case Calendar.SATURDAY:
                dbDay = "saturday";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currDay);
        }


            database = FirebaseDatabase.getInstance().getReference().child("lots").child(lotNameParam);
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String[] times = {"6am", "7am", "8am", "9am", "10am", "11am", "12pm",
                            "1pm", "2pm", "3pm", "4pm", "5pm", "6pm"};
                    String[] polls = new String[times.length];

                    //retrieve data form database
                    String lotName = dataSnapshot.child("lot_name").getValue().toString();
                    for (int i = 0; i < times.length; i++) {
                        polls[i] = dataSnapshot.child("day").child(dbDay).child("hour").child(times[i]).child("polls").getValue().toString();
                    }

                    float[] averages = new float[polls.length];

                    for (int i = 0; i < polls.length; i++) {
                        //turn string polls into an int array
                        String[] stringArray = polls[i].split(",");
                        int[] intArray = new int[stringArray.length];
                        for (int j = 0; j < stringArray.length; j++) {
                            String numberAsString = stringArray[j];
                            intArray[j] = Integer.parseInt(numberAsString);
                        }

                        //calculate average of poll
                        float avg = 0;
                        for (int j = 0; j < intArray.length; j++) {
                            avg += intArray[j];
                        }
                        avg /= intArray.length;
                        averages[i] = avg;
                        //String tvAvg = Float.toString(avg);
                    }

                    float currentStatus = 0;
                    int tvColor = Color.GREEN;

                    if (currentHour > START_TIME - 1 && currentHour < END_TIME + 1)
                        currentStatus = averages[currentHour - START_TIME];

                    if (currentStatus <= OPEN) {
                        tvColor = Color.GREEN;
                        button.setTextColor(Color.BLACK);
                    } else if (currentStatus <= MODERATE && currentStatus > OPEN) {
                        tvColor = Color.YELLOW;
                        button.setTextColor(Color.BLACK);
                    } else if (currentStatus <= FULL && currentStatus > MODERATE) {
                        tvColor = Color.RED;
                    }

                    //set views
                    button.setBackgroundColor(tvColor);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }


