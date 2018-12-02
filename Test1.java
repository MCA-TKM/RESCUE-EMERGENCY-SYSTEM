package com.example.julie.rescueemergency;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Test1 extends AppCompatActivity implements LocationListener {
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

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
    public void onLocationChanged(Location location) {
    String     place=("Current Location: " + location.getLatitude() + ", " + location.getLongitude());
        Toast.makeText(Test1.this, place, Toast.LENGTH_SHORT).show();
    }


    public void onProviderDisabled(String provider) {
        Toast.makeText(Test1.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }
    public void onProviderEnabled(String provider) {
        Toast.makeText(Test1.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}
