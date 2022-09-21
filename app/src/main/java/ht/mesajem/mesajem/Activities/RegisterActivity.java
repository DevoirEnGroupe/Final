package ht.mesajem.mesajem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import ht.mesajem.mesajem.R;

public class RegisterActivity extends AppCompatActivity {

    TextView tvkodsekre1;
    TextView tvkodsekre2;
    TextView tvimel;
    TextView tvnon;
    EditText etnon;
    EditText etimel;
    EditText etkodsekre1;
    EditText etkodsekre2;
    Button btKonekte1;
    TextView tvLogin;
    String TAG ="RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvkodsekre1 = findViewById(R.id.tvkodsekre1)  ;
        tvkodsekre2 = findViewById(R.id.tvkodsekre2)  ;
        tvimel = findViewById(R.id.tvimel)  ;
        tvnon = findViewById(R.id.tvnon)  ;
        etnon =  findViewById(R.id.etnon)  ;
        etimel = findViewById(R.id.etimel)  ;
        etkodsekre1 =  findViewById(R.id.etkodsekre1) ;
        etkodsekre2 = findViewById(R.id.etkodsekre2)  ;
        btKonekte1 =  findViewById(R.id.btKonekte1)  ;
        tvLogin =  findViewById(R.id.tvlogin)  ;

        btKonekte1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etnon.getText().toString();
                String Email = etimel.getText().toString();
                String password1 = etkodsekre1.getText().toString();
                String password2 = etkodsekre2.getText().toString();
                 //CHECK PASSWORD
                if(password1.equals(password2) ){
                    SignUp(username,Email,password1);

                }
                else{
                    Toast.makeText(RegisterActivity.this,"password error",Toast.LENGTH_LONG).show();

                }


            }

            //METHOD SIGNUP
            private void SignUp(String username, String email, String password1) {
                Log.i(TAG,"Trying to Sign user" +username);
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password1);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {


                        if(e!=null){
                            Log.e(TAG, "Issue with Login",e);
                            return;
                        }

                            goMainActivity();
                        Toast.makeText(RegisterActivity.this,"password ok",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });


        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });

    }

    private void goMainActivity() {
        Intent i = new Intent(RegisterActivity.this, ListTaskActivity.class);
        startActivity(i);
        finish();
    }
}