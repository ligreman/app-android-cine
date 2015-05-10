package com.ligresoftware.queechanenelcine.models.helpers;

import com.ligresoftware.queechanenelcine.models.Cine;

public class CineUnit {
    private Cine cine;
    private String error;

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
