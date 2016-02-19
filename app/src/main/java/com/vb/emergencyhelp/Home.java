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
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;

import com.alertdialogpro.AlertDialogPro;


public class Home extends AppCompatActivity implements OnClickListener, LocationListener {
    DatabaseHelper dh;
    Details d;
    String provider = "";
    String locationString = "";

    CardView hospital, police, fire, emergency, sos, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dh = new DatabaseHelper(this);
        if (dh.check() == 0) {
            startActivity(new Intent(Home.this, MainActivity.class));
            if (dh.check() == 0) {
                this.finish();
            }
        }

        setContentView(R.layout.activity_home);

        hospital = (CardView) findViewById(R.id.hospital);
        hospital.setOnClickListener(this);
        police = (CardView) findViewById(R.id.police);
        police.setOnClickListener(this);
        fire = (CardView) findViewById(R.id.fire);
        fire.setOnClickListener(this);
        emergency = (CardView) findViewById(R.id.emergency);
        emergency.setOnClickListener(this);
        sos = (CardView) findViewById(R.id.sos);
        sos.setOnClickListener(this);
        settings = (CardView) findViewById(R.id.settings);
        settings.setOnClickListener(this);

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location l = manager.getLastKnownLocation("network");
        if (l != null) {
            onLocationChanged(l);
        }
        locSett();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void locSett() {
        final WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (telephonyManager.getDataState() == 0 && wifiManager.isWifiEnabled() == false && manager.isProviderEnabled(LocationManager.GPS_PROVIDER) == false) {
            AlertDialogPro.Builder ad = new AlertDialogPro.Builder(this);
            ad.setIcon(R.drawable.ic_launcher);
            ad.setTitle(getString(R.string.first_resp));
            ad.setMessage(getString(R.string.ad_msg));
            ad.setPositiveButton("Network", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AlertDialogPro.Builder network = new AlertDialogPro.Builder(Home.this);
                    network.setIcon(R.drawable.ic_launcher);
                    network.setTitle("Network");
                    network.setMessage(getString(R.string.network_msg));
                    network.setPositiveButton("Wi-Fi", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /*Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                            startActivity(intent);*/
                            provider = "network";
                            wifiManager.setWifiEnabled(true);
                        }
                    });
                    network.setNegativeButton("Mobile Data", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /*Intent intent = new Intent (android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
							startActivity(intent);*/

                            try {
                                setMobileDataEnabled(getBaseContext(), true);
                                provider = "network";
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    network.show();
                }
            });
            ad.setNegativeButton("GPS", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                    provider = "gps";
                }
            });
            ad.show();
        }

        if (telephonyManager.getDataState() == TelephonyManager.DATA_CONNECTED) {
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
        final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final Class conmanClass = Class.forName(conman.getClass().getName());
        final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
        connectivityManagerField.setAccessible(true);
        final Object connectivityManager = connectivityManagerField.get(conman);
        final Class connectivityManagerClass = Class.forName(connectivityManager.getClass().getName());
        final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
        setMobileDataEnabledMethod.setAccessible(true);

        setMobileDataEnabledMethod.invoke(connectivityManager, enabled);
    }

    @Override
    public void onClick(View v) {
        d = new Details();
        d = dh.getDetails();


        if (v.getId() == R.id.hospital) {
            /*Intent callIntent = new Intent(Intent.ACTION_CALL);
		    callIntent.setData(Uri.parse("tel:" + d.getEno1()));
		    startActivity(callIntent);
		    String msg1="Hey "+d.getEname1()+" I'm in trouble. Please come to Hospital near location "+s+"";
		    SmsManager smsManager = SmsManager.getDefault();
		    smsManager.sendTextMessage(d.getEno1(), null, msg1, null, null);
		    String msg2="Hey "+d.getEname2()+" I'm in trouble. Please come to Hospital near location "+s+"";
		    smsManager.sendTextMessage(d.getEno2(), null, msg2, null, null);*/
            String s1 = String.format(Locale.ENGLISH, getString(R.string.url) + locationString + getString(R.string.nearest_hospital));
            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(s1));
            startActivity(in);
            //Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        } else if (v.getId() == R.id.police) {
			/*Intent callIntent = new Intent(Intent.ACTION_CALL);
		    callIntent.setData(Uri.parse("tel:" + d.getEno1()));
		    startActivity(callIntent);
		    String msg1="Hey "+d.getEname1()+" I'm in trouble. Please come to Police Station! near location "+s+"";
		    SmsManager smsManager = SmsManager.getDefault();
		    smsManager.sendTextMessage(d.getEno1(), null, msg1, null, null);
		    String msg2="Hey "+d.getEname2()+" I'm in trouble. Please come to Police Station! near location "+s+"";
		    smsManager.sendTextMessage(d.getEno2(), null, msg2, null, null);*/
            String s1 = String.format(Locale.ENGLISH, getString(R.string.url) + locationString + getString(R.string.nearest_police));
            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(s1));
            startActivity(in);

        } else if (v.getId() == R.id.fire) {
			/*Intent callIntent = new Intent(Intent.ACTION_CALL);
		    callIntent.setData(Uri.parse("tel:" + d.getEno1()));
		    startActivity(callIntent);
		    String msg1="Hey "+d.getEname1()+" I'm in trouble. It's Fire ! Please come here! location "+s+"";
		    SmsManager smsManager = SmsManager.getDefault();
		    smsManager.sendTextMessage(d.getEno1(), null, msg1, null, null);
		    String msg2="Hey "+d.getEname2()+" I'm in trouble. it's Fire ! Please come here! location "+s+"";
		    smsManager.sendTextMessage(d.getEno2(), null, msg2, null, null); */
            String s1 = String.format(Locale.ENGLISH, getString(R.string.url) + locationString + getString(R.string.nearest_fire));
            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(s1));
            startActivity(in);
        } else if (v.getId() == R.id.emergency) {
            startActivity(new Intent(Home.this, EmerContact.class));
        } else if (v.getId() == R.id.sos) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + d.getEno1()));
            startActivity(callIntent);
            String msg1 = getString(R.string.hey) + " " + d.getEname1() + " " + getString(R.string.trouble) + " " + locationString + "";
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(d.getEno1(), null, msg1, null, null);
            String msg2 = getString(R.string.hey) + " " + d.getEname2() + " " + getString(R.string.trouble) + " " + locationString + "";
            smsManager.sendTextMessage(d.getEno2(), null, msg2, null, null);
        } else if (v.getId() == R.id.settings) {
            startActivity(new Intent(Home.this, Settings.class));

        }

    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = (double) (location.getLatitude());
        double lng = (double) (location.getLongitude());
        locationString = lat + "," + lng;
    }

    @Override
    public void onProviderDisabled(String arg0) {

    }

    @Override
    public void onProviderEnabled(String arg0) {

    }

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

    }
}
