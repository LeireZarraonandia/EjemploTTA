package com.example.alumno.ejemplo31;

import android.content.Intent;
import android.graphics.Color;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import android.widget.VideoView;
import android.widget.MediaController;

import com.example.alumno.ejemplo31.Test;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;

public class TestActivity extends AppCompatActivity  {
    private int correct;
    private LinearLayout layout;
    private Test test;
    private String advise;
    private String adviseType;
    private Status user;
    private View.OnClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String frase[]=new String [5];
        frase[0]="Version de la aplicacion";
        frase[1]= "Listado de componentes de la aplicacion";
        frase[2]="Opciones del menu de ajustes";
        frase[3]="Nivel minimo de la API Android requerida";
        frase[4]= "Nombre del paquete de la app";

        findViewById(R.id.button_help).setVisibility(View.INVISIBLE);
        RadioGroup grupo = (RadioGroup) findViewById(R.id.test_choices);
        for (int i=0;i<5;i++) {
            RadioButton boton = new RadioButton(this);
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
            findViewById(R.id.button_help).setVisibility(View.VISIBLE);

        }else{
            Toast.makeText(this, "Acierto", Toast.LENGTH_SHORT).show();
        }


    }

    public void consejo(View view) throws IOException{
        RadioGroup group = (RadioGroup) findViewById(R.id.test_choices);
        int selected = group.getCheckedRadioButtonId();


        switch (selected)
        {
            case 0:
                showHtml("https://es.wikipedia.org/wiki/Texto");
                break;
            case 2:
                showVideo("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
                break;
            case 3:
                showAudio();
                break;
            case 4:
                showText();
                break;
            default:
                break;

        }

    }
        private void showHtml(String advice)
        {
            if(advice.substring(0,10).contains("://"))
            {
                Uri uri= Uri.parse(advice);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
            else
            {
                WebView web=new WebView(this);
                web.loadData(advice, "text/html", null);
                web.setBackgroundColor(Color.TRANSPARENT);
                web.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null);
                layout.addView(web);
            }
        }
        private void showVideo(String advice)
        {

            VideoView video=new VideoView(this);
            video.setVideoURI(Uri.parse(advice));
            LinearLayout layout=(LinearLayout)findViewById(R.id.test_layout);
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            video.setLayoutParams(params);

            MediaController controller=new MediaController(this){

                @Override
                public void hide(){}

                @Override
                public boolean dispatchKeyEvent(KeyEvent event){

                    if(event.getKeyCode()==KeyEvent.KEYCODE_BACK)
                        finish();
                    return super.dispatchKeyEvent(event);
                }

            };
            controller.setAnchorView(video);
            video.setMediaController(controller);
            layout.addView(video);
            video.start();
        }
        public void showAudio() throws IOException{

            View view = new View(this);

            AudioPlayer audio = new AudioPlayer(view);
            LinearLayout layout=(LinearLayout)findViewById(R.id.test_layout);

            Uri uri = Uri.parse("http://www.noiseaddicts.com/samples_1w72b820/55.mp3");
            audio.setAudioUri(uri);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(params);

            layout.addView(view);
            audio.start();
            /*View view=new View(this);
            AudioPlayer audio=new AudioPlayer(view);
            LinearLayout layout=(LinearLayout)findViewById(R.id.test_layout);
            audio.setAudioUri(Uri.parse("http://www.noiseaddicts.com/samples_1w72b820/55.mp3"));

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(params);
            layout.addView(view);
            audio.start();*/
        }

        private void showText(){
            LinearLayout layout=(LinearLayout)findViewById(R.id.test_layout);
            String Texto = "<html><body>Texto ayuda <b>HTML<b></body></html>";
            WebView web=new WebView(this);
            web.loadData(Texto, "text/html", null);
            web.setBackgroundColor(Color.TRANSPARENT);
            web.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null);
            layout.addView(web);
        }






    }
