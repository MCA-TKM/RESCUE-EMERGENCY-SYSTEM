package com.example.julie.rescueemergency;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;


import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


import android.location.LocationListener;

public class test extends AppCompatActivity implements  LocationListener{
public static  test Me;
TextView tv;
    public static  String place;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Me=this;
        tv=(TextView)findViewById(R.id.textView5) ;
     Button bu=(Button)findViewById(R.id.button3);
     bu.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

         }
     });
        getLocation();
        }
        public void NextForm()
        {
            Intent in = new Intent(Me.getApplicationContext(), StartForm.class);
            Me.startActivity(in);
        }

    public void onLocationChanged(Location location) {
        place=("Current Location: " + location.getLatitude() + ", " + location.getLongitude());
        tv.setText(place);
    }


    public void onProviderDisabled(String provider) {
        Toast.makeText(test.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }
    public void onProviderEnabled(String provider) {
        Toast.makeText(test.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    void getLocation() {
        try {
    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }
    }
