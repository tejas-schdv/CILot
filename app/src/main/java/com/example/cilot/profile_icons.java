package com.example.cilot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class profile_icons extends AppCompatActivity {

    Button btnBack_icons;
    ProgressBar playerLevelXPProgressBar;
    DatabaseReference user_points,user_level;
    TextView level;
    Button Home;
    int playerLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View view = inflater.inflate(R.layout.lot_report,container,false);
        setContentView(R.layout.activity_profile_icons);

        btnBack_icons = findViewById(R.id.btnBack_icons);
        Home = findViewById(R.id.home_button);
        playerLevelXPProgressBar = findViewById(R.id.playerLevelBar);
        level = findViewById(R.id.playerLevelText);
        user_points = FirebaseDatabase.getInstance().getReference().child("users").child("107703088750367185275").child("points");
        user_level = FirebaseDatabase.getInstance().getReference().child("users").child("107703088750367185275").child("level");
        level.setText(String.valueOf(playerLevel));

        user_points.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                playerLevelXPProgressBar.setProgress(Integer.parseInt(dataSnapshot.getValue().toString()));
                if (playerLevelXPProgressBar.getProgress() == 100) {
                    playerLevelXPProgressBar.setProgress(0);
                }

                if (Integer.parseInt(dataSnapshot.getValue().toString()) < 100) {
                    level.setText(String.valueOf(1));
                } else if (Integer.parseInt(dataSnapshot.getValue().toString()) > 99 && Integer.parseInt(dataSnapshot.getValue().toString()) < 200) {
                    playerLevel =2;
                    level.setText(String.valueOf(2));
                } else {
                    playerLevel =3;
                    level.setText(String.valueOf(3));
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

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_icons.this, com.example.cilot.MainActivity.class);
                startActivity(intent);
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
        }
    }

}
