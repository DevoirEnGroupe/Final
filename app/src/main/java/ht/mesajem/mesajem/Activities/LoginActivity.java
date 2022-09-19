package ht.mesajem.mesajem.Activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


import ht.mesajem.mesajem.R;

public class LoginActivity extends AppCompatActivity {

    TextView tvnonitiliza;
    TextView tvpaswod;
    EditText etnonitiliza;
    EditText etpaswod;
    Button btKonekte;
    TextView tvenskri;
    SignInButton btSignIn;
    GoogleSignInClient googleSignInClient;
    String TAG = "LoginActivity";
    FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//        if(ParseUser.getCurrentUser()!=null){
//            goMainActivity();
//        }

        // Assign variable
        tvenskri= findViewById(R.id.tvenskri);
        btKonekte = findViewById(R.id.btKonekte);
        etpaswod = findViewById(R.id.etpaswod);
        etnonitiliza= findViewById(R.id.etnonitiliza);
        tvpaswod= findViewById(R.id.tvpaswod);
        tvnonitiliza = findViewById(R.id.tvnonitiliza);
        //btSignIn=findViewById(R.id.bt_sign_in);


        btKonekte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click on Login button");
                String username = etnonitiliza.getText().toString();
                String password = etpaswod.getText().toString();
                loginUser(username,password);
            }

            private void loginUser(String username, String password) {
                Log.i(TAG,"Trying to login user" +username);
                //Navigate to the main activity if the user has signed properly
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(e!=null){
                            Log.e(TAG, "Issue with Login",e);
                            return;
                        }
                        goMainActivity();
                    }
                });
            }
        });

        tvenskri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);

            }
        });

    }

    private void goMainActivity() {
        Intent i = new Intent(LoginActivity.this, ListTaskActivity.class);
        startActivity(i);
        finish();;
    }

}