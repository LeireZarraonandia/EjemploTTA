package com.example.alumno.ejemplo31;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.prefs.Preferences;

public class ModelActivity extends AppCompatActivity {

    public static final String baseURL="http://u017633.ehu.eus:18080/AlumnoTta/rest/tta";
    protected RestClient rest;
    protected Business server;
    protected Preferences prefs;
    protected Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // data=new Data(getIntent().getExtras());
       // rest = new RestClient(URL);
      //  String auth = data.getAuthToken();
       // if (auth != null)
       //     rest.setAuthorization(auth);
        server = new Business (rest);
        //prefs = new Preferences(this);
    }
}
