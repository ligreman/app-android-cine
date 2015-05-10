package com.ligresoftware.queechanenelcine.models.helpers;

import com.ligresoftware.queechanenelcine.models.Provincia;

import java.util.ArrayList;

public class ProvinciaList {
    private ArrayList<Provincia> provincias;
    private String error;

    public ArrayList<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(ArrayList<Provincia> provincias) {
        this.provincias = provincias;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
