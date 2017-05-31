package com.example.android.contactapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PrivateKey;

/**
 * Created by Android on 5/30/2017.
 */

public class dbhelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "contacts.db";
    private static final String CONTACTS_TABLE_NAME = "contacts";
    private static final String CONTACTS_COLUMN_ID = "ID";
    private static final String CONTACTS_COLUMN_FNAME = "First";
    private static final String CONTACTS_COLUMN_LNAME = "Last";
    private static final String CONTACTS_COLUMN_FPATH = "Path";

    public dbhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table contactlist" + "(id integer primary key, first text, last text, file_path text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertContact(String first, String last, String path){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CONTACTS_COLUMN_FNAME, first);
        cv.put(CONTACTS_COLUMN_LNAME, last);
        cv.put(CONTACTS_COLUMN_FPATH, path);


    }
    public String getThatShit(){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.get(CONTACTS_COLUMN_FNAME);
        return "";
    }

}
