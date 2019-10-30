package com.example.cilot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class A2Fragrment extends BottomSheetDialogFragment {

    BarChart barChart;
    TextView tvStatus;
    TextView tvLot;
    DatabaseReference database;
    Button btnSubmitPoll;

    Calendar calendar = Calendar.getInstance();
    int currDay;
    String dbDay;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lot_a2,container,false);
        barChart = (BarChart) view.findViewById(R.id.barChart);
        tvStatus = view.findViewById(R.id.tvStatus);
        tvLot = view.findViewById(R.id.tvLot);
        btnSubmitPoll = view.findViewById(R.id.btnSubmitPoll);

        currDay = calendar.get(Calendar.DAY_OF_WEEK);
        dbDay = null;

        switch(currDay)
        {
            case Calendar.SUNDAY:
                //CHANGE BACK TO SUNDAY (TESTING PURPOSES)
                dbDay = "monday";
                break;
            case Calendar.MONDAY:
                dbDay = "monday";
                break;
            case Calendar.TUESDAY:
                dbDay = "tuesday";
                break;
            case Calendar.WEDNESDAY:
                dbDay = "wednesday";
                break;
            case Calendar.THURSDAY:
                dbDay = "thursday";
                break;
            case Calendar.FRIDAY:
                dbDay = "friday";
                break;
            case Calendar.SATURDAY:
                dbDay = "saturday";
                break;
        }

        database = FirebaseDatabase.getInstance().getReference().child("lots").child("A1");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String[] times = {"6am", "7am", "8am", "9am", "10am", "11am", "12pm",
                                  "1pm", "2pm", "3pm", "4pm", "5pm", "6pm"};
                String[] polls = new String[times.length];

                //retrieve data form database
                String lotName = dataSnapshot.child("lot_name").getValue().toString();
                for(int i = 0; i < times.length; i++)
                {
                    polls[i] = dataSnapshot.child("day").child(dbDay).child("hour").child(times[i]).child("polls").getValue().toString();
                }


                float[] averages = new float[polls.length];

                for(int i = 0; i < polls.length; i++)
                {
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

                ////////////////////////////////////////
                //place data into graph
                ArrayList<BarEntry> barEntries = new ArrayList<>();

                for(int i = 0; i < averages.length; i++)
                {
                    barEntries.add(new BarEntry(i, averages[i]));
                }
                BarDataSet barDataSet = new BarDataSet(barEntries, "Capacity");

                //FORMAT GRAPH
               // private class MyValueFormatter : ValueFormatter(){

                //}

                //disable description
                Description description = barChart.getDescription();
                description.setEnabled(false);

                barChart.setFitBars(true);

                YAxis left = barChart.getAxisLeft();
                left.setDrawLabels(false); // no axis labels
                left.setDrawAxisLine(false); // no axis line
                left.setDrawGridLines(false); // no grid lines
                left.setDrawZeroLine(true); // draw a zero line
                barChart.getAxisRight().setEnabled(false);

                XAxis xAxis = barChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawAxisLine(true);
                xAxis.setDrawGridLines(false);

                //disable legend
                Legend legend = barChart.getLegend();
                legend.setEnabled(false);

                BarData theData = new BarData(barDataSet);
                barChart.setData(theData);
                ////////////////////////////////////////

                //set views
                tvLot.setText(lotName);
                //tvStatus.setText(tvAvg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}