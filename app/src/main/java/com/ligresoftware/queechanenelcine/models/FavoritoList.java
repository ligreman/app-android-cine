package com.ligresoftware.queechanenelcine.models;

import java.util.ArrayList;

public class FavoritoList {
    private ArrayList<Favorito> listaFavoritos;

    public FavoritoList() {
        listaFavoritos = new ArrayList<>();
    }

    public void addFavorito(Favorito nuevo) {
        listaFavoritos.add(nuevo);
    }

    public void addFavorito(String idCiudad, String nombreCiudad, String idcine, String nombreCine) {
        Favorito favorito = new Favorito(idCiudad, nombreCiudad, idcine, nombreCine);
        listaFavoritos.add(favorito);
    }

    public void removeFavorito(String idCine) {
        ArrayList<Favorito> nuevaLista = new ArrayList<>();

        for (Favorito fav : listaFavoritos) {
            //Si no es el que quiero quitar lo dejo en la lista nueva
            if (!fav.getIdCine().equals(idCine)) {
                nuevaLista.add(fav);
            }
        }

        //Guardo la lista nueva
        listaFavoritos = nuevaLista;
    }

    public ArrayList<Favorito> getListaFavoritos() {
        return listaFavoritos;
    }

    public void setListaFavoritos(ArrayList<Favorito> listaFavoritos) {
        this.listaFavoritos = listaFavoritos;
    }
}
