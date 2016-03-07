package com.vb.emergencyhelp;

/**
 * Created by Vaibhav on 12/11/15.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    static int dbVersion = 1;

    SQLiteDatabase db;

    Details details;

    final static String KEY_DB_NAME = "emergency";
    final static String KEY_TABLE_NAME = "help";
    final static String KEY_NAME = "name";
    final static String KEY_ADDRESS = "address";
    final static String KEY_PHN = "phone";
    final static String KEY_BLOOD = "blood";
    final static String KEY_ENAME_1 = "ename1";
    final static String KEY_ENAME_2 = "ename2";
    final static String KEY_ENO_1 = "eno1";
    final static String KEY_ENO_2 = "eno2";

    public DatabaseHelper(Context context) {
        super(context, KEY_DB_NAME, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table  " + KEY_TABLE_NAME + "("
                + KEY_NAME + " text,"
                + KEY_ADDRESS + " text,"
                + KEY_PHN + " long,"
                + KEY_BLOOD + " text,"
                + KEY_ENAME_1 + " text,"
                + KEY_ENO_1 + " long,"
                + KEY_ENAME_2 + " text,"
                + KEY_ENO_2 + " long)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS " + KEY_TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    void addDetails(Details details) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, details.getName());
        values.put(KEY_ADDRESS, details.getAddress());
        values.put(KEY_PHN, details.getPhone());
        values.put(KEY_BLOOD, details.getBlood());
        values.put(KEY_ENAME_1, details.getEname1());
        values.put(KEY_ENO_1, details.getEno1());
        values.put(KEY_ENAME_2, details.getEname2());
        values.put(KEY_ENO_2, details.getEno2());
        db.insert(KEY_TABLE_NAME, null, values);
        db.close();
    }

    Details getDetails() {
        db = this.getReadableDatabase();
        details = new Details();
        Cursor cursor = db.query(KEY_TABLE_NAME, new String[]{KEY_NAME, KEY_ADDRESS, KEY_PHN, KEY_BLOOD, KEY_ENAME_1, KEY_ENO_1, KEY_ENAME_2, KEY_ENO_2},
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        details.setName(cursor.getString(0));
        details.setAddress(cursor.getString(1));
        details.setPhone(cursor.getString(2));
        details.setBlood(cursor.getString(3));
        details.setEname1(cursor.getString(4));
        details.setEno1(cursor.getString(5));
        details.setEname2(cursor.getString(6));
        details.setEno2(cursor.getString(7));

        cursor.close();
        return details;

    }

    int check() {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + KEY_TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return 1;
        } else {
            cursor.close();
            return 0;
        }

    }

    void editMain(String address, String phone) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ADDRESS, address);
        values.put(KEY_PHN, phone);
        db.update(KEY_TABLE_NAME, values, null, null);
        db.close();
    }

    void editEmergency(String eName1, String eNumber1, String eName2, String eNumber2) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ENAME_1, eName1);
        values.put(KEY_ENO_1, eNumber1);
        values.put(KEY_ENAME_2, eName2);
        values.put(KEY_ENO_2, eNumber2);
        db.update(KEY_TABLE_NAME, values, null, null);
        db.close();
    }
}
