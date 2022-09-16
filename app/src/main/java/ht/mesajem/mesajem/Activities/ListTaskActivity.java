package ht.mesajem.mesajem.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import ht.mesajem.mesajem.Fragments.ReceivedFragment;
import ht.mesajem.mesajem.Fragments.SendFragment;
import ht.mesajem.mesajem.Fragments.TrackFragment;
import ht.mesajem.mesajem.R;

public class ListTaskActivity extends AppCompatActivity {

    GoogleSignInClient googleSignInClient;
    RelativeLayout trackNavigation;
    RelativeLayout  sendNavigation;
    RelativeLayout receivedNavigation;
    FragmentManager fragmentManager = getSupportFragmentManager();
    String TAG = "ListTaskActivity";
    int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task);

        // Assign variable
        trackNavigation = findViewById(R.id.FirstRel);
        sendNavigation = findViewById(R.id.SecondRel);
        receivedNavigation = findViewById(R.id.ThirdRel);

        sendNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackNavigation.setVisibility(View.GONE);
                receivedNavigation.setVisibility(View.GONE);
                sendNavigation.setVisibility(View.GONE);
                Fragment fragment= new SendFragment();
                fragmentManager.beginTransaction().replace(R.id.relativ,fragment).commit();


            }
        });

        receivedNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackNavigation.setVisibility(View.GONE);
                receivedNavigation.setVisibility(View.GONE);
                sendNavigation.setVisibility(View.GONE);
                Fragment fragment= new ReceivedFragment();
                fragmentManager.beginTransaction().replace(R.id.relativ,fragment).commit();


            }
        });

        trackNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackNavigation.setVisibility(View.GONE);
                receivedNavigation.setVisibility(View.GONE);
                sendNavigation.setVisibility(View.GONE);
                Fragment fragment= new TrackFragment();
                fragmentManager.beginTransaction().replace(R.id.relativ,fragment).commit();


            }
        });

    }
}