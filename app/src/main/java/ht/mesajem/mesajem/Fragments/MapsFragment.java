package ht.mesajem.mesajem.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

import ht.mesajem.mesajem.Activities.LoginActivity;
import ht.mesajem.mesajem.Models.InfoPost;
import ht.mesajem.mesajem.Models.Post;
import ht.mesajem.mesajem.R;

public class MapsFragment extends Fragment {

    TextView tvadress;
    TextView tvsiyati;
    TextView tvimel;
    TextView tvnon;
    EditText etnon;
    EditText etimel;
    EditText etadress;
    EditText etsiyati;

    private static final int REQUEST_LOCATION = 1;
    public static final String TAG ="MapsFragment";

    LocationManager locationManager;
    Button btShare;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng haiti = new LatLng(19.054426, -73.04597100000001);
            googleMap.addMarker(new MarkerOptions().position(haiti).title("Marker in Haiti"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(haiti));
            showCurrentUserInMap(googleMap);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);


        btShare = view.findViewById(R.id.btShare);

        tvadress = view.findViewById(R.id.tvaddresse);
        tvsiyati = view.findViewById(R.id.tvsiyati);
        tvimel  = view.findViewById(R.id.tvimel);
        tvnon = view.findViewById(R.id.tvnon);
        etnon = view.findViewById(R.id.etnon);
        etimel = view.findViewById(R.id.etimel);
        etadress =view.findViewById(R.id.etadress);
        etsiyati = view.findViewById(R.id.etsiyati);

        btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "FELISITASYON", Toast.LENGTH_SHORT).show();
                String addresse = etadress.getText().toString();
                String lastname = etsiyati.getText().toString();
                String firstname = etnon.getText().toString();
                String email = etimel.getText().toString();

                ParseUser currentUser = ParseUser.getCurrentUser();
                savePost(currentUser,lastname,firstname,email,addresse);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.relativ, new PayementFragment())
                        .commit();

            }
        });

    }



    private void savePost(ParseUser currentUser, String lastname, String firstname, String email,String addresse) {
        Post post = new Post();
        post.setNom(lastname);
        post.setPrenom(firstname);
        post.setEmail(email);
        post.setAddresse(addresse);
        post.setUser(currentUser);
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!= null){
                    Log.e(TAG, "error while save", e);
                    //Toast.makeText(getContext(),"error save", Toast.LENGTH_LONG).show();
                }
                Log.i(TAG,"Save post");
                etimel.setText("");
                etadress.setText("");
                etsiyati.setText("");
                etnon.setText("");

            }
        });
    }


    private void saveCurrentUserLocation() {

        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if(location != null){
                ParseGeoPoint currentUserLocation = new ParseGeoPoint(location.getLatitude(), location.getLongitude());

                ParseUser currentUser = ParseUser.getCurrentUser();
                if (currentUser != null) {
                    currentUser.put("Location", currentUserLocation);
                    currentUser.saveInBackground();
                } else {
                    alertDisplayer("Well... you're not logged in...","Login first!");
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
            else {
                Log.d("userLocation", "Unable to find current user location.");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_LOCATION:
                saveCurrentUserLocation();
                break;
        }
    }

    private ParseGeoPoint getCurrentUserLocation(){
        // saving the currentUserLocation to allow it's return
        saveCurrentUserLocation();

        // finding currentUser
        ParseUser currentUser = ParseUser.getCurrentUser();

        return currentUser.getParseGeoPoint("Location");


    }

    private void showCurrentUserInMap(final GoogleMap googleMap){

        ParseGeoPoint currentUserLocation = getCurrentUserLocation();

        // creating a marker in the map showing the current user location
        LatLng currentUser = new LatLng(currentUserLocation.getLatitude(), currentUserLocation.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(currentUser).title(ParseUser.getCurrentUser().getUsername()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        // zoom the map to the currentUserLocation
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentUser, 5));
    }

    private void alertDisplayer(String title,String message){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        android.app.AlertDialog ok = builder.create();
        ok.show();
    }
}