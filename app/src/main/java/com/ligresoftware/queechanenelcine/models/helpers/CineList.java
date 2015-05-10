package com.ligresoftware.queechanenelcine.models.helpers;

import com.ligresoftware.queechanenelcine.models.Cine;

import java.util.ArrayList;

public class CineList {
    private ArrayList<Cine> cines;
    private String error;

    public ArrayList<Cine> getCines() {
        return cines;
    }

    public void setCines(ArrayList<Cine> cines) {
        this.cines = cines;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
