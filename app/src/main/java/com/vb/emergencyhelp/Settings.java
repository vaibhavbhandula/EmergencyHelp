package com.vb.emergencyhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Settings extends AppCompatActivity implements OnClickListener {

    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(this);
        b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        this.finish();
        startActivity(new Intent(this, Home.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                this.finish();
                startActivity(new Intent(Settings.this, Home.class));
                return true;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.button1) {
            this.finish();
            startActivity(new Intent(Settings.this, EditMainDetails.class));

        } else if (v.getId() == R.id.button2) {
            this.finish();
            startActivity(new Intent(Settings.this, EditEmergencyDetails.class));

        }
    }
}
