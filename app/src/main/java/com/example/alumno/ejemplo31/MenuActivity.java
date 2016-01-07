package com.example.alumno.ejemplo31;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MenuActivity extends ModelActivity {
    public final RestClient rest=new RestClient(baseURL);
    Business business=new Business(rest);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        String bienvenido;
        TextView textBienvenido = (TextView) findViewById(R.id.menu_tittle);
        bienvenido="Bienvenido "+intent.getStringExtra(MainActivity.EXTRA_LOGIN);
        textBienvenido.setText(bienvenido);

        TextView textLeccion = (TextView) findViewById(R.id.menu_lesson);
        textLeccion.setText("Leccion 1");
    }

    public void test (View view) {
        Intent intent = new Intent(this, TestActivity.class);

        startActivity(intent);
    }

    public void exercise (View view) {
        final Intent intent = new Intent(this, ExerciseActivity.class);

        new ProgressTask<Exercise>(this){
            @Override
            protected Exercise work()throws Exception{
                return business.getExercise(1);//devuelve el enunciado del ejercicio
            }
            @Override
            public void onFinish(Exercise result){
                TextView textWording=(TextView)findViewById(R.id.exercise_wording);
                textWording.setText(result.getWording());
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "onFinish", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
