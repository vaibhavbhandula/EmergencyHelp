package com.vb.emergencyhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;



import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;



public class Home extends AppCompatActivity implements OnClickListener,LocationListener{
    ImageButton i1,i2,i3,i4,i5,i6;
    DatabaseHelper dh;
    Details d;
    String provider="";
    String s="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dh=new DatabaseHelper(this);
        if(dh.check()==0){
            startActivity(new Intent(Home.this,MainActivity.class));
            if(dh.check()==0){
                this.finish();
            }
        }

        setContentView(R.layout.activity_home);
        i1=(ImageButton) findViewById(R.id.imageButton1);
        i2=(ImageButton) findViewById(R.id.imageButton2);
        i3=(ImageButton) findViewById(R.id.imageButton3);
        i4=(ImageButton) findViewById(R.id.imageButton4);
        i5=(ImageButton) findViewById(R.id.imageButton5);
        i6=(ImageButton) findViewById(R.id.imageButton6);
        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);
        i4.setOnClickListener(this);
        i5.setOnClickListener(this);
        i6.setOnClickListener(this);
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location l=manager.getLastKnownLocation("network");
        if(l!=null){
            onLocationChanged(l);
        }
        locSett();
    }
    @Override
    public void onBackPressed() {
        this.finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings: this.finish();
                startActivity(new Intent(Home.this,Settings.class));
                return true;
        }
        return true;
    }
    public void locSett(){
        final WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(telephonyManager.getDataState() == 0 && wifiManager.isWifiEnabled() == false && manager.isProviderEnabled(LocationManager.GPS_PROVIDER) == false) {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("First-Responder");
            ad.setMessage("Your network and gps providers are off. Please enable one of them.");
            ad.setPositiveButton("Network", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    AlertDialog.Builder network = new AlertDialog.Builder(Home.this);
                    network.setTitle("First-Responder");
                    network.setMessage("Please choose between Wi-Fi and Mobile Data");
                    network.setPositiveButton("Wi-Fi", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
							/*Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
							startActivity(intent);*/
                            provider = "network";
                            wifiManager.setWifiEnabled(true);
                        }
                    });
                    network.setNegativeButton("Mobile Data", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
							/*Intent intent = new Intent (android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
							startActivity(intent);*/

                            try {
                                setMobileDataEnabled(getBaseContext(), true);
                                provider = "network";
                            } catch (IllegalArgumentException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (NoSuchFieldException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }						}
                    });
                    network.show();
                }
            });
            ad.setNegativeButton("GPS", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                    provider = "gps";


                }
            });
            ad.show();
        }

        if(telephonyManager.getDataState() == TelephonyManager.DATA_CONNECTED) {
            provider = "network";
        } else if (wifiManager.isWifiEnabled()) {
            provider = "network";
        } else if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            provider = "gps";
        }
        Location location = manager.getLastKnownLocation(provider);
        if (location != null) {
            onLocationChanged(location);

        }

    }
    private void setMobileDataEnabled(Context context, boolean enabled) throws ClassNotFoundException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ConnectivityManager conman = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final Class conmanClass = Class.forName(conman.getClass().getName());
        final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
        connectivityManagerField.setAccessible(true);
        final Object connectivityManager = connectivityManagerField.get(conman);
        final Class connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());
        final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
        setMobileDataEnabledMethod.setAccessible(true);

        setMobileDataEnabledMethod.invoke(connectivityManager, enabled);
    }
    @Override
    public void onClick(View v) {
        d=new Details();
        d=dh.getDetails();


        // TODO Auto-generated method stub
        if(v.getId()==R.id.imageButton1){

            //startActivity(new Intent(this,Maps.class));
			/*Intent callIntent = new Intent(Intent.ACTION_CALL);
		    callIntent.setData(Uri.parse("tel:" + d.getEno1()));
		    startActivity(callIntent);
		    String msg1="Hey "+d.getEname1()+" I'm in trouble. Please come to Hospital near location "+s+"";
		    SmsManager smsManager = SmsManager.getDefault();
		    smsManager.sendTextMessage(d.getEno1(), null, msg1, null, null);
		    String msg2="Hey "+d.getEname2()+" I'm in trouble. Please come to Hospital near location "+s+"";
		    smsManager.sendTextMessage(d.getEno2(), null, msg2, null, null);*/
            String s1=String.format(Locale.ENGLISH, "https://www.google.co.in/maps/dir/"+s+"/nearest+hospital");
            Intent in=new Intent(Intent.ACTION_VIEW,Uri.parse(s1));
            startActivity(in);
            //Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }
        else if(v.getId()==R.id.imageButton2){
			/*Intent callIntent = new Intent(Intent.ACTION_CALL);
		    callIntent.setData(Uri.parse("tel:" + d.getEno1()));
		    startActivity(callIntent);
		    String msg1="Hey "+d.getEname1()+" I'm in trouble. Please come to Police Station! near location "+s+"";
		    SmsManager smsManager = SmsManager.getDefault();
		    smsManager.sendTextMessage(d.getEno1(), null, msg1, null, null);
		    String msg2="Hey "+d.getEname2()+" I'm in trouble. Please come to Police Station! near location "+s+"";
		    smsManager.sendTextMessage(d.getEno2(), null, msg2, null, null);*/
            String s1=String.format(Locale.ENGLISH, "https://www.google.co.in/maps/dir/"+s+"/nearest+police+station");
            Intent in=new Intent(Intent.ACTION_VIEW,Uri.parse(s1));
            startActivity(in);

        }
        else if(v.getId()==R.id.imageButton3){
			/*Intent callIntent = new Intent(Intent.ACTION_CALL);
		    callIntent.setData(Uri.parse("tel:" + d.getEno1()));
		    startActivity(callIntent);
		    String msg1="Hey "+d.getEname1()+" I'm in trouble. It's Fire ! Please come here! location "+s+"";
		    SmsManager smsManager = SmsManager.getDefault();
		    smsManager.sendTextMessage(d.getEno1(), null, msg1, null, null);
		    String msg2="Hey "+d.getEname2()+" I'm in trouble. it's Fire ! Please come here! location "+s+"";
		    smsManager.sendTextMessage(d.getEno2(), null, msg2, null, null); */
            String s1=String.format(Locale.ENGLISH, "https://www.google.co.in/maps/dir/"+s+"/nearest+fire+station");
            Intent in=new Intent(Intent.ACTION_VIEW,Uri.parse(s1));
            startActivity(in);
        }
        else if(v.getId()==R.id.imageButton4){
            this.finish();
            startActivity(new Intent(Home.this,EmerContact.class));
        }
        else if(v.getId()==R.id.imageButton5){
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + d.getEno1()));
            startActivity(callIntent);
            String msg1="Hey "+d.getEname1()+" I'm in trouble. Please come here! location "+s+"";
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(d.getEno1(), null, msg1, null, null);
            String msg2="Hey "+d.getEname2()+" I'm in trouble. Please come here! location "+s+"";
            smsManager.sendTextMessage(d.getEno2(), null, msg2, null, null);
        }
        else if(v.getId()==R.id.imageButton6){
            this.finish();
            startActivity(new Intent(Home.this,Settings.class));

        }

    }
    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
        double lat = (double) (location.getLatitude());
        double lng = (double) (location.getLongitude());
        s=lat+","+lng;

    }
    @Override
    public void onProviderDisabled(String arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onProviderEnabled(String arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        // TODO Auto-generated method stub

    }
}
