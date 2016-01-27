package com.vb.emergencyhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.R.color;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditMainDetails extends AppCompatActivity implements OnClickListener{

    EditText et1,et2;
    Button b;
    DatabaseHelper dh;
    Details d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_main_details);
        et1=(EditText) findViewById(R.id.home_address_field);
        et2=(EditText) findViewById(R.id.home_phone_field);
        b=(Button) findViewById(R.id.button1);
        b.setOnClickListener(this);
        dh=new DatabaseHelper(this);
        d=new Details();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_main_details, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        this.finish();
        startActivity(new Intent(this,Settings.class));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.Home: this.finish();
                startActivity(new Intent(EditMainDetails.this,Home.class));
                return true;
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        d=dh.getDetails();
        et1.setTextColor(Color.WHITE);
        et2.setTextColor(color.white);
        String s1=et1.getText().toString();
        String s2=et2.getText().toString();
        int z=0;
        // TODO Auto-generated method stub
        if(et1.getText().toString().isEmpty()&& et2.getText().toString().isEmpty()){
            Toast.makeText(getBaseContext(), "Please fill all the details", 50).show();
            et1.requestFocus();
        }
        else{
            if(s1.isEmpty())
            {
                z=1;
            }
            else if(s2.isEmpty()){
                z=2;
            }
            if((et2.getText().toString().length()<10 || et2.getText().toString().length()>10) && z!=2 ){
                Toast.makeText(this, "Phone number should be of 10 digits", 50).show();

                et2.setTextColor(Color.RED);
                et2.requestFocus();
            }
            else
            {
                if(z==0){
                    dh.editMain(s1, s2);
                }
                else if(z==1){
                    String nm=d.getAddr();
                    dh.editMain(nm, s2);
                }
                else if(z==2){
                    String ph=d.getPhone();
                    dh.editMain(s1, ph);
                }
                //Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
                alertDialog.setTitle("Edit Main Details");
                alertDialog.setMessage("Updated");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        startActivity(new Intent(EditMainDetails.this,Home.class));
                        onBackPressed();
                    }
                });
                alertDialog.show();

                //startActivity(new Intent(EditMainDetails.this,Home.class));
            }
        }

    }
}
