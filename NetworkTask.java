package com.example.julie.rescueemergency;



        import java.io.DataInputStream;
        import java.io.PrintStream;
        import java.math.BigInteger;
        import java.net.Socket;
        import java.util.HashMap;

        import android.content.Intent;
        import android.os.AsyncTask;
        import android.telephony.SmsManager;
        import android.util.Log;
        import android.widget.Toast;

public class NetworkTask extends AsyncTask<Void, Void, Void> {
    public static	Socket so;
    public static		DataInputStream din;
    public static		PrintStream ps;
public int Mode;
public static String utype;
public String response;
public String error="";
    NetworkTask(int m){
   Mode=m;
    }


    @Override
    protected Void doInBackground(Void... arg0) {



        try {
            if(Mode==0)
            {
                try{
                    so = new Socket("192.168.43.137",9000);
                    ps=new PrintStream(so.getOutputStream());
                    din=new DataInputStream(so.getInputStream());
response="";
                    Log.d("Error", "Connected");
                }catch(Exception ee)
                {
                    error=ee.toString();
                    Log.d("Error", ee.toString());

                }
            }
            else
            {
                while(true) {
                    if (Timers.Me != null && Timers.IsEnable && din.available() <= 0) {
                        try {
                            Thread.sleep(1000);
                        }catch(Exception ee)
                        {

                        }
                        response = null;
                        break;
                    }
                    if(din.available()>0) {
                        response = din.readLine();
                        break;
                    }
                }


            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
    }
public static void SendData(String sg)
{
    ps.println(sg);
    ps.flush();
}
    @Override
    protected void onPostExecute(Void result) {

        super.onPostExecute(result);
        try{
        try{
            if(error!=null)
            {
              //  Toast.makeText(StartForm.me.getApplicationContext(), error, Toast.LENGTH_LONG).show();
                //error=null;
            }
            if(response!=null) {
                if(response.startsWith("MOB"))
                {
                    response=response.substring((3);
                    int k=response.indexOf("<");
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(response.substring(0,k), null, response.substring(k+1), null, null);
                }
              else  if (response.startsWith("ADUS")) {
                    MainActivity.NextForm();
                } else if (response.startsWith("ADUF")) {
                    Toast.makeText(MainActivity.Me.getApplicationContext(), "User Exist", Toast.LENGTH_LONG).show();
                } else if (response.startsWith("LOGS")) {
                    utype = response.substring(4);
                    StartForm.NextForm(utype);


                }
        /*    else if(response.startsWith("PREGS"))
            {
                Toast.makeText(PoliceStationReg.Me.getApplicationContext(), "Registartion Completed", Toast.LENGTH_LONG).show();

            }*/
                else if (response.startsWith("LOGF")) {
                    Toast.makeText(StartForm.me.getApplicationContext(), "Invalid UserID/Password", Toast.LENGTH_LONG).show();
                }

            }
            if(Timers.Me!=null&&Timers.IsEnable)
            {
                Timers.Me.NextRun();

            }


                }catch(Exception ee){ ee.printStackTrace(); }
                Thread.sleep(2);
                NetworkTask myClientTask = new NetworkTask( 1);
                myClientTask.execute();


        }catch(Exception ee)
        {

        }
    }

}

