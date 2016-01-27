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

    static int dbversion = 1;
    static String dbname = "emerge";
    String tbname = "help";
    String keyname = "name";
    String keyaddr = "addr";
    String keyphn = "phone";
    String keyblood = "blood";
    String keyename1 = "ename1";
    String keyename2 = "ename2";
    String keyeno1 = "eno1";
    String keyeno2 = "eno2";

    public DatabaseHelper(Context context) {
        super(context, dbname, null, dbversion);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("Create table help (name text,addr text,phone long,blood text,ename1 text,eno1 long,ename2 text,eno2 long)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + tbname);

        // Create tables again
        onCreate(db);
    }

    void addDetails(Details d) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(keyname, d.getName());
        cv.put(keyaddr, d.getAddr());
        cv.put(keyphn, d.getPhone());
        cv.put(keyblood, d.getBlood());
        cv.put(keyename1, d.getEname1());
        cv.put(keyeno1, d.getEno1());
        cv.put(keyename2, d.getEname2());
        cv.put(keyeno2, d.getEno2());
        db.insert(tbname, null, cv);
        db.close();
    }

    Details getDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        Details d = new Details();
        Cursor c = db.query(tbname, new String[]{keyname, keyaddr, keyphn, keyblood, keyename1, keyeno1, keyename2, keyeno2},
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
        Cursor c = db.rawQuery("Select * from " + tbname, null);
        if (c.moveToFirst())
            return 1;
        else return 0;
    }

    void editMain(String s1, String s2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(keyaddr, s1);
        cv.put(keyphn, s2);
        db.update(tbname, cv, null, null);
        db.close();
    }

    void editEmer(String s1, String s2, String s3, String s4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(keyename1, s1);
        cv.put(keyeno1, s2);
        cv.put(keyename2, s3);
        cv.put(keyeno2, s4);
        db.update(tbname, cv, null, null);
        db.close();
    }
}
