package com.example.alumno.ejemplo31;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        String frase[]=new String [5];
        frase[0]="Version de la aplicacion";
        frase[1]= "Listado de componentes de la aplicacion";
        frase[2]="Opciones del menu de ajustes";
        frase[3]="Nivel minimo de la API Android requerida";
        frase[4]= "Nombre del paquete de la app";

        findViewById(R.id.ayuda).setVisibility(View.INVISIBLE);
        RadioGroup grupo = (RadioGroup) findViewById(R.id.test_choices);
        for (int i=0;i<5;i++){
            RadioButton boton=new RadioButton (this);
            boton.setText(frase[i]);
            boton.setId(i);
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findViewById(R.id.button_send_test).setVisibility(View.VISIBLE);
                }
            });
            grupo.addView(boton);
        }

    }



    public void enviar (View v)
    {
        int correct=1;
        Color color=new Color();
        Button enviar = (Button)findViewById(R.id.button_send_test);
        TextView advise = (TextView)findViewById(R.id.ayuda);


        RadioGroup grupo = (RadioGroup) findViewById(R.id.test_choices);
        int choices=grupo.getChildCount();

        for(int i=0;i<choices;i++)
        {
            grupo.getChildAt(i).setEnabled(false);
        }

        enviar.setVisibility(View.INVISIBLE);
        grupo.getChildAt(correct).setBackgroundColor(color.GREEN);

        int selected=grupo.getCheckedRadioButtonId();
        if (selected!=correct){
            grupo.getChildAt(selected).setBackgroundColor(color.RED);
            Toast.makeText(this, "Has fallado", Toast.LENGTH_SHORT).show();
            if(advise==null){
                findViewById(R.id.ayuda).setVisibility(View.VISIBLE);
            }
        }else{
            Toast.makeText(this, "Acierto", Toast.LENGTH_SHORT).show();
        }


    }


}
