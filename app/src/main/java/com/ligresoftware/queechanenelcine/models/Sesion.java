package com.ligresoftware.queechanenelcine.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Sesion implements Parcelable {
    private String _idPelicula;
    private String peliculaId;
    private ArrayList<String> horarios;
    private String titulo;
    private String tituloOriginal;
    private String estreno;
    private Integer anno;
    private Integer duracion;
    private ArrayList<String> pais;
    private ArrayList<String> genero;
    private ArrayList<String> estudio;
    private String sinopsis;
    private ArrayList<String> director;
    private ArrayList<String> reparto;
    private ArrayList<String> repartoExtendido;
    private String imagen;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
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

    public ArrayList<String> getEstudio() {
        return estudio;
    }

    public void setEstudio(ArrayList<String> estudio) {
        this.estudio = estudio;
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

    public ArrayList<String> getRepartoExtendido() {
        return repartoExtendido;
    }

    public void setRepartoExtendido(ArrayList<String> repartoExtendido) {
        this.repartoExtendido = repartoExtendido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._idPelicula);
        dest.writeString(this.peliculaId);
        dest.writeSerializable(this.horarios);
        dest.writeString(this.titulo);
        dest.writeString(this.tituloOriginal);
        dest.writeString(this.estreno);
        dest.writeValue(this.anno);
        dest.writeValue(this.duracion);
        dest.writeSerializable(this.pais);
        dest.writeSerializable(this.genero);
        dest.writeSerializable(this.estudio);
        dest.writeString(this.sinopsis);
        dest.writeSerializable(this.director);
        dest.writeSerializable(this.reparto);
        dest.writeSerializable(this.repartoExtendido);
        dest.writeString(this.imagen);
    }

    public Sesion() {
    }

    private Sesion(Parcel in) {
        this._idPelicula = in.readString();
        this.peliculaId = in.readString();
        this.horarios = (ArrayList<String>) in.readSerializable();
        this.titulo = in.readString();
        this.tituloOriginal = in.readString();
        this.estreno = in.readString();
        this.anno = (Integer) in.readValue(Integer.class.getClassLoader());
        this.duracion = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pais = (ArrayList<String>) in.readSerializable();
        this.genero = (ArrayList<String>) in.readSerializable();
        this.estudio = (ArrayList<String>) in.readSerializable();
        this.sinopsis = in.readString();
        this.director = (ArrayList<String>) in.readSerializable();
        this.reparto = (ArrayList<String>) in.readSerializable();
        this.repartoExtendido = (ArrayList<String>) in.readSerializable();
        this.imagen = in.readString();
    }

    public static final Parcelable.Creator<Sesion> CREATOR = new Parcelable.Creator<Sesion>() {
        public Sesion createFromParcel(Parcel source) {
            return new Sesion(source);
        }

        public Sesion[] newArray(int size) {
            return new Sesion[size];
        }
    };
}
