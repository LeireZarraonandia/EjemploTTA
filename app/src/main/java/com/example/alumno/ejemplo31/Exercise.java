package com.example.alumno.ejemplo31;

import java.io.Serializable;

/**
 * Created by leire on 6/01/16.
 */
public class Exercise implements Serializable {

    private int id;
    private String wording;

    public Exercise(int id, String wording){
        this.id=id;
        this.wording=wording;
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


}