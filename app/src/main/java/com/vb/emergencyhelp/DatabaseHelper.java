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

    final static String KEY_DB_NAME = "emerge";
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

        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS " + KEY_TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    void addDetails(Details d) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, d.getName());
        cv.put(KEY_ADDRESS, d.getAddress());
        cv.put(KEY_PHN, d.getPhone());
        cv.put(KEY_BLOOD, d.getBlood());
        cv.put(KEY_ENAME_1, d.getEname1());
        cv.put(KEY_ENO_1, d.getEno1());
        cv.put(KEY_ENAME_2, d.getEname2());
        cv.put(KEY_ENO_2, d.getEno2());
        db.insert(KEY_TABLE_NAME, null, cv);
        db.close();
    }

    Details getDetails() {
        db = this.getReadableDatabase();
        Details d = new Details();
        Cursor c = db.query(KEY_TABLE_NAME, new String[]{KEY_NAME, KEY_ADDRESS, KEY_PHN, KEY_BLOOD, KEY_ENAME_1, KEY_ENO_1, KEY_ENAME_2, KEY_ENO_2},
                null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        d = new Details();
        d.setName(c.getString(0));
        d.setAddress(c.getString(1));
        d.setPhone(c.getString(2));
        d.setBlood(c.getString(3));
        d.setEname1(c.getString(4));
        d.setEno1(c.getString(5));
        d.setEname2(c.getString(6));
        d.setEno2(c.getString(7));

        db.close();
        return d;

    }

    int check() {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from " + KEY_TABLE_NAME, null);
        db.close();
        if (c.moveToFirst())
            return 1;
        else return 0;
    }

    void editMain(String s1, String s2) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ADDRESS, s1);
        cv.put(KEY_PHN, s2);
        db.update(KEY_TABLE_NAME, cv, null, null);
        db.close();
    }

    void editEmergency(String s1, String s2, String s3, String s4) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ENAME_1, s1);
        cv.put(KEY_ENO_1, s2);
        cv.put(KEY_ENAME_2, s3);
        cv.put(KEY_ENO_2, s4);
        db.update(KEY_TABLE_NAME, cv, null, null);
        db.close();
    }
}
