package com.vb.emergencyhelp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alertdialogpro.AlertDialogPro;

public class EditMainDetails extends AppCompatActivity implements OnClickListener {

    EditText address, phone;
    Button ok;
    DatabaseHelper databaseHelper;
    Details details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_main_details);
        address = (EditText) findViewById(R.id.home_address_field);
        phone = (EditText) findViewById(R.id.home_phone_field);
        ok = (Button) findViewById(R.id.update);
        ok.setOnClickListener(this);
        databaseHelper = new DatabaseHelper(this);
        details = new Details();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        details = databaseHelper.getDetails();
        String s1 = address.getText().toString();
        String s2 = phone.getText().toString();
        int z = 0;
        if (address.getText().toString().isEmpty() && phone.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), getString(R.string.fill), Toast.LENGTH_LONG).show();
            address.requestFocus();
        } else {
            if (s1.isEmpty()) {
                z = 1;
            } else if (s2.isEmpty()) {
                z = 2;
            }
            if ((phone.getText().toString().length() < 10 || phone.getText().toString().length() > 10) && z != 2) {
                Toast.makeText(this, getString(R.string.phn), Toast.LENGTH_LONG).show();
                phone.setTextColor(Color.RED);
                phone.requestFocus();
            } else {
                if (z == 0) {
                    databaseHelper.editMain(s1, s2);
                } else if (z == 1) {
                    String nm = details.getAddress();
                    databaseHelper.editMain(nm, s2);
                } else if (z == 2) {
                    String ph = details.getPhone();
                    databaseHelper.editMain(s1, ph);
                }
                //Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                AlertDialogPro.Builder alertDialog = new AlertDialogPro.Builder(this);
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.setTitle(getString(R.string.main_ad));
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
