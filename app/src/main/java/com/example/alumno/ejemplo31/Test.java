package com.example.alumno.ejemplo31;

import org.json.JSONException;

/**
 * Created by leire on 5/01/16.
 */

public class Test {

    private String wording;
    private Choice[] choices;

    public Test(String WordingGeneral, int [] id,String [] choicesWording, String [] advise,
                boolean [] correct, String [] resourceType) {
        wording = WordingGeneral;
        if(choicesWording.length == correct.length){
            choices = new Choice[correct.length];
            int i = 0;
            for(String choice : choicesWording){
                choices[i] = new Choice(id[i],choicesWording[i],correct[i],advise[i],resourceType[i]);
                i++;
            }
        }

    }

    public class Choice {
        private int id;
        private String wording;
        private boolean correct;
        private String advise;
        private String resourceType;

        public Choice(int id, String wording, boolean correct, String advise, String resourceType) {
            this.id = id;
            this.wording = wording;
            this.correct = correct;
            this.advise = advise;
            this.resourceType = resourceType;


        }


        public int getId() {

            return id;
        }

        public void setId(int id) {

            this.id = id;
        }

        public String getWording() {

            return wording;
        }

        public void setWording(String wording) {

            this.wording = wording;
        }

        public boolean isCorrect() {

            return correct;
        }

        public void setCorrect(boolean correct) {

            this.correct = correct;
        }

        public String getAdvise() {
            return advise;
        }

        public void setAdvise(String advise) {
            this.advise = advise;
        }

        public String getResourceType() {
            return resourceType;
        }

        public void setResourceType(String resourceType) {
            this.resourceType = resourceType;
        }
    }
}