package com.ligresoftware.queechanenelcine.utils;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.ligresoftware.queechanenelcine.Constants;
import com.ligresoftware.queechanenelcine.models.helpers.PeliculaList;
import com.ligresoftware.queechanenelcine.models.helpers.PeliculaUnit;

public class PeliculaUtils {
    private static PeliculaUtilsCallback mCallback;

    public boolean getPeliculas() {
        AsyncTask<Void, Integer, PeliculaList> getPeliculasTask =
                new AsyncTask<Void, Integer, PeliculaList>() {
                    @Override
                    protected PeliculaList doInBackground(Void... unused) {
                        try {
                            String json = HttpUtils.readUrl(Constants.WEBSERVICE_URL + "/api/cine/peliculas");

                            Gson gson = new Gson();
                            PeliculaList pelisGson = gson.fromJson(json, PeliculaList.class);

                            return pelisGson;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }

                    @Override
                    protected void onCancelled() {
                        super.onCancelled();
                    }

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                    }

                    @Override
                    protected void onPostExecute(PeliculaList listaPelis) {
                        if (mCallback != null) {
                            mCallback.onGetPeliculasFinished(listaPelis);
                        }
                    }
                };

        getPeliculasTask.execute();
        return true;
    }

    public boolean getPelicula(final String peliculaId) {
        AsyncTask<Void, Integer, PeliculaUnit> getPeliculaTask =
                new AsyncTask<Void, Integer, PeliculaUnit>() {
                    @Override
                    protected PeliculaUnit doInBackground(Void... unused) {
                        try {
                            String json = HttpUtils.readUrl(Constants.WEBSERVICE_URL + "/api/cine/peliculas/" + peliculaId);

                            Gson gson = new Gson();
                            PeliculaUnit peliGson = gson.fromJson(json, PeliculaUnit.class);

                            return peliGson;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }

                    @Override
                    protected void onCancelled() {
                        super.onCancelled();
                    }

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                    }

                    @Override
                    protected void onPostExecute(PeliculaUnit peli) {
                        if (mCallback != null) {
                            mCallback.onGetPeliculaFinished(peli);
                        }
                    }
                };

        getPeliculaTask.execute();
        return true;
    }

    public void setmCallback(PeliculaUtilsCallback callback) {
        mCallback = callback;
    }

    public interface PeliculaUtilsCallback {
        void onGetPeliculasFinished(PeliculaList listaPeliculas);

        void onGetPeliculaFinished(PeliculaUnit peliculaUnit);
    }
}
