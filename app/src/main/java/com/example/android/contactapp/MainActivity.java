package com.example.android.contactapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.contactapp.dbhelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String TAG = "_TAG";
    Button cButton;
    Button sButton;
    Button bButton;

    EditText fName;
    DataBaseHelper dh;
    EditText lName;
    Boolean picTaken = false;
    //dbhelper SQLOH = new dbhelper(getApplicationContext(), "theDatabase", null, 3);
    //private dbhelper DH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cButton = (Button)findViewById(R.id.button2);
        cButton.setOnClickListener(this);
        sButton = (Button)findViewById(R.id.button);
        sButton.setOnClickListener(this);
        bButton = (Button)findViewById(R.id.button3);
        bButton.setOnClickListener(this);
        fName = (EditText)findViewById(R.id.editText);
        lName = (EditText) findViewById(R.id.editText2);
        dh = new DataBaseHelper(this);
    }
    public void AddData (String newEntry, String newEntryLast, String path){
        boolean insertData = dh.addData(newEntry, newEntryLast, path);
        if(insertData){
            toastMessage("data successfully inserted!!");
        }else {
            toastMessage("something is wrong :(");
            //toastMessage(path);
        }
    }
    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                picTaken = true;
            }
        }
    }
    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        mCurrentPhotoPath = image.getCanonicalPath();
        return image;
    }
//    @Override //do i need???
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            imageBitmap = (Bitmap) extras.get("data");
//
//        }
//    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                dispatchTakePictureIntent();
                break;
            case R.id.button:
                String first = fName.getText().toString();
                String last = lName.getText().toString();
                if(picTaken && first != "" && last != "") {
                    AddData(first, last, mCurrentPhotoPath);
                    toastMessage(first + last + mCurrentPhotoPath);
                }

                //SQLOH.insertContact(first, last, mCurrentPhotoPath);
                break;
            case R.id.button3:
                Intent myIntent = new Intent(MainActivity.this, Dbactivity.class );
                startActivity(myIntent);
                break;
            default:
                return;
        }
    }


}
