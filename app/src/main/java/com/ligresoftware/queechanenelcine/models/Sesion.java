package com.ligresoftware.queechanenelcine.models;

import java.util.ArrayList;

public class Sesion {
    private String _idPelicula;
    private String peliculaId;
    private ArrayList<String> horarios;

    public String get_idPelicula() {
        return _idPelicula;
    }

    public void set_idPelicula(String _idPelicula) {
        this._idPelicula = _idPelicula;
    }

    public String getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(String peliculaId) {
        this.peliculaId = peliculaId;
    }

    public ArrayList<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<String> horarios) {
        this.horarios = horarios;
    }
}
