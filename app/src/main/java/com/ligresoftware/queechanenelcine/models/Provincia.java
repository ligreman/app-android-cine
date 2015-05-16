package com.ligresoftware.queechanenelcine.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Provincia implements Parcelable {
    private String _id;
    private String provinciaId;
    private String nombre;
    private ArrayList<Ciudad> ciudades;
    private Long actualizado;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.provinciaId);
        dest.writeString(this.nombre);
        dest.writeSerializable(this.ciudades);
        dest.writeValue(this.actualizado);
    }

    public Provincia() {
    }

    private Provincia(Parcel in) {
        this._id = in.readString();
        this.provinciaId = in.readString();
        this.nombre = in.readString();
        this.ciudades = (ArrayList<Ciudad>) in.readSerializable();
        this.actualizado = (Long) in.readValue(Long.class.getClassLoader());
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