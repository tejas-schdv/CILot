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
    DatabaseReference currentStatusDatabase;
    DatabaseReference pollDatabase;
    DatabaseReference respondantsDatabase;
    DatabaseReference currentStatusTimeDatabase;

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

        currentStatusDatabase = FirebaseDatabase.getInstance().getReference().child("lots").child(lotName).child("current_status").child("polls");
        pollDatabase = FirebaseDatabase.getInstance().getReference().child("lots").child(lotName).child("current_status").child("polls");
        respondantsDatabase = FirebaseDatabase.getInstance().getReference().child("lots").child(lotName).child("current_status").child("respondants");
        currentStatusTimeDatabase = FirebaseDatabase.getInstance().getReference().child("lots").child(lotName).child("current_status").child("time");




        btnSubmitPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radioButtonPollOpen.isChecked())
                {
                    Toast.makeText(getContext(), "Submitted " + radioButtonPollOpen.getText(), Toast.LENGTH_SHORT).show();
                    pollDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            long openCounter;
                            if (dataSnapshot.exists())
                            {
                                openCounter =(dataSnapshot.getValue(Long.class));
                                pollDatabase.setValue(openCounter+1);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    respondantsDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            long resCounter;

                            if (dataSnapshot.exists())
                            {
                                resCounter = (dataSnapshot.getValue(Long.class));
                                respondantsDatabase.setValue(resCounter+1);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (radioButtonPollModerate.isChecked())
                {
                    Toast.makeText(getContext(), "Submitted " + radioButtonPollOpen.getText(), Toast.LENGTH_SHORT).show();
                    pollDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            long openCounter;
                            if (dataSnapshot.exists())
                            {
                                openCounter =(dataSnapshot.getValue(Long.class));
                                pollDatabase.setValue(openCounter+2);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    respondantsDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            long resCounter;

                            if (dataSnapshot.exists())
                            {
                                resCounter = (dataSnapshot.getValue(Long.class));
                                respondantsDatabase.setValue(resCounter+1);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(getContext(), "Submitted " + radioButtonPollOpen.getText(), Toast.LENGTH_SHORT).show();
                    pollDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            long openCounter;
                            if (dataSnapshot.exists())
                            {
                                openCounter =(dataSnapshot.getValue(Long.class));
                                pollDatabase.setValue(openCounter+3);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    respondantsDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            long resCounter;

                            if (dataSnapshot.exists())
                            {
                                resCounter = (dataSnapshot.getValue(Long.class));
                                respondantsDatabase.setValue(resCounter+1);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        database = FirebaseDatabase.getInstance().getReference().child("lots").child(lotName);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String[] times = {"6am", "7am", "8am", "9am", "10am", "11am", "12pm",
                        "1pm", "2pm", "3pm", "4pm", "5pm", "6pm"};
                String[] baseDataString = new String[times.length];
                Float[] baseDataFloat = new Float[times.length];

                //retrieve data from database to put into graphs
                String lotName = dataSnapshot.child("lot_name").getValue().toString();
                for(int i = 0; i < times.length; i++)
                {
                    baseDataString[i] = dataSnapshot.child(dbDay).child(times[i]).getValue().toString();
                    baseDataFloat[i] = Float.parseFloat(baseDataString[i]);
                }


                /*float[] averages = new float[polls.length];

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
                }*/

                ////////////////////////////////////////
                //place data into graph
                ArrayList<BarEntry> barEntries = new ArrayList<>();

                for(int i = 0; i < baseDataFloat.length; i++)
                {
                    barEntries.add(new BarEntry(i, baseDataFloat[i]));
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

                //set the data
                BarData theData = new BarData(barDataSet);
                barChart.setData(theData);


                ////////////////////////////////////////
                //cuurent status


                float polls = Float.parseFloat(dataSnapshot.child("current_status").child("polls").getValue().toString());
                int respondants = Integer.parseInt((dataSnapshot.child("current_status").child("respondants").getValue().toString()));

                float currentStatus = polls/respondants;

                int tvColor = Color.GREEN;
                String tvCurrentStatus = "OPEN";

                /*if(currentHour > START_TIME-1 && currentHour < END_TIME+1)
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
                }*/


               // int timeIndex = time - START_TIME;

                /*char[] temp = currentStatusTime.toCharArray();

                //0 determines int time, 1 and 2 determine am or pm
                int time = Integer.parseInt(Character.toString(temp[0]));
                int timeIndex;

                //if time is am
                if(temp[1] == 'a')
                    timeIndex = time - START_TIME;
                else //if time is pm
                    timeIndex = time + START_TIME;*/

                //Grab most recent data from base data
                String currentStatusTime = dataSnapshot.child("current_status").child("time").getValue().toString();
                int time = Integer.parseInt(currentStatusTime);

                float basePoll;
                //check if currentHour is in between start time and end time, if not set default to open
                if(currentHour < START_TIME || currentHour > END_TIME)
                    basePoll = OPEN;
                else
                    basePoll = Float.parseFloat(dataSnapshot.child(dbDay).child(times[currentHour-START_TIME]).getValue().toString());
                if(currentHour != time)
                {
                    currentStatusTimeDatabase.setValue(currentHour);
                    pollDatabase.setValue(basePoll);
                    respondantsDatabase.setValue(1);
                }

                //Set curretn status textviews
                if(currentStatus >= 1 && currentStatus <= 1.4) {
                    tvCurrentStatus = "OPEN";
                    tvColor = Color.GREEN;
                }
                else if(currentStatus > 1.4 && currentStatus < 2.4) {
                    tvCurrentStatus = "MODERATE";
                    tvColor = Color.YELLOW;
                }
                else if(currentStatus >= 2.4 && currentStatus <= 3) {
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

    private void updatePoll(DatabaseReference lot)
    {

    }



}



