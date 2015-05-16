package com.ligresoftware.queechanenelcine.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Ciudad implements Parcelable {
    private String id;
    private String ciudadId;
    private String nombre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(String ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.ciudadId);
        dest.writeString(this.nombre);
    }

    public Ciudad() {
    }

    private Ciudad(Parcel in) {
        this.id = in.readString();
        this.ciudadId = in.readString();
        this.nombre = in.readString();
    }

    public static final Parcelable.Creator<Ciudad> CREATOR = new Parcelable.Creator<Ciudad>() {
        public Ciudad createFromParcel(Parcel source) {
            return new Ciudad(source);
        }

        public Ciudad[] newArray(int size) {
            return new Ciudad[size];
        }
    };
}
