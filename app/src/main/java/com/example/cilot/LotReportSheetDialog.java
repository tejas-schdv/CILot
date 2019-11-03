package com.example.cilot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

public class LotReportSheetDialog extends BottomSheetDialogFragment {

    BarChart barChart;
    TextView tvStatus;
    TextView tvLot;
    DatabaseReference database;
    Button btnSubmitPoll;
    RadioGroup poll_group;
    RadioButton radioButton;

    Calendar calendar = Calendar.getInstance();
    int currDay;
    String dbDay;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lot_report,container,false);
        barChart = (BarChart) view.findViewById(R.id.barChart);
        tvStatus = view.findViewById(R.id.tvStatus);
        tvLot = view.findViewById(R.id.tvLot);
        btnSubmitPoll = view.findViewById(R.id.btnSubmitPoll);

        currDay = calendar.get(Calendar.DAY_OF_WEEK);
        dbDay = null;

        String lotName = getArguments().getString("params");


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
                dbDay = "monday";
                break;
        }

        database = FirebaseDatabase.getInstance().getReference().child("lots").child(lotName);
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

                //FORMAT x-axis
                // private class MyValueFormatter : ValueFormatter(){

                //}

                //highlight
                //barChart.highlightValue(3,-1,false);
                //dataSet.setHighlightEnabled(true); // allow highlighting for DataSet
                // set this to false to disable the drawing of highlight indicator (lines)
                //dataSet.setDrawHighlightIndicators(true);
                //dataSet.setHighlightColor(Color.BLACK);

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

