package com.ligresoftware.queechanenelcine.models.helpers;

import com.ligresoftware.queechanenelcine.models.Pelicula;

public class PeliculaUnit {
    private Pelicula pelicula;
    private String error;

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
