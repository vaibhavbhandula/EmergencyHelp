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
    Button b1, b2, b3, b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emer_contact);
        b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(this);
        b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(this);
        b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(this);
        b4 = (Button) findViewById(R.id.button4);
        b4.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        this.finish();
        startActivity(new Intent(EmerContact.this, Home.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.emer_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                this.finish();
                startActivity(new Intent(EmerContact.this, Home.class));
                return true;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.button1) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + "100"));
            startActivity(callIntent);
        } else if (v.getId() == R.id.button2) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + "101"));
            startActivity(callIntent);
        } else if (v.getId() == R.id.button3) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + "102"));
            startActivity(callIntent);
        } else if (v.getId() == R.id.button4) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + "1091"));
            startActivity(callIntent);
        }
    }

}
