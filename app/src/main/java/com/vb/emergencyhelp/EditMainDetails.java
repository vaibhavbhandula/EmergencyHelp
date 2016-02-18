package com.vb.emergencyhelp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditMainDetails extends AppCompatActivity implements OnClickListener {

    EditText add, phn;
    Button ok;
    DatabaseHelper dh;
    Details d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_main_details);
        add = (EditText) findViewById(R.id.home_address_field);
        phn = (EditText) findViewById(R.id.home_phone_field);
        ok = (Button) findViewById(R.id.button1);
        ok.setOnClickListener(this);
        dh = new DatabaseHelper(this);
        d = new Details();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        d = dh.getDetails();
        String s1 = add.getText().toString();
        String s2 = phn.getText().toString();
        int z = 0;
        if (add.getText().toString().isEmpty() && phn.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), getString(R.string.fill), Toast.LENGTH_LONG).show();
            add.requestFocus();
        } else {
            if (s1.isEmpty()) {
                z = 1;
            } else if (s2.isEmpty()) {
                z = 2;
            }
            if ((phn.getText().toString().length() < 10 || phn.getText().toString().length() > 10) && z != 2) {
                Toast.makeText(this, getString(R.string.phn), Toast.LENGTH_LONG).show();

                phn.setTextColor(Color.RED);
                phn.requestFocus();
            } else {
                if (z == 0) {
                    dh.editMain(s1, s2);
                } else if (z == 1) {
                    String nm = d.getAddr();
                    dh.editMain(nm, s2);
                } else if (z == 2) {
                    String ph = d.getPhone();
                    dh.editMain(s1, ph);
                }
                //Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Main Details");
                alertDialog.setMessage(getString(R.string.updated));
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                alertDialog.show();

                //startActivity(new Intent(EditMainDetails.this,Home.class));
            }
        }

    }
}
