package com.example.julie.rescues;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.SensorListener;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class userHomepage extends AppCompatActivity implements SensorListener ,LocationListener {
    SensorManager sensorMgr;
    public static  userHomepage Me;
    public static long Delay=20;
    private static final int SHAKE_THRESHOLD = 800;
    public static  String place;
    public static  String Loca;
    LocationManager locationManager;
    Button bt2;
    public EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);
        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        Me = this;
        getLocation();
        sensorMgr.registerListener(this,
                SensorManager.SENSOR_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_GAME);
        edit=(EditText)findViewById(R.id.editText);

        Button bt1 = (Button) findViewById(R.id.btnhospital);

       bt2 = (Button) findViewById(R.id.btnworkshop);

       Button b5=(Button)findViewById(R.id.button5) ;
       b5.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view) {

              getLocation();
           }
       });
        Button bt3 = (Button) findViewById(R.id.btnpolice);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NetworkTask.SendData("SHOS"+edit.getText().toString());
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           NetworkTask.SendData("WORK"+edit.getText().toString());
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkTask.SendData("POLI"+edit.getText().toString());
            }
        });
    }

    long lastUpdate;
    float x,y,z;
    float last_x,last_y,last_z;
    public void onSensorChanged(int sensor, float[] values) {
        if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                x = values[SensorManager.DATA_X];
                y = values[SensorManager.DATA_Y];
                z = values[SensorManager.DATA_Z];

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    ShowAlert();
                    //  Log.d("sensor", "shake detected w/ speed: " + speed);
                    //  Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }
    public  AlertDialog.Builder dialogBuilder;
    public  AlertDialog alertDialog;
    public void ShowAlert()
    {

        Delay=20;
        dialogBuilder   = new AlertDialog.Builder(Me);
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = Me.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogs, null);
        dialogBuilder.setView(dialogView);

        TextView editText = (TextView) dialogView.findViewById(R.id.textView6);
        editText.setText("Remaining "+Delay);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
        new Timers(editText,new Date().getTime(),Delay);
        Button bu1=(Button)  dialogView.findViewById(R.id.button2);
        bu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendRequest();
            }
        });
        bu1=(Button)  dialogView.findViewById(R.id.button4);
        bu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Timers.IsEnable=false;
                CloseDialog();

            }
        });
    }
    public String tos,Sub;
    public void SendSMS(String to,String msg)
    {
        tos=to;
        Sub=msg;
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},2);
    }
    public void ShowAlert1()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (!isFinishing()){
                    new AlertDialog.Builder(userHomepage.this)
                            .setTitle("Your Alert!!")
                            .setMessage("Abort Request..")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                   Intent int4=new Intent(userHomepage.this,userHomepage.class);
                                   startActivity(int4);
                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...
                                }
                            }).show();
                }
            }
        });

    }
    public void onAccuracyChanged(int sensor, int accuracy) { }

public void SendRequest()
{
   // alertDialog.dismiss();
    Timers.IsEnable=false;

    Intent int2=new Intent(userHomepage.this,userHomepage.class);
    startActivity(int2);
    NetworkTask.SendData("ACC"+edit.getText().toString());
    Toast.makeText(Me.getApplicationContext(), "Sending Request", Toast.LENGTH_LONG).show();
}
    public void CloseDialog()
    {
        Timers.IsEnable=false;
        Intent int2=new Intent(userHomepage.this,userHomepage.class);
        startActivity(int2);

    }

    public void onLocationChanged(Location location) {
        place= location.getLatitude() + ", " + location.getLongitude();
     edit.setText(place);
     //   Toast.makeText(userHomepage.this, place, Toast.LENGTH_SHORT).show();
    }


    public void onProviderDisabled(String provider) {
        Toast.makeText(userHomepage.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }
    public void onProviderEnabled(String provider) {
        Toast.makeText(userHomepage.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                try{
                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
                }
                catch(SecurityException e) {
                    bt2.setText(e.toString());
                    Log.d("Error1",e.toString());
                   // tv.setText(e.toString());
                }
                return;
            }
            case 2: {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(tos, null,Sub, null, null);
                }catch(Exception ee)
                {
                    Log.d("Erro1",ee.toString());
                }
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    void getLocation() {
        try {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            bt2.setText(e.toString());
            Log.d("Error1",e.toString());
        }
    }

    }





