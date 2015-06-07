package com.ligresoftware.queechanenelcine.models.helpers;

import com.ligresoftware.queechanenelcine.models.Pelicula;

import java.util.ArrayList;

public class PeliculaList {
    private ArrayList<Pelicula> peliculas;
    private String error;

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
