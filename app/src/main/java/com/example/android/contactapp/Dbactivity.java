package com.example.android.contactapp;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class Dbactivity extends AppCompatActivity {
    TextView fnTV;
    TextView lnTV;
    TextView pTV;
    //dbhelper dbh;
    DataBaseHelper dh;
    private static final String TAG = "ListdataActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbactivity);
        fnTV = (TextView)findViewById(R.id.textView);
        lnTV = (TextView)findViewById(R.id.textView2);
        pTV = (TextView)findViewById(R.id.textView3);
        dh = new DataBaseHelper(this);
       // tV.setText(SQLiteDatabase.);
        Populate();

    }
    protected void Populate(){
        Log.d(TAG, "Populate: populating");
          Cursor data = dh.getData();
//        ArrayList<String> listdataF = new ArrayList<>();
//        ArrayList<String> listdataL = new ArrayList<>();
//        ArrayList<String> listdataP = new ArrayList<>();
        while(data.moveToNext()){
            fnTV.append("\n" + data.getString(1));
            lnTV.append("\n" + data.getString(2));
            pTV.append("\n" + data.getString(3));

        }
    }
}
