package com.example.julie.rescueemergency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartForm extends AppCompatActivity {
public  static  StartForm me;
public EditText uid,pwd;
public Button login,reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_form);
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
}
