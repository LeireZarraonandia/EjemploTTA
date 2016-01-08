package com.example.alumno.ejemplo31;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExerciseActivity extends AppCompatActivity {

    public final static int READ_REQUEST_CODE=1;
    public final static int VIDEO_REQUEST_CODE=2;
    public final static int AUDIO_REQUEST_CODE=3;
    public final static int PICTURE_REQUEST_CODE=4;
    private Uri pictureURI;
    private Status user;
    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_exercise);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);


            }

    public void sendFile (View v){

        Intent i=new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        startActivityForResult(i, READ_REQUEST_CODE);

    }

    public void sendPicture (View v){
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
            Toast.makeText(this, "No hay camara", Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager())!= null){
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                try {
                    File file = File.createTempFile("tta", ".jpg", dir);
                    Uri pictureUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                    startActivityForResult(intent, PICTURE_REQUEST_CODE);
                } catch (IOException e) {}

                }
            else
                Toast.makeText(this, "No hay aplicacion", Toast.LENGTH_SHORT).show();
        }
    }

    public void recordVideo (View v){
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
            Toast.makeText(this, "No hay camara", Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (intent.resolveActivity(getPackageManager())!= null){
                startActivityForResult(intent, VIDEO_REQUEST_CODE);

            }
            else
                Toast.makeText(this, "No hay aplicacion", Toast.LENGTH_SHORT).show();
        }
    }

    public void recordAudio (View v){
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE))
            Toast.makeText(this, "No hay micro", Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if (intent.resolveActivity(getPackageManager())!= null){
                startActivityForResult(intent, AUDIO_REQUEST_CODE);
            }
            else
                Toast.makeText(this, "No hay aplicacion", Toast.LENGTH_SHORT).show();
        }
    }


    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode != Activity.RESULT_OK)
            return;
        switch(requestCode){
            case READ_REQUEST_CODE:
               // sendFile(data.getData());
                break;
            case VIDEO_REQUEST_CODE:
                //sendFile(data.getData());
                break;
            case AUDIO_REQUEST_CODE:
                //sendFile(data.getData());
                break;
            case PICTURE_REQUEST_CODE:
                //sendFile(pictureURI);
                break;
        }
    }


}
