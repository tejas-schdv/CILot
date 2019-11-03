package com.example.cilot;

import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.Dataset;
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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
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
import java.util.Collection;




public class LotReportSheetDialog extends BottomSheetDialogFragment {
    public static int START_TIME = 6;
    public static int END_TIME = 18;
    public static int OPEN = 1;
    public static int MODERATE = 2;
    public static int FULL = 3;

    BarChart barChart;
    TextView tvStatus;
    TextView tvLot;
    DatabaseReference database;
    Button btnSubmitPoll;
    RadioGroup radioGroupPoll;
    RadioButton radioButtonPollOpen;
    RadioButton radioButtonPollModerate;
    RadioButton radioButtonPollFull;

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

        radioGroupPoll = view.findViewById(R.id.poll);
        btnSubmitPoll = view.findViewById(R.id.btnSubmitPoll);
        radioButtonPollOpen = view.findViewById(R.id.open);
        radioButtonPollModerate = view.findViewById(R.id.moderate);
        radioButtonPollFull = view.findViewById(R.id.full);
        btnSubmitPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radioButtonPollOpen.isChecked())
                {
                    Toast.makeText(getContext(), "Selected " + radioButtonPollOpen.getText(), Toast.LENGTH_SHORT).show();
                }
                else if (radioButtonPollModerate.isChecked())
                {
                    Toast.makeText(getContext(), "Selected " + radioButtonPollModerate.getText(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Selected " + radioButtonPollFull.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

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
                BarDataSet barDataSet = new BarDataSet(barEntries, "0");


                BarData data = new BarData(barDataSet);
                barChart.setData(data);

                //highlight current hour
                barDataSet.setHighlightEnabled(true);
                barDataSet.setHighLightColor(Color.BLACK);

                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                //int currentHour = 16;

                if(currentHour > START_TIME-1 && currentHour < END_TIME+1)
                {
                    barChart.highlightValue(currentHour-START_TIME,0,false);
                }

                //disable values
                barDataSet.setDrawValues(false);

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

                //FORMAT x-axis

                final ArrayList<String> xLabel = new ArrayList<>();
                xLabel.add("6AM");
                xLabel.add("7AM");
                xLabel.add("8AM");
                xLabel.add("9AM");
                xLabel.add("10AM");
                xLabel.add("11AM");
                xLabel.add("12PM");
                xLabel.add("1PM");
                xLabel.add("2PM");
                xLabel.add("3PM");
                xLabel.add("4PM");
                xLabel.add("5PM");
                xLabel.add("6PM");

                class MyXAxisFormatter extends ValueFormatter {

                    @Override
                    public String getAxisLabel(float value, AxisBase axis) {
                        return xLabel.get((int)value);
                    }
                }
                xAxis.setValueFormatter(new MyXAxisFormatter());




                //disable legend
                Legend legend = barChart.getLegend();
                legend.setEnabled(false);

                BarData theData = new BarData(barDataSet);
                barChart.setData(theData);
                ////////////////////////////////////////

                float currentStatus = 0;
                int tvColor = Color.GREEN;
                String tvCurrentStatus = "OPEN";

                if(currentHour > START_TIME-1 && currentHour < END_TIME+1)
                    currentStatus = averages[currentHour-START_TIME];

                if(currentStatus <= OPEN) {
                    tvCurrentStatus = "OPEN";
                    tvColor = Color.GREEN;
                }
                else if(currentStatus <= MODERATE && currentStatus > OPEN) {
                    tvCurrentStatus = "MODERATE";
                    tvColor = Color.YELLOW;
                }
                else if(currentStatus <= FULL && currentStatus > MODERATE) {
                    tvCurrentStatus = "FULL";
                    tvColor = Color.RED;
                }


                tvLot.setText(lotName);

                tvStatus.setText(tvCurrentStatus);
                tvStatus.setTextColor(tvColor);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }



}

