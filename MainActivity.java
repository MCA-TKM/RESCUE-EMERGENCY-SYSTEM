package com.example.julie.rescueemergency;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
EditText name,pass;
Button ok;
String nm , ps;
String item;
public  String type;
List list;
    Spinner spinner;
    public static  String user;
    public static  String utype;
public  static  MainActivity Me;
ArrayAdapter<String> dataAdapter;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Me=this;
        setContentView(R.layout.activity_main);

/*list = [];*/
        name = findViewById(R.id.name);
        pass = findViewById(R.id.passwrd);
        ok = findViewById(R.id.btnok);

    spinner = (Spinner) findViewById(R.id.sp1);
        List<String> categories = new ArrayList<String>();
        categories.add("-----Select-------");
        categories.add("user");
        categories.add("Police Station");
        categories.add("Hospital");
        categories.add("Workshop");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
     /*  sp.setOnItemSelectedListener(this);
       loadSpinnerData();*/

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Toast toast=Toast.makeText(getApplicationContext(),"sucessfully Registered",Toast.LENGTH_SHORT);
                   // toast.show();
                //    Intent intent = new Intent(MainActivity.this,userHomepage.class);
                   // startActivity(intent);
                   StartForm.username=name.getText().toString();
                    utype=spinner.getSelectedItem().toString();
                    NetworkTask.SendData("ADU"+name.getText().toString()+"<"+pass.getText().toString()+"<"+spinner.getSelectedItem().toString());

                }
            });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();

         // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public static void NextForm()
    {

        if(utype.compareTo("user")==0)
        {
            Intent in = new Intent(Me.getApplicationContext(), userreg.class);
            Me.startActivity(in);
        }
        else  if(utype.compareTo("Police Station")==0)
        {
            Intent in = new Intent(Me.getApplicationContext(), PoliceStationReg.class);
            Me.startActivity(in);
        }
        else  if(utype.compareTo("Hospital")==0)
        {
            Intent in = new Intent(Me.getApplicationContext(), HospitalRegistration.class);
            Me.startActivity(in);
        }
        else  if(utype.compareTo("Workshop")==0)
        {
            Intent in = new Intent(Me.getApplicationContext(), WorkshopRegistration.class);
            Me.startActivity(in);
        }
    }
    private void loadSpinnerData() {
        // database handler

        // Spinner Drop down elements
               //rows = db.getDetails();

        // Creating adapter for spinner
//        dataAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, list);
   //     dataAdapter=new ArrayAdapter<String>();
/*dataAdapter.add("User1");
        dataAdapter.add("User2");
        dataAdapter.add("User3");
        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/

        // attaching data adapter to spinner
       // sp.setAdapter(dataAdapter);
    }


        }







