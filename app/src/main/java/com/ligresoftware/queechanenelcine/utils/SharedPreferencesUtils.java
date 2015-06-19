package com.ligresoftware.queechanenelcine.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;

import com.google.gson.Gson;
import com.ligresoftware.queechanenelcine.Constants;
import com.ligresoftware.queechanenelcine.models.Favorito;
import com.ligresoftware.queechanenelcine.models.FavoritoList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SharedPreferencesUtils {
    public static Boolean getPhotoAccess(Context context) {
        if (context == null) {
            return null;
        }

        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_FILE_FAVORITOS, Context.MODE_PRIVATE);
        Boolean acceso = prefs.getBoolean(Constants.SHARED_PHOTOS_FAVORITOS, false);
        return acceso;
    }

    public static void setPhotoAccess(Context context, Boolean acceso) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_FILE_FAVORITOS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(Constants.SHARED_PHOTOS_FAVORITOS, acceso);
        editor.commit();
    }

    public static String getKey(Context context) {
        if (context == null) {
            return null;
        }

        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_FILE_FAVORITOS, Context.MODE_PRIVATE);
        return prefs.getString(Constants.SHARED_KEY_FAVORITOS, null);
    }

    public static void setKey(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_FILE_FAVORITOS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Constants.SHARED_KEY_FAVORITOS, key);
        editor.commit();
    }


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

    public static HashMap getMatrixCiudadesCines(Context context) {
        //Cojo los favoritos del Shared
        FavoritoList mFavoritoListHelper = getListaFavoritos(context);

        if (mFavoritoListHelper == null) {
            return null;
        }

        //Creo una estructura que asocie ciudad con cines
        HashMap<Pair<String, String>, List<Pair<String, String>>> matrix = new HashMap<>();

        //Recorro los favoritos guardados para poblar la estructura
        for (Favorito fav : mFavoritoListHelper.getListaFavoritos()) {
            //Saco los pares
            Pair<String, String> ciudad = new Pair<>(fav.getIdCiudad(), fav.getNombreCiudad());
            Pair<String, String> cine = new Pair<>(fav.getIdCine(), fav.getNombreCine());

            //Añado a mi matrix, pero primero miro a ver si ya existe
            if (matrix.containsKey(ciudad)) {
                //Añado a la lista
                List listaCines = matrix.get(ciudad);
                listaCines.add(cine);
                matrix.put(ciudad, listaCines);
            } else {
                //Creo una lista para añadir
                List<Pair<String, String>> listaCines = new ArrayList<>();
                listaCines.add(cine);
                matrix.put(ciudad, listaCines);
            }
        }

        return matrix;
    }
}
