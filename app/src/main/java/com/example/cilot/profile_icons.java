package com.example.cilot;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.HashMap;

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
//        View view = inflater.inflate(R.layout.lot_report,container,false);
        setContentView(R.layout.activity_profile_icons);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnBack_icons = findViewById(R.id.btnBack_icons);

        btnBack_icons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(profile_icons.this, com.example.cilot.profile_edit_account.class);
                startActivity(intent);*/
                switch (v.getId()) {
                    // ...
                    case R.id.btnBack_icons:
                        signOut();
                        break;
                }
            }
        });


        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_icons.this, com.example.cilot.MainActivity.class);
                startActivity(intent);
            }
        });

        lock1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(profile_icons.this, com.example.cilot.MainActivity.class)
                //if (playerLevel > 0)
                {
                  //  final View image = new View()
                }

                //ImageView image = findViewById(R.id.nav_icon);
                //image.setImageResource(R.drawable.ricardo);
                //Intent intent = new Intent(profile_icons.this, com.example.cilot.MainActivity.class);
                //startActivity(intent);
            }
        });

        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        final String uid = account.getId();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(uid)) {
                    //do nothing
                }
                else
                {
                    DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
                    HashMap<String, Object> user_data = new HashMap<>();
                    user_data.put("email", account.getEmail());
                    user_data.put("points", 0);
                    user_data.put("name", account.getDisplayName());
                    user_data.put("level", 0);

                    usersRef.child(uid).setValue(user_data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(profile_icons.this, "Signed Out Successfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

}
