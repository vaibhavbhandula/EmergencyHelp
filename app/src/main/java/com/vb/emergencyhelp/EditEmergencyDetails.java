package com.vb.emergencyhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditEmergencyDetails extends AppCompatActivity implements OnClickListener {

    EditText et1, et2, et3, et4;
    Button b;
    DatabaseHelper dh;
    Details d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emergencey_details);
        et1 = (EditText) findViewById(R.id.Emergency_contacts_name);
        et2 = (EditText) findViewById(R.id.Emergency_contacts_number1);
        et3 = (EditText) findViewById(R.id.Emergency_contacts_number2);
        et4 = (EditText) findViewById(R.id.Emergency_contacts_name2);
        b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(this);
        dh = new DatabaseHelper(this);
        d = new Details();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_emergencey_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                this.finish();
                startActivity(new Intent(EditEmergencyDetails.this, Home.class));
                return true;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        this.finish();
        startActivity(new Intent(this, Settings.class));
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        d = dh.getDetails();
        et1.setTextColor(Color.WHITE);
        et2.setTextColor(Color.WHITE);
        et3.setTextColor(Color.WHITE);
        et4.setTextColor(Color.WHITE);
        String s1 = et1.getText().toString();
        String s2 = et2.getText().toString();
        String s3 = et4.getText().toString();
        String s4 = et3.getText().toString();
        int z = 0;
        int x = 0;
        // TODO Auto-generated method stub

        if (s1.isEmpty() || s2.isEmpty()) {
            z = 1;
        }
        if (s3.isEmpty() || s4.isEmpty() && z != 1) {
            z = 2;
        }
        if ((et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty()) && (et3.getText().toString().isEmpty() || et4.getText().toString().isEmpty())) {

            Toast.makeText(getBaseContext(), "Please fill all the details", 50).show();

        } else if (!s2.isEmpty() && (et2.getText().toString().length() < 10 || et2.getText().toString().length() > 10)) {

            Toast.makeText(this, "Phone number should be of 10 digits", 50).show();

            et2.setTextColor(Color.RED);
            et2.requestFocus();

        } else if (!s4.isEmpty() && (et3.getText().toString().length() < 10 || et3.getText().toString().length() > 10)) {
            Toast.makeText(this, "Phone number should be of 10 digits", 50).show();

            et3.setTextColor(Color.RED);
            et2.requestFocus();

        } else {
            if (z == 0) {
                dh.editEmer(s1, s2, s3, s4);
            } else if (z == 2) {
                String nm = d.getEname2();
                String ph = d.getEno2();
                dh.editEmer(s1, s2, nm, ph);
            } else if (z == 1) {
                String nm = d.getEname1();
                String ph = d.getEno1();
                dh.editEmer(nm, ph, s3, s4);
            }
            /*Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
			this.finish();
			startActivity(new Intent(EditEmergenceyDetails.this,Home.class));*/
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Edit Emergency Details");
            alertDialog.setMessage("Updated");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    startActivity(new Intent(EditEmergencyDetails.this, Home.class));
                    onBackPressed();
                }
            });
            alertDialog.show();
        }


    }
}
