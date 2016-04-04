package com.vb.emergencyhelp;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.content.Intent;
import android.widget.Button;


public class EmergencyContact extends AppCompatActivity implements View.OnClickListener {
    Button police, fire, ambulance, women;
    String phone_number="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
        police = (Button) findViewById(R.id.police);
        police.setOnClickListener(this);
        fire = (Button) findViewById(R.id.fire);
        fire.setOnClickListener(this);
        ambulance = (Button) findViewById(R.id.ambulance);
        ambulance.setOnClickListener(this);
        women = (Button) findViewById(R.id.women);
        women.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.police) {
            phone_number=getString(R.string.call_100);
            if(PermissionChecks.checkCallPermission(this)){
                tryToCall();
            }
        } else if (v.getId() == R.id.fire) {
            phone_number=getString(R.string.call_101);
            if(PermissionChecks.checkCallPermission(this)){
                tryToCall();
            }
        } else if (v.getId() == R.id.ambulance) {
            phone_number=getString(R.string.call_102);
            if(PermissionChecks.checkCallPermission(this)){
                tryToCall();
            }
        } else if (v.getId() == R.id.women) {
            phone_number=getString(R.string.call_1091);
            if(PermissionChecks.checkCallPermission(this)){
                tryToCall();
            }
        }
    }

    public void tryToCall(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone_number));
        startActivity(callIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PermissionChecks.RC_PERM_CALL_PHONE:
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    tryToCall();
                } else {
                    if (permissions.length > 0) {
                        PermissionChecks.showRationale(this, permissions[0], PermissionChecks.RC_PERM_CALL_PHONE, null);
                    }
                }
                break;
        }
    }
}
