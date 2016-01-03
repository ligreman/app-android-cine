package com.ligresoftware.queechanenelcine.models;

import java.util.ArrayList;

public class Pelicula {
    private String _id;
    private String titulo;
    private String estreno;
    private Integer anno;
    private Integer duracion;
    private ArrayList<String> pais;
    private ArrayList<String> genero;
    private String sinopsis;
    private ArrayList<String> director;
    private ArrayList<String> reparto;
    private String imagen;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstreno() {
        return estreno;
    }

    public void setEstreno(String estreno) {
        this.estreno = estreno;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public ArrayList<String> getPais() {
        return pais;
    }

    public void setPais(ArrayList<String> pais) {
        this.pais = pais;
    }

    public ArrayList<String> getGenero() {
        return genero;
    }

    public void setGenero(ArrayList<String> genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public ArrayList<String> getDirector() {
        return director;
    }

    public void setDirector(ArrayList<String> director) {
        this.director = director;
    }

    public ArrayList<String> getReparto() {
        return reparto;
    }

    public void setReparto(ArrayList<String> reparto) {
        this.reparto = reparto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
