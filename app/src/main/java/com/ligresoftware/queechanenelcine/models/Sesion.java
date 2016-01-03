package com.ligresoftware.queechanenelcine.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Sesion implements Parcelable {
    private String _id;
    private Pelicula pelicula;
    private ArrayList<String> horarios;
    private ArrayList<String> horarios3D;

    /*private String _idPelicula;
    private ArrayList<String> horarios;
    private String titulo;
    private String estreno;
    private Integer anno;
    private Integer duracion;
    private ArrayList<String> pais;
    private ArrayList<String> genero;
    private String sinopsis;
    private ArrayList<String> director;
    private ArrayList<String> reparto;
    private String imagen;*/

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public ArrayList<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<String> horarios) {
        this.horarios = horarios;
    }

    public ArrayList<String> getHorarios3D() {
        return horarios3D;
    }

    public void setHorarios3D(ArrayList<String> horarios3D) {
        this.horarios3D = horarios3D;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeParcelable(this.pelicula, flags);
        dest.writeSerializable(this.horarios);
        dest.writeSerializable(this.horarios3D);
    }

    public Sesion() {
    }

    private Sesion(Parcel in) {
        this._id = in.readString();
        this.pelicula = in.readParcelable(Pelicula.class.getClassLoader());
        this.horarios = (ArrayList<String>) in.readSerializable();
        this.horarios3D = (ArrayList<String>) in.readSerializable();
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
