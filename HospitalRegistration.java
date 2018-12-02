package com.example.julie.rescueemergency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HospitalRegistration extends AppCompatActivity {
    public EditText name,phone,loc,address;
    public Button bt;
    public static HospitalRegistration Me;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_registration);
        name=findViewById(R.id.hospname);
        phone=findViewById(R.id.hospphn);
        loc=findViewById(R.id.hloc);
        address=findViewById(R.id.hospadd);
        bt=findViewById(R.id.hospreg);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkTask.SendData("HREG"+StartForm.username+"<"+name.getText().toString()+"<"+address.getText().toString()+"<"+loc.getText().toString()+"<"+phone.getText().toString());
                Toast.makeText(MainActivity.Me.getApplicationContext(), "Registration Completed", Toast.LENGTH_LONG).show();
                Intent in = new Intent(StartForm.me.getApplicationContext(), HospitalHomepage.class);
                StartForm.me.startActivity(in);
            }
        });

    }
}
