package ht.mesajem.mesajem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;


import ht.mesajem.mesajem.R;

public class ListTaskActivity extends AppCompatActivity {

    GoogleSignInClient googleSignInClient;
    RelativeLayout trackNavigation;
    RelativeLayout  sendNavigation;
    RelativeLayout receivedNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task);

        // Assign variable
        trackNavigation = findViewById(R.id.ThirdRel);
        sendNavigation = findViewById(R.id.FirstRel);
        receivedNavigation = findViewById(R.id.SecondRel);

        sendNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTaskActivity.this, SendActivity.class);
                startActivity(intent);


            }
        });

        receivedNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTaskActivity.this, ReceivedActivity.class);
                startActivity(intent);
            }
        });

        trackNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ListTaskActivity.this, TrackActivity.class);
                startActivity(intent);

            }
        });

    }
}