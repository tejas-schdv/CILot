package com.example.cilot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class profile_icons extends AppCompatActivity {

    Button btnBack_icons;
    
    ProgressBar playerLevelXPProgressBar;
    DatabaseReference user_points,user_level;
    TextView level, pointsAdded;
    Button Home;
    int playerLevel = 1;
    GoogleSignInClient mGoogleSignInClient;
    Button lock1_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_icons);

        btnBack_icons = findViewById(R.id.btnBack_icons);
        Home = findViewById(R.id.home_button);
        playerLevelXPProgressBar = findViewById(R.id.playerLevelBar);
        level = findViewById(R.id.playerLevelText);
        pointsAdded = findViewById(R.id.playerPointsText);




        user_points = FirebaseDatabase.getInstance().getReference().child("users").child("107703088750367185275").child("points");
        user_level = FirebaseDatabase.getInstance().getReference().child("users").child("107703088750367185275").child("level");
        level.setText(String.valueOf(playerLevel));
        lock1_button = findViewById(R.id.level1_button);


        user_points.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pointsAdded.setText(String.valueOf(Integer.parseInt(dataSnapshot.getValue().toString())));
                playerLevelXPProgressBar.setProgress(Integer.parseInt(dataSnapshot.getValue().toString()));
                if (playerLevelXPProgressBar.getProgress() == 100) {
                    playerLevelXPProgressBar.setProgress(0);
                }

                if (Integer.parseInt(dataSnapshot.getValue().toString()) < 100) {
                    playerLevel = 1;
                    level.setText(String.valueOf(1));
                    ImageView image1 = (ImageView) findViewById(R.id.lock_lvl1);
                    image1.setImageResource(R.drawable.echo);
                } else if (Integer.parseInt(dataSnapshot.getValue().toString()) > 99 && Integer.parseInt(dataSnapshot.getValue().toString()) < 200) {
                    playerLevel = 2;
                    level.setText(String.valueOf(2));
                    ImageView image1 = (ImageView) findViewById(R.id.lock_lvl1);
                    image1.setImageResource(R.drawable.echo);
                    ImageView image2 = (ImageView) findViewById(R.id.lock_lvl2);
                    image2.setImageResource(R.drawable.buffy);

                } else {
                    playerLevel = 3;
                    level.setText(String.valueOf(3));
                    ImageView image1 = (ImageView) findViewById(R.id.lock_lvl1);
                    image1.setImageResource(R.drawable.echo);
                    ImageView image2 = (ImageView) findViewById(R.id.lock_lvl2);
                    image2.setImageResource(R.drawable.buffy);
                    ImageView image3 = (ImageView) findViewById(R.id.lock_lvl3);
                    image3.setImageResource(R.drawable.ricardo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        user_level.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user_level.setValue(playerLevel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnBack_icons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_icons.this, com.example.cilot.profile_edit_account.class);
                startActivity(intent);
            }
        });
    }
}
