package com.vb.emergencyhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class Settings extends AppCompatActivity implements OnClickListener {

    TextView main, emergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        main = (TextView) findViewById(R.id.button1);
        main.setOnClickListener(this);
        emergency = (TextView) findViewById(R.id.button2);
        emergency.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            startActivity(new Intent(Settings.this, EditMainDetails.class));

        } else if (v.getId() == R.id.button2) {
            startActivity(new Intent(Settings.this, EditEmergencyDetails.class));

        }
    }
}
