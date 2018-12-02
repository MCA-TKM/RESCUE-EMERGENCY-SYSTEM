package com.example.julie.rescues;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class userreg extends AppCompatActivity {

    public EditText name,add,phn,carid;
    Button ubtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userreg);
        name=findViewById(R.id.username);
        add=findViewById(R.id.useraddress);
        phn=findViewById(R.id.userphn);
        carid=findViewById(R.id.carid);
        ubtn=findViewById(R.id.userreg);
        ubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkTask.SendData("UREG"+StartForm.username+"<"+name.getText().toString()+"<"+add.getText().toString()+"<"+phn.getText().toString()+"<"+carid.getText().toString());
                Toast.makeText(MainActivity.Me.getApplicationContext(), "Registration Completed", Toast.LENGTH_LONG).show();
                Intent in = new Intent(StartForm.me.getApplicationContext(), userHomepage.class);
                StartForm.me.startActivity(in);
            }
        });

    }
}
