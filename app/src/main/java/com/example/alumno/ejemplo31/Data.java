package com.example.alumno.ejemplo31;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import com.example.alumno.ejemplo31.RestClient;
import com.example.alumno.ejemplo31.Exercise;
import com.example.alumno.ejemplo31.Status;
import com.example.alumno.ejemplo31.Test;


/**
 * Created by leire on 5/01/16.
 */
public class Data {

    String urlBase = "http://u017633.ehu.es:18080/AlumnoTta/rest/tta";

    RestClient restclient;

    public Data(String user, String passwd) {
        restclient = new RestClient(urlBase);
        restclient.setHttpBasicAuth(user, passwd);
    }
    public Test getTest(int id) throws IOException, JSONException{

            JSONObject json = restclient.getJson(String.format("getTest?id=%d", id));

            String generalWording = json.getString("wording");
            JSONArray choicesArray = json.getJSONArray("choices");
            int length = choicesArray.length();
            int[] idChoice = new int[length];
            String[] advise = new String[length];
            String[] wordingChoice = new String[length];
            boolean[] correct = new boolean[length];
            String[] resourceType = new String[length];
            for (int i = 0; i < length; i++) {
                JSONObject jsonChoices = choicesArray.getJSONObject(i);
                idChoice[i] = jsonChoices.getInt("id");
                advise[i] = jsonChoices.getString("advise");
                wordingChoice[i] = jsonChoices.getString("wording");
                correct[i] = jsonChoices.getBoolean("correct");
                if (jsonChoices.isNull("resourceType")) {
                    resourceType[i] = null;
                } else {
                    resourceType[i] = jsonChoices.getJSONObject("resourceType").getString("mime");
                }
            }
            Test test = new Test(generalWording, idChoice, advise, wordingChoice, correct, resourceType);

            return test;


    }
    public Exercise getExercise(int id)throws IOException, JSONException{
        JSONObject json = restclient.getJson(String.format("getExercise?id=%d",id));
        Exercise exercise = new Exercise(json.getInt("id"),json.getString("wording"));
        return exercise;
    }

    public Status getStatus(String dni,String pss) throws IOException, JSONException{
        JSONObject json = restclient.getJson(String.format("getStatus?dni=%s",dni));
        Status user = new Status(json.getInt("id"),json.getString("user"),json.getInt("lessonNumber"),
                json.getString("lessonTitle"),json.getInt("nextTest"),json.getInt("nextExercise"),dni,pss);
        return user;
    }
}