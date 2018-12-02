package com.example.julie.rescueemergency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PoliceStationReg extends AppCompatActivity {
public EditText name,phone,loc;
public Button but;
public static PoliceStationReg Me;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_station_reg);
        name=findViewById(R.id.pname);
        phone=findViewById(R.id.pphno);
        loc=findViewById(R.id.ploc);
        but=(Button)findViewById(R.id.preg);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            NetworkTask.SendData("PREG"+StartForm.username+"<"+name.getText().toString()+"<"+phone.getText().toString()+"<"+loc.getText().toString());
                Toast.makeText(MainActivity.Me.getApplicationContext(), "Registration Completed", Toast.LENGTH_LONG).show();
                Intent in = new Intent(StartForm.me.getApplicationContext(), policestationHomepage.class);
                StartForm.me.startActivity(in);
            }
        });
    }
}
