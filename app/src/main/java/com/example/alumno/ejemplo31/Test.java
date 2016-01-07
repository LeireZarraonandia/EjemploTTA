package com.example.alumno.ejemplo31;

/**
 * Created by leire on 5/01/16.
 */

public class Test {

    protected String getWording(){

        String preguntaTest="¿Cual de las siguientes opciones NO se indica en el fichero de manifiesto de la app?";

        return preguntaTest;
    }

    public Choice[]getChoices(){

        Choice [] choices= new Choice[5];

        choices[0]=new Choice("Versión de la aplicación",false);
        choices[1]= new Choice("Listado de componentes de la aplicación",false);
        choices[2]= new Choice(  "Opciones del menú de ajustes",true);
        choices[3]= new Choice( "Nivel mínimo de la API Android requerida",false);
        choices[4]= new Choice("Nombre del paquete java de la aplicación",false);

        return choices;

    }


    protected Advice getAdvice()
    {
        //Aqui se creara el objeto advice a partir de los datos que hayan llegado del servidor
        Advice advice= new Advice("http://www.movilzona.es/", Advice.HTML);
        //Advice advice= new Advice("El manifiesto de la aplicacion describe las diversas partes de que se compone la misma: actividades, servicios, broadcast receviers, y content provider de los que esta compuesta la aplicacion.......",Advice.HTML);
        return advice;
    }


    public class Choice{
        private String wording;
        private boolean correcta;

        public Choice(String wording, boolean correcta){

            this.wording=wording;
            this.correcta=correcta;

        }

        public String getWording(){

            return this.wording;
        }

        public boolean isCorrecta(){
            return this.correcta;
        }


    }
    public class Advice
    {
        public final static int HTML=1;
        public final static int IMAGE=2;
        public final static int AUDIO=3;
        public final static int VIDEO=4;
        private String content;
        private int type;

        public Advice(String content,int type)
        {
            this.content=content;
            this.type=type;
        }
        public String getContent()
        {
            return this.content;
        }
        public int getType()
        {
            return this.type;
        }
    }


}