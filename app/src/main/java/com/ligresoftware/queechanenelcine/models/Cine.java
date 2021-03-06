package com.ligresoftware.queechanenelcine.models;

import java.util.ArrayList;

public class Cine {
    private String _id;
    private String nombre;
    private String _idCiudad;
    private String nombreCiudad;
    private String direccion;
    private String urlCompraOnline;
    private Integer codigoPostal;
    private String telefono;
    private Float coordLatitud;
    private Float coordLongitud;
    private ArrayList<Sesion> sesiones;
    private Long actualizado;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_idCiudad() {
        return _idCiudad;
    }

    public void set_idCiudad(String _idCiudad) {
        this._idCiudad = _idCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Float getCoordLatitud() {
        return coordLatitud;
    }

    public void setCoordLatitud(Float coordLatitud) {
        this.coordLatitud = coordLatitud;
    }

    public Float getCoordLongitud() {
        return coordLongitud;
    }

    public void setCoordLongitud(Float coordLongitud) {
        this.coordLongitud = coordLongitud;
    }

    public ArrayList<Sesion> getSesiones() {
        return sesiones;
    }

    public void setSesiones(ArrayList<Sesion> sesiones) {
        this.sesiones = sesiones;
    }

    public Long getActualizado() {
        return actualizado;
    }

    public void setActualizado(Long actualizado) {
        this.actualizado = actualizado;
    }

    public String getUrlCompraOnline() {
        return urlCompraOnline;
    }

    public void setUrlCompraOnline(String urlCompraOnline) {
        this.urlCompraOnline = urlCompraOnline;
    }
}
