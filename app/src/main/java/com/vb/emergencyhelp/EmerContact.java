package com.vb.emergencyhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class EmerContact extends AppCompatActivity implements View.OnClickListener {
    Button police, fire, ambulance, women;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emer_contact);
        police = (Button) findViewById(R.id.police);
        police.setOnClickListener(this);
        fire = (Button) findViewById(R.id.fire);
        fire.setOnClickListener(this);
        ambulance = (Button) findViewById(R.id.ambulance);
        ambulance.setOnClickListener(this);
        women = (Button) findViewById(R.id.women);
        women.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.police) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + "100"));
            startActivity(callIntent);
        } else if (v.getId() == R.id.fire) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + "101"));
            startActivity(callIntent);
        } else if (v.getId() == R.id.ambulance) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + "102"));
            startActivity(callIntent);
        } else if (v.getId() == R.id.women) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + "1091"));
            startActivity(callIntent);
        }
    }

}
