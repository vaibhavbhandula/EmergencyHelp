package com.vb.emergencyhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.content.Intent;
import android.widget.Button;


public class EmergencyContact extends AppCompatActivity implements View.OnClickListener {
    Button police, fire, ambulance, women;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
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
            callIntent.setData(Uri.parse("tel:" + getString(R.string.call_100)));
            startActivity(callIntent);
        } else if (v.getId() == R.id.fire) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + getString(R.string.call_101)));
            startActivity(callIntent);
        } else if (v.getId() == R.id.ambulance) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + getString(R.string.call_102)));
            startActivity(callIntent);
        } else if (v.getId() == R.id.women) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + getString(R.string.call_1091)));
            startActivity(callIntent);
        }
    }

}
