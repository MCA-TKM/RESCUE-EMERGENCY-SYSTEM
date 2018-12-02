package com.example.julie.rescueemergency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WorkshopRegistration extends AppCompatActivity {
public EditText name,phnno,loc;
public Button wregi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_registration);
        name=findViewById(R.id.wname);
        phnno=findViewById(R.id.wphn);
        loc=findViewById(R.id.wloc);
        wregi=findViewById(R.id.wreg);
        wregi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkTask.SendData("WREG"+StartForm.username+"<"+name.getText().toString()+"<"+phnno.getText().toString()+"<"+loc.getText().toString());
                Toast.makeText(MainActivity.Me.getApplicationContext(), "Registration Completed", Toast.LENGTH_LONG).show();
                Intent in = new Intent(StartForm.me.getApplicationContext(), workshopHomepage.class);
                StartForm.me.startActivity(in);
            }
        });
    }
}
