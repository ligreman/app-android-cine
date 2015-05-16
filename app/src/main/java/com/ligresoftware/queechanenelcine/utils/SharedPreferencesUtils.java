package com.ligresoftware.queechanenelcine.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.ligresoftware.queechanenelcine.Constants;
import com.ligresoftware.queechanenelcine.models.FavoritoList;

public class SharedPreferencesUtils {
    public static FavoritoList getListaFavoritos(Context context) {
        if (context == null) {
            //Devuelvo que se produjo un error o algo
            return null;
        }
        SharedPreferences favoritos = context.getSharedPreferences(Constants.SHARED_FILE_FAVORITOS, Context.MODE_PRIVATE);
        Gson gson = new Gson();

        //Obtengo la lista de preferences
        String listaString = favoritos.getString(Constants.SHARED_LISTA_FAVORITOS, null);
        if (listaString == null) {
            //Si es null devuelvo una lista nueva
            return new FavoritoList();
        } else {
            return gson.fromJson(listaString, FavoritoList.class);
        }
    }

    public static void setListaFavoritos(Context context, FavoritoList lista) {
        SharedPreferences favoritos = context.getSharedPreferences(Constants.SHARED_FILE_FAVORITOS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = favoritos.edit();
        Gson gson = new Gson();

        String newLista = gson.toJson(lista);

        editor.putString(Constants.SHARED_LISTA_FAVORITOS, newLista);
        editor.commit();
    }
}
