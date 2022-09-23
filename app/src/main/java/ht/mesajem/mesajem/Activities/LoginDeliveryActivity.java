package ht.mesajem.mesajem.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import ht.mesajem.mesajem.Models.Delivery;
import ht.mesajem.mesajem.Models.Post;
import ht.mesajem.mesajem.R;

public class LoginDeliveryActivity extends AppCompatActivity {

    TextView tvnonitiliza;
    TextView tvpaswod;
    EditText etnonitiliza;
    EditText etpaswod;
    Button btKonekte;
    TextView tvenskri;
    String TAG = "LoginDeliveryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_login);


        // Assign variable

        btKonekte = findViewById(R.id.btKonekte);
        etpaswod = findViewById(R.id.etpaswod);
        etnonitiliza= findViewById(R.id.etnonitiliza);
        tvpaswod= findViewById(R.id.tvpaswod);
        tvnonitiliza = findViewById(R.id.tvnonitiliza);



        btKonekte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click on Login button");
                String username = etnonitiliza.getText().toString();
                String password = etpaswod.getText().toString();
                loginUser(username,password);
            }

            private void loginUser(String username, String password) {

                ParseQuery<Delivery> query = ParseQuery.getQuery(Delivery.class);
                query.include(Delivery.KEY_POST_ACC);
                
                query.getInBackground("<PARSE_OBJECT_ID>", (object, e) -> {
                    if (e == null) {

                        ParseUser.logInInBackground(username, password, new LogInCallback() {

                            @Override
                            public void done(ParseUser user, ParseException e) {
//                        progressDialog.dismiss();
                                goMainActivity();
                                showAlert("Login Successful", "Welcome, " + username + "!", false);
                            }
                        });
                    } else {

                        showAlert("Login Fail", e.getMessage() + " Please try again", true);
                        Log.e(TAG, "Issue with Login",e);

                    }
                });

            }
        });
    }



    private void goMainActivity() {
        Intent i = new Intent(LoginDeliveryActivity.this, MainDeliveyActivity.class);
        startActivity(i);
        finish();
    }


    private void showAlert(String title, String message, boolean error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginDeliveryActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.cancel();
                    // don't forget to change the line below with the names of your Activities
                    if (!error) {

                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}