package com.ligresoftware.queechanenelcine.models;

public class Favorito {
    private String idCiudad;
    private String nombreCiudad;
    private String idCine;
    private String nombreCine;

    public Favorito() {
    }

    public Favorito(String idCiudad, String nombreCiudad, String idCine, String nombreCine) {
        this.idCiudad = idCiudad;
        this.nombreCiudad = nombreCiudad;
        this.idCine = idCine;
        this.nombreCine = nombreCine;
    }

    public String getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getIdCine() {
        return idCine;
    }

    public void setIdCine(String idCine) {
        this.idCine = idCine;
    }

    public String getNombreCine() {
        return nombreCine;
    }

    public void setNombreCine(String nombreCine) {
        this.nombreCine = nombreCine;
    }
}
