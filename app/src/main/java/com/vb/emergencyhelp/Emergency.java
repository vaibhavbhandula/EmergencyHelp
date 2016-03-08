package com.vb.emergencyhelp;

import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alertdialogpro.AlertDialogPro;


public class Emergency extends Fragment implements View.OnClickListener{

    EditText name1, number1, number2, name2;
    Button ok;
    DatabaseHelper databaseHelper;
    Details details;

    public static Emergency newInstance(){
        Emergency fragment=new Emergency();
        return fragment;
    }
    public Emergency() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(getActivity());
        details = new Details();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_edit_emergencey_details,container,false);

        name1 = (EditText) rootView.findViewById(R.id.Emergency_contacts_name);
        number1 = (EditText) rootView.findViewById(R.id.Emergency_contacts_number1);
        number2 = (EditText) rootView.findViewById(R.id.Emergency_contacts_number2);
        name2 = (EditText) rootView.findViewById(R.id.Emergency_contacts_name2);
        ok = (Button) rootView.findViewById(R.id.update);
        ok.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        details = databaseHelper.getDetails();
        String s1 = name1.getText().toString();
        String s2 = number1.getText().toString();
        String s3 = name2.getText().toString();
        String s4 = number2.getText().toString();
        int z = 0;

        if (s1.isEmpty() || s2.isEmpty()) {
            z = 1;
        }
        if (s3.isEmpty() || s4.isEmpty() && z != 1) {
            z = 2;
        }
        if ((name1.getText().toString().isEmpty() || number1.getText().toString().isEmpty()) && (number2.getText().toString().isEmpty() || name2.getText().toString().isEmpty())) {

            if (name1.getText().toString().isEmpty()) {
                name1.requestFocus();
            } else if (number1.getText().toString().isEmpty()) {
                number1.requestFocus();
            } else if (name2.getText().toString().isEmpty()) {
                name2.requestFocus();
            } else if (number2.getText().toString().isEmpty()) {
                number2.requestFocus();
            }

            Toast.makeText(getActivity(), getString(R.string.fill), Toast.LENGTH_LONG).show();

        } else if (!s2.isEmpty() && (number1.getText().toString().length() < 10 || number1.getText().toString().length() > 10)) {

            Toast.makeText(getActivity(), getString(R.string.phn), Toast.LENGTH_LONG).show();

            number1.setTextColor(Color.RED);
            number1.requestFocus();

        } else if (!s4.isEmpty() && (number2.getText().toString().length() < 10 || number2.getText().toString().length() > 10)) {
            Toast.makeText(getActivity(), getString(R.string.phn), Toast.LENGTH_LONG).show();

            number2.setTextColor(Color.RED);
            number2.requestFocus();

        } else {
            if (z == 0) {
                databaseHelper.editEmergency(s1, s2, s3, s4);
            } else if (z == 2) {
                String nm = details.getEname2();
                String ph = details.getEno2();
                databaseHelper.editEmergency(s1, s2, nm, ph);
            } else if (z == 1) {
                String nm = details.getEname1();
                String ph = details.getEno1();
                databaseHelper.editEmergency(nm, ph, s3, s4);
            }
            /*Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
            this.finish();
			startActivity(new Intent(EditEmergenceyDetails.this,Home.class));*/
            AlertDialogPro.Builder alertDialog = new AlertDialogPro.Builder(getActivity());
            alertDialog.setIcon(R.drawable.ic_launcher);
            alertDialog.setTitle(getString(R.string.emergency_details));
            alertDialog.setMessage(getString(R.string.updated));
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getActivity().finish();
                }
            });
            alertDialog.show();
        }


    }

}
