package com.ligresoftware.queechanenelcine.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Provincia implements Parcelable {
    private String _id;
    private String nombre;
    private ArrayList<Ciudad> ciudades;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.nombre);
        dest.writeSerializable(this.ciudades);
    }

    public Provincia() {
    }

    private Provincia(Parcel in) {
        this._id = in.readString();
        this.nombre = in.readString();
        this.ciudades = (ArrayList<Ciudad>) in.readSerializable();
    }

    public static final Parcelable.Creator<Provincia> CREATOR = new Parcelable.Creator<Provincia>() {
        public Provincia createFromParcel(Parcel source) {
            return new Provincia(source);
        }

        public Provincia[] newArray(int size) {
            return new Provincia[size];
        }
    };
}