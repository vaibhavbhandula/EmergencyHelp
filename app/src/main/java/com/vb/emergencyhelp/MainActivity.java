package com.vb.emergencyhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    EditText et1, et2, et3, et4, et5, et6, et7;
    Spinner sp;
    Button b1, b2;
    DatabaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.home_name_field);
        et2 = (EditText) findViewById(R.id.home_address_field);
        et3 = (EditText) findViewById(R.id.home_phone_field);
        et4 = (EditText) findViewById(R.id.Emergency_contacts_name);
        et5 = (EditText) findViewById(R.id.Emergency_contacts_number1);
        et6 = (EditText) findViewById(R.id.Emergency_contacts_number2);
        et7 = (EditText) findViewById(R.id.Emergency_contacts_name2);
        sp = (Spinner) findViewById(R.id.blood_type_spinner);
        b1 = (Button) findViewById(R.id.save_button);
        b2 = (Button) findViewById(R.id.cancel_button);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        dh = new DatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        if (v.getId() == R.id.save_button) {
            et1.setTextColor(Color.BLACK);
            et2.setTextColor(Color.BLACK);
            et3.setTextColor(Color.BLACK);
            et4.setTextColor(Color.BLACK);
            et5.setTextColor(Color.BLACK);
            et6.setTextColor(Color.BLACK);
            et7.setTextColor(Color.BLACK);
            if (et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() || et3.getText().toString().isEmpty() || et4.getText().toString().isEmpty() || et5.getText().toString().isEmpty() || et6.getText().toString().isEmpty() || sp.getSelectedItemPosition() == 0) {
                Toast.makeText(getBaseContext(), "Please fill all the details", 50).show();
            } else if (et3.getText().toString().length() < 10 || et3.getText().toString().length() > 10) {
                Toast.makeText(this, "Phone number should be of 10 digits", 50).show();

                et3.setTextColor(Color.RED);
                et3.requestFocus();
            } else if (et5.getText().toString().length() < 10 || et5.getText().toString().length() > 10) {
                Toast.makeText(this, "Phone number should be of 10 digits", 50).show();
                et5.setTextColor(Color.RED);
                et5.requestFocus();
            } else if (et6.getText().toString().length() < 10 || et6.getText().toString().length() > 10) {
                Toast.makeText(this, "Phone number should be of 10 digits", 50).show();
                et6.setTextColor(Color.RED);
                et6.requestFocus();
            } else {
                int i = sp.getSelectedItemPosition();
                String s = (String) sp.getItemAtPosition(i);
                String nm = et1.getText().toString();
                String add = et2.getText().toString();
                String phn = et3.getText().toString();
                String enm1 = et4.getText().toString();
                String eno1 = et5.getText().toString();
                String eno2 = et6.getText().toString();
                String enm2 = et7.getText().toString();

                dh.addDetails(new Details(nm, add, phn, s, enm1, eno1, enm2, eno2));
                Intent in = new Intent(this, Home.class);
                /*Bundle b=new Bundle();
				b.putString("name",nm);
				in.putExtras(b);*/
                startActivity(in);
                this.finish();
            }

        }
        if (v.getId() == R.id.cancel_button) {
            this.finish();

        }

    }
}
