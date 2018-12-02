package com.example.julie.rescues;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Test1 extends AppCompatActivity implements LocationListener {
    LocationManager locationManager;
    private static final int INITIAL_REQUEST=1337;
    private static final int CAMERA_REQUEST=INITIAL_REQUEST+1;
    private static final int CONTACTS_REQUEST=INITIAL_REQUEST+2;
    private static final int LOCATION_REQUEST=INITIAL_REQUEST+3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

    }
    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(perm));
    }
    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }
    void getLocation() {
        try {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
         //   locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         //   locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
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
