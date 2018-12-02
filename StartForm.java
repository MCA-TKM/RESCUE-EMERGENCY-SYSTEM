package com.example.julie.rescueemergency;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.hardware.SensorListener;
import android.hardware.SensorManager;

public class StartForm extends AppCompatActivity  {

SensorManager sensorMgr;
private static final int SHAKE_THRESHOLD = 800;
public  static  StartForm me;
public EditText uid,pwd;
public Button login,reg;
public static  String username;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_form);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        uid=(EditText)findViewById(R.id.t_uid) ;
       pwd=(EditText)findViewById(R.id.t_pwd) ;
       reg=(Button)findViewById(R.id.btnreg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(),MainActivity.class);
               startActivity(in);
            }
        });
        login=(Button)findViewById(R.id.btnlogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



             NetworkTask.SendData("LOG"+uid.getText().toString()+"<"+pwd.getText().toString());
            }
        });
        me=this;
        NetworkTask nt=new NetworkTask(0);
        nt.execute();
    }


    public  static void NextForm(String utype )
    {
        if(utype.compareTo("user")==0)
        {
            Intent in = new Intent(me.getApplicationContext(), userHomepage.class);
           me.startActivity(in);
        }
        else  if(utype.compareTo("Police Station")==0)
        {
            Intent in = new Intent(me.getApplicationContext(), policestationHomepage.class);
            me.startActivity(in);
        }
        else  if(utype.compareTo("Hospital")==0)
        {
            Intent in = new Intent(me.getApplicationContext(), HospitalHomepage.class);
            me.startActivity(in);
        }
        else  if(utype.compareTo("Workshop")==0)
        {
            Intent in = new Intent(me.getApplicationContext(), workshopHomepage.class);
            me.startActivity(in);
        }
    }
}
