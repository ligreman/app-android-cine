package com.ligresoftware.queechanenelcine.models;

import java.util.ArrayList;

public class Provincia {
    private String id;
    private String provinciaId;
    private String nombre;
    private ArrayList<Ciudad> ciudades;
    private Long actualizado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(String provinciaId) {
        this.provinciaId = provinciaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(ArrayList<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public Long getActualizado() {
        return actualizado;
    }

    public void setActualizado(Long actualizado) {
        this.actualizado = actualizado;
    }
}