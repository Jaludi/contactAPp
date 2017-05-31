package com.example.android.contactapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.security.PublicKey;

/**
 * Created by Android on 5/30/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "contacts.db";
    private static  final String TABLE_NAME = "contacts";
    private static final String COL1 = "ID";
    private static final String COL2 = "FIRSTNAME";
    private static final String COL3 = "LASTNAME";
    private static final String COL4 = "PATH";
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " FIRSTNAME TEXT, LASTNAME TEXT, PATH TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public boolean addData(String fname, String lname, String path){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL2, fname);
        cv.put(COL3, lname);
        cv.put(COL4, path);
        Log.d(TAG, "addData: "+ cv.size());
        Log.d(TAG, "addData: ADDING " + fname + " " + lname + " " + path + " To " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            return false;}
        else{
        return true;}
    }


}
