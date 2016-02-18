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
    static String dbName = "emerge";
    String tbName = "help";
    String keyName = "name";
    String keyAddr = "addr";
    String keyPhn = "phone";
    String keyBlood = "blood";
    String keyEname1 = "ename1";
    String keyEname2 = "ename2";
    String keyEno1 = "eno1";
    String keyEno2 = "eno2";

    public DatabaseHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table help (name text,addr text,phone long,blood text,ename1 text,eno1 long,ename2 text,eno2 long)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS " + tbName);

        // Create tables again
        onCreate(db);
    }

    void addDetails(Details d) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(keyName, d.getName());
        cv.put(keyAddr, d.getAddr());
        cv.put(keyPhn, d.getPhone());
        cv.put(keyBlood, d.getBlood());
        cv.put(keyEname1, d.getEname1());
        cv.put(keyEno1, d.getEno1());
        cv.put(keyEname2, d.getEname2());
        cv.put(keyEno2, d.getEno2());
        db.insert(tbName, null, cv);
        db.close();
    }

    Details getDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        Details d = new Details();
        Cursor c = db.query(tbName, new String[]{keyName, keyAddr, keyPhn, keyBlood, keyEname1, keyEno1, keyEname2, keyEno2},
                null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        d = new Details();
        d.setName(c.getString(0));
        d.setAddr(c.getString(1));
        d.setPhone(c.getString(2));
        d.setBlood(c.getString(3));
        d.setEname1(c.getString(4));
        d.setEno1(c.getString(5));
        d.setEname2(c.getString(6));
        d.setEno2(c.getString(7));

        return d;

    }

    int check() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from " + tbName, null);
        if (c.moveToFirst())
            return 1;
        else return 0;
    }

    void editMain(String s1, String s2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(keyAddr, s1);
        cv.put(keyPhn, s2);
        db.update(tbName, cv, null, null);
        db.close();
    }

    void editEmer(String s1, String s2, String s3, String s4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(keyEname1, s1);
        cv.put(keyEno1, s2);
        cv.put(keyEname2, s3);
        cv.put(keyEno2, s4);
        db.update(tbName, cv, null, null);
        db.close();
    }
}
