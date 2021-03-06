package com.example.alumno.ejemplo31;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by leire on 6/01/16.
 */

public abstract class ProgressTask<T> extends AsyncTask<Void,Void,T>{

    protected final Context context;
    private final ProgressDialog dialog;
    private Exception e;


    public ProgressTask (Context context){
        this.context=context;
        dialog=new ProgressDialog(context);
        dialog.setMessage("Conectando...");
    }

    @Override
    protected void onPreExecute(){
        dialog.show();
    }

    @Override
    protected T doInBackground(Void... params) {
        T result=null;
        try{
            result=work();
        }
        catch(Exception e){
            this.e=e;
        }
        return result;
    }

    @Override
    protected void onPostExecute(T result){
        if( dialog.isShowing()){
            dialog.dismiss();
        }
        if(e!=null){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        else
            onFinish(result);
    }

    protected abstract T work() throws Exception;
    public abstract void onFinish(T result);

}
