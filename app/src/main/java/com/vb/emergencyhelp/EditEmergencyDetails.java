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

public class EditEmergencyDetails extends AppCompatActivity implements OnClickListener {

    EditText name1, number1, number2, name2;
    Button ok;
    DatabaseHelper dh;
    Details d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emergencey_details);
        name1 = (EditText) findViewById(R.id.Emergency_contacts_name);
        number1 = (EditText) findViewById(R.id.Emergency_contacts_number1);
        number2 = (EditText) findViewById(R.id.Emergency_contacts_number2);
        name2 = (EditText) findViewById(R.id.Emergency_contacts_name2);
        ok = (Button) findViewById(R.id.update);
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
        String s1 = name1.getText().toString();
        String s2 = number1.getText().toString();
        String s3 = name2.getText().toString();
        String s4 = number2.getText().toString();
        int z = 0;
        int x = 0;

        if (s1.isEmpty() || s2.isEmpty()) {
            z = 1;
        }
        if (s3.isEmpty() || s4.isEmpty() && z != 1) {
            z = 2;
        }
        if ((name1.getText().toString().isEmpty() || number1.getText().toString().isEmpty()) && (number2.getText().toString().isEmpty() || name2.getText().toString().isEmpty())) {

            Toast.makeText(getBaseContext(), getString(R.string.fill), Toast.LENGTH_LONG).show();

        } else if (!s2.isEmpty() && (number1.getText().toString().length() < 10 || number1.getText().toString().length() > 10)) {

            Toast.makeText(this, getString(R.string.phn), Toast.LENGTH_LONG).show();

            number1.setTextColor(Color.RED);
            number1.requestFocus();

        } else if (!s4.isEmpty() && (number2.getText().toString().length() < 10 || number2.getText().toString().length() > 10)) {
            Toast.makeText(this, getString(R.string.phn), Toast.LENGTH_LONG).show();

            number2.setTextColor(Color.RED);
            number2.requestFocus();

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
            AlertDialogPro.Builder alertDialog = new AlertDialogPro.Builder(this);
            alertDialog.setIcon(R.drawable.ic_launcher);
            alertDialog.setTitle(getString(R.string.emer_details));
            alertDialog.setMessage(getString(R.string.updated));
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();
                }
            });
            alertDialog.show();
        }


    }
}
