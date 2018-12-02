package com.example.julie.rescueemergency;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.util.Date;



/**
 * Created by User on 20-11-2018.
 */

public class Timers {
    public static TextView tv;
    public static long starttime;
    public static long delay;
    public static long diff;
    public static boolean status;
    public static Timers Me;
    public static boolean IsEnable;

    public Timers(TextView tvv, long ti, long de) {
        status = true;
        tv = tvv;
        starttime = ti;
        delay = de;
        diff = 1;
        IsEnable = true;
        Me = this;

        //  new Thread(this).start();
    }

    public void NextRun() {
        try {

            diff = 1;
            long lastdiff = diff;
            if (diff > 0) {
                long ct = new Date().getTime();
                diff = ct - starttime;
                diff = diff / 1000;
                diff = delay - diff;
                if (diff < 0)
                    diff = 0;


                //   Thread.sleep(1000);

                if (lastdiff != diff)

                    tv.setText("Remaining " + diff);

            }
        } catch (Exception ee) {

        }

        if (diff <= 0) {

            IsEnable = false;
        }


    }

    public void Process() {
        userHomepage.Me.SendRequest();
    }
}
/**
 * Created by User on 20-11-2018.
 */

/*
public class Timers extends AsyncTask<Void, Void, Void> {
  public static TextView tv;
  public static long starttime;
 public  static   long delay;
 public  static  long diff;
 public static boolean status;
   public  Timers(TextView tvv,long ti,long de)
   {
       status=true;
       tv=tvv;
       starttime=ti;
       delay=de;
       execute();
   }
    public  Timers()
    {

        execute();
    }
    @Override
    protected Void doInBackground(Void... arg0) {
        try{


            long ct=new Date().getTime();
           diff=ct-starttime;
            diff=diff/1000;
            diff=delay-diff;
            if(diff<0)
                diff=0;


            Thread.sleep(1000);
        }catch(Exception ee)
        {

        }
        return null;
    }
    protected void onPostExecute(Void result) {

     //  if(status)
        Log.d("Error",""+diff);
        tv.setText("Remaining "+diff);
        if(diff>0)
        {
            new Timers();
        }
        else
           userHomepage.Me.SendRequest();
    }
}
*/