package com.ligresoftware.queechanenelcine.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Ciudad implements Parcelable {
    private String _id;
    private String nombre;

    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
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
        dest.writeString(this._id);
        dest.writeString(this.nombre);
    }

    public Ciudad() {
    }

    private Ciudad(Parcel in) {
        this._id = in.readString();
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
