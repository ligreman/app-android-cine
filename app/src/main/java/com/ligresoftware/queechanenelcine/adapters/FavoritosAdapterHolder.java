package com.ligresoftware.queechanenelcine.adapters;

public class FavoritosAdapterHolder {
    private String titulo;
    private String id;
    private int type;

    public FavoritosAdapterHolder(String titulo, String id, int type) {
        this.titulo = titulo;
        this.id = id;
        this.type = type;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
