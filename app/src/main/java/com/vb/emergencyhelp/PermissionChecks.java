package com.vb.emergencyhelp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import com.alertdialogpro.AlertDialogPro;

/**
 * Created by Vaibhav on 4/1/16.
 */
public class PermissionChecks {

    /* RequestCode for resolutions to get SEND_SMS permission on M */
    public static final int RC_PERM_SEND_SMS = 1;

    /* RequestCode for resolutions to get GET_ACCOUNTS permission on M */
    public static final int RC_PERM_GET_ACCOUNTS = 2;

    /* RequestCode for resolutions to get ACCESS_FINE_LOCATION permission on M */
    public static final int RC_PERM_ACCESS_FINE_LOCATION = 3;

    /* RequestCode for resolutions to get CAMERA permission on M */
    public static final int RC_PERM_CAMERA = 4;

    /* RequestCode for resolutions to get STORAGE permissions on M */
    public static final int RC_PERM_STORAGE = 5;

    /* RequestCode for resolutions to get CALL_PHONE permission on M */
    public static final int RC_PERM_CALL_PHONE = 6;

    /**
     * Check for ANDROID M
     */
    private static boolean checkForAndroidM(){
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
    }

    /**
     * Check if we have the GET_ACCOUNTS permission and request it if we do not.
     * @return true if we have the permission, false if we do not.
     */
    public static boolean checkAccountsPermission(final Activity mActivity) {
        if(checkForAndroidM())
            return true;

        final String perm = Manifest.permission.GET_ACCOUNTS;
        int permissionCheck = ContextCompat.checkSelfPermission(mActivity, perm);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // We have the permission
            return true;

        } else if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, perm)) {
            // Need to show permission rationale, display a snackbar and then request
            // the permission again when the snackbar is dismissed.

            final ViewGroup mLayout = (ViewGroup) ((ViewGroup) mActivity
                    .findViewById(android.R.id.content)).getChildAt(0);

            try {
                Snackbar.make(mLayout,
                        mActivity.getString(R.string.permission_account_rationale, mActivity.getString(R.string.app_name)),
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Request the permission again.
                                ActivityCompat.requestPermissions(mActivity,
                                        new String[]{perm},
                                        RC_PERM_GET_ACCOUNTS);
                            }
                        }).show();
            }catch (Throwable t){

            }
            return false;
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{perm},
                    RC_PERM_GET_ACCOUNTS);
            return false;
        }
    }
    /**
     * Check if we have the ACCESS_FINE_LOCATION permission and request it if we do not.
     * @return true if we have the permission, false if we do not.
     */
    public static boolean checkLocationPermission(final Activity mActivity) {
        if(checkForAndroidM())
            return true;

        final String perm = Manifest.permission.ACCESS_FINE_LOCATION;
        int permissionCheck = ContextCompat.checkSelfPermission(mActivity, perm);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // We have the permission
            return true;
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, perm)) {
            // Need to show permission rationale, display a snackbar and then request
            // the permission again when the snackbar is dismissed.

            final ViewGroup mLayout = (ViewGroup) ((ViewGroup) mActivity
                    .findViewById(android.R.id.content)).getChildAt(0);

            try {
                Snackbar.make(mLayout,
                        mActivity.getString(R.string.permission_location_rationale, mActivity.getString(R.string.app_name)),
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Request the permission again.
                                ActivityCompat.requestPermissions(mActivity,
                                        new String[]{perm},
                                        RC_PERM_ACCESS_FINE_LOCATION);
                            }
                        }).show();
            }catch (Throwable t){

            }
            return false;
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{perm},
                    RC_PERM_ACCESS_FINE_LOCATION);
            return false;
        }
    }

    /**
     * Check if we have the READ_EXTERNAL_STORAGE & WRITE_EXTERNAL_STORAGE permission and request it if we do not.
     * @return true if we have the permission, false if we do not.
     */
    public static boolean checkStoragePermissions(final Activity mActivity) {
        if(checkForAndroidM())
            return true;

        final String perm = Manifest.permission.READ_EXTERNAL_STORAGE;
        final String perm2 = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int permissionCheck = ContextCompat.checkSelfPermission(mActivity, perm);
        int permissionCheck2 = ContextCompat.checkSelfPermission(mActivity, perm2);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED && permissionCheck2 == PackageManager.PERMISSION_GRANTED) {
            // We have the permission
            return true;
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, perm)) {
            // Need to show permission rationale, display a snackbar and then request
            // the permission again when the snackbar is dismissed.

            final ViewGroup mLayout = (ViewGroup) ((ViewGroup) mActivity
                    .findViewById(android.R.id.content)).getChildAt(0);

            try {
                Snackbar.make(mLayout,
                        mActivity.getString(R.string.permission_storage_rationale, mActivity.getString(R.string.app_name)),
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Request the permission again.
                                ActivityCompat.requestPermissions(mActivity,
                                        new String[]{perm},
                                        RC_PERM_STORAGE);
                            }
                        }).show();
            }catch (Throwable t){

            }
            return false;
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{perm, perm2},
                    RC_PERM_STORAGE);
            return false;
        }
    }

    /**
     * Check if we have the CAMERA permission and request it if we do not.
     * @return true if we have the permission, false if we do not.
     */
    public static boolean checkCameraPermission(final Activity mActivity) {
        if(checkForAndroidM())
            return true;

        final String perm = Manifest.permission.CAMERA;
        int permissionCheck = ContextCompat.checkSelfPermission(mActivity, perm);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // We have the permission
            return true;
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, perm)) {
            // Need to show permission rationale, display a snackbar and then request
            // the permission again when the snackbar is dismissed.

            final ViewGroup mLayout = (ViewGroup) ((ViewGroup) mActivity
                    .findViewById(android.R.id.content)).getChildAt(0);

            try {
                Snackbar.make(mLayout,
                        mActivity.getString(R.string.permission_camera_rationale, mActivity.getString(R.string.app_name)),
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Request the permission again.
                                ActivityCompat.requestPermissions(mActivity,
                                        new String[]{perm},
                                        RC_PERM_CAMERA);
                            }
                        }).show();
            }catch (Throwable t){

            }
            return false;
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{perm},
                    RC_PERM_CAMERA);
            return false;
        }
    }

    /**
     * Check if we have the CALL_PHONE permission and request it if we do not.
     * @return true if we have the permission, false if we do not.
     */
    public static boolean checkCallPermission(final Activity mActivity) {
        if(checkForAndroidM())
            return true;

        final String perm = Manifest.permission.CALL_PHONE;
        int permissionCheck = ContextCompat.checkSelfPermission(mActivity, perm);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // We have the permission
            return true;
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, perm)) {
            // Need to show permission rationale, display a snackbar and then request
            // the permission again when the snackbar is dismissed.

            final ViewGroup mLayout = (ViewGroup) ((ViewGroup) mActivity
                    .findViewById(android.R.id.content)).getChildAt(0);

            try {
                Snackbar.make(mLayout,
                        mActivity.getString(R.string.permission_phone_rationale, mActivity.getString(R.string.app_name)),
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Request the permission again.
                                ActivityCompat.requestPermissions(mActivity,
                                        new String[]{perm},
                                        RC_PERM_CALL_PHONE);
                            }
                        }).show();
            }catch (Throwable t){

            }
            return false;
        } else {
            // No explanation needed, we can request the permission.
            try {
                ActivityCompat.requestPermissions(mActivity,
                        new String[]{perm},
                        RC_PERM_CALL_PHONE);
            }catch (Throwable t){

            }
            return false;
        }
    }

    /**
     * Check if we have the SEND_SMS permission and request it if we do not.
     * @return true if we have the permission, false if we do not.
     */
    public static boolean checkSMSPermission(final Activity mActivity) {
        final String perm = Manifest.permission.SEND_SMS;
        int permissionCheck = ContextCompat.checkSelfPermission(mActivity, perm);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // We have the permission
            return true;

        }
        else if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, perm)) {
            // Need to show permission rationale, display a snackbar and then request
            // the permission again when the snackbar is dismissed.

            final ViewGroup mLayout = (ViewGroup) ((ViewGroup) mActivity
                    .findViewById(android.R.id.content)).getChildAt(0);

            try {
                Snackbar.make(mLayout,
                        mActivity.getString(R.string.permission_sms_rationale, mActivity.getString(R.string.app_name)),
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Request the permission again.
                                ActivityCompat.requestPermissions(mActivity,
                                        new String[]{perm},
                                        RC_PERM_SEND_SMS);
                            }
                        }).show();
            } catch (Throwable t) {

            }
            return false;
        }else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{perm},
                    RC_PERM_SEND_SMS);
            return false;
        }
    }

    private static AlertDialogPro.Builder getRationaleDialog(final Activity activity, final String permission, final int requestCode, DialogInterface.OnClickListener cancelListener) {

        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
        if (!showRationale) {
            // user denied flagging NEVER ASK AGAIN
            AlertDialogPro.Builder builder = new AlertDialogPro.Builder(activity) //,android.R.style.Theme_DeviceDefault_Dialog_Alert
                    .setMessage(activity.getString(R.string.permission_dialog_neveragain_message, activity.getString(R.string.app_name)))
                    .setPositiveButton(activity.getString(R.string.permission_dialog_gotosettings), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                            intent.setData(uri);
                            activity.startActivity(intent);
                        }
                    });
            builder.show();
            return null;
        }

        AlertDialogPro.Builder builder = new AlertDialogPro.Builder(activity) //,android.R.style.Theme_DeviceDefault_Dialog_Alert
                .setTitle(getRationalTitleWithPermission(activity, permission))
                .setCancelable(false)
                .setPositiveButton(activity.getString(R.string.permission_dialog_positive), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(activity,
                                new String[]{permission},
                                requestCode);
                    }
                })
                .setNegativeButton(activity.getString(R.string.permission_dialog_negative), cancelListener);
        return builder;
    }

    public static void showRationale(final Activity activity, final String permission, final int requestCode, DialogInterface.OnClickListener cancelListener) {
        AlertDialogPro.Builder builder = getRationaleDialog(activity, permission, requestCode, cancelListener);
        if (builder != null)
            builder.setMessage(getRationalMessageWithPermission(activity, permission))
                    .create()
                    .show();
        else if (cancelListener != null)
            cancelListener.onClick(null, -1);
    }

    public static void showRationale(final Activity activity, final String permission, String message, final int requestCode, DialogInterface.OnClickListener cancelListener) {
        AlertDialogPro.Builder builder = getRationaleDialog(activity, permission, requestCode, cancelListener);
        if (builder != null)
            builder.setMessage(message)
                    .create()
                    .show();
        else if (cancelListener != null)
            cancelListener.onClick(null, -1);
    }

    private static String getRationalTitleWithPermission(Context context, String permission) {
        String title = "";
        switch (permission) {
            case Manifest.permission.ACCESS_FINE_LOCATION:
                title = context.getString(R.string.permission_location_title);
                break;
            case Manifest.permission.CAMERA:
                title = context.getString(R.string.permission_camera_title);
                break;
            case Manifest.permission.READ_EXTERNAL_STORAGE:
                title = context.getString(R.string.permission_storage_title);
                break;
            case Manifest.permission.CALL_PHONE:
                title = context.getString(R.string.permission_phone_title);
                break;
            case Manifest.permission.GET_ACCOUNTS:
                title = context.getString(R.string.permission_contacts_title);
                break;
            case Manifest.permission.SEND_SMS:
                title=context.getString(R.string.permission_sms_title);
                break;
        }
        return title;
    }

    private static String getRationalMessageWithPermission(Context context, String permission) {
        String title = "";
        switch (permission) {
            case Manifest.permission.ACCESS_FINE_LOCATION:
                title = context.getString(R.string.permission_location_message, context.getString(R.string.app_name));
                break;
            case Manifest.permission.CAMERA:
                title = context.getString(R.string.permission_camera_message, context.getString(R.string.app_name));
                break;
            case Manifest.permission.READ_EXTERNAL_STORAGE:
                title = context.getString(R.string.permission_storage_message, context.getString(R.string.app_name));
                break;
            case Manifest.permission.CALL_PHONE:
                title = context.getString(R.string.permission_phone_message, context.getString(R.string.app_name));
                break;
            case Manifest.permission.GET_ACCOUNTS:
                title = context.getString(R.string.permission_contacts_message, context.getString(R.string.app_name));
                break;
            case Manifest.permission.SEND_SMS:
                title=context.getString(R.string.permission_sms_message, context.getString((R.string.app_name)));
                break;
        }
        return title;
    }
}
