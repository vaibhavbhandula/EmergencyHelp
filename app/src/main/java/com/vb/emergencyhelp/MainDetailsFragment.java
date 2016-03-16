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


public class MainDetailsFragment extends Fragment implements View.OnClickListener {

    EditText address, phone;
    Button ok;
    DatabaseHelper databaseHelper;
    Details details;

    public MainDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(getContext());
        details = new Details();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_main_details, container, false);
        address = (EditText) rootView.findViewById(R.id.home_address_field);
        phone = (EditText) rootView.findViewById(R.id.home_phone_field);
        ok = (Button) rootView.findViewById(R.id.update);
        ok.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        details = databaseHelper.getDetails();
        String s1 = address.getText().toString();
        String s2 = phone.getText().toString();
        int z = 0;
        if (address.getText().toString().isEmpty() && phone.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), getString(R.string.fill), Toast.LENGTH_SHORT).show();
            address.requestFocus();
        } else {
            if (s1.isEmpty()) {
                z = 1;
            } else if (s2.isEmpty()) {
                z = 2;
            }
            if ((phone.getText().toString().length() < 10 || phone.getText().toString().length() > 10) && z != 2) {
                Toast.makeText(getActivity(), getString(R.string.phn), Toast.LENGTH_SHORT).show();
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
                AlertDialogPro.Builder alertDialog = new AlertDialogPro.Builder(getContext());
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.setTitle(getString(R.string.main_ad));
                alertDialog.setMessage(getString(R.string.updated));
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });
                alertDialog.show();

                //startActivity(new Intent(EditMainDetails.this,Home.class));
            }
        }

    }

}
