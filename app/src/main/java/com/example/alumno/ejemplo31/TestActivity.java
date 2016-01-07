package com.example.alumno.ejemplo31;

import android.content.Intent;
import android.graphics.Color;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.VideoView;
import android.widget.MediaController;

import java.io.IOException;

public class TestActivity extends ActionBarActivity implements View.OnClickListener {

    private int correct=0;
    private Test.Advice advise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        


        Data data= new Data();
        Test test= data.getTest();



        TextView textWording=(TextView)findViewById(R.id.menu_test);
        textWording.setText(test.getWording());


        int i=0;

        RadioGroup group= (RadioGroup)findViewById(R.id.test_choices);

        for(Test.Choice choice : test.getChoices()){

            RadioButton radio=new RadioButton(this);
            radio.setText(choice.getWording());
            radio.setOnClickListener(this);//hacer visible el botton enviar
            group.addView(radio);
            if(choice.isCorrecta()){
                correct=i;
            }
            i++;

        }


        advise=test.getAdvice();

    }

    @Override
    public void onClick(View view){

        findViewById(R.id.button_send_test).setVisibility(View.VISIBLE);

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

    /*public void consejo(View view) {

        LinearLayout layout=(LinearLayout)findViewById(R.id.advice_layout);
        switch (advise.getType())
        {
            case Test.Advise.HTML:
                showHtml(advise.getContent(),layout);
                break;
            case Test.Advise.IMAGE:
                break;
            case Test.Advise.AUDIO:
                break;
            case Test.Advise.VIDEO:
                showVideo(advise.getContent(),layout);
                break;
            default:
                break;

        }

    }*/
        private void showHtml(String advice,LinearLayout layout)
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
        private void showVideo(String advice,LinearLayout layout)
        {
            VideoView video=new VideoView(this);
            video.setVideoURI(Uri.parse(advice));
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
        public void showAudio(String advise){
            View view=new View(this);
            AudioPlayer audio=new AudioPlayer(view);
            try {
                audio.setAudioUri(Uri.parse(advise));
            }
            catch(IOException e) {
            }
            LinearLayout layout=(LinearLayout)findViewById(R.id.test_layout);
            layout.addView(view);
            audio.start();
        }

    }
