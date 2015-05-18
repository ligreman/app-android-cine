package com.ligresoftware.queechanenelcine.utils;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.ligresoftware.queechanenelcine.R;
import com.ligresoftware.queechanenelcine.models.Cine;
import com.ligresoftware.queechanenelcine.models.Ciudad;
import com.ligresoftware.queechanenelcine.models.helpers.CineList;
import com.ligresoftware.queechanenelcine.models.helpers.CineUnit;

public class CineUtils {
    private static CineUtilsCallback mCallback;

    public boolean getCines(Ciudad ciudad) {
        AsyncTask<Void, Integer, CineList> getCinesTask =
                new AsyncTask<Void, Integer, CineList>() {
                    @Override
                    protected CineList doInBackground(Void... unused) {
                        try {
                            System.out.println("Las pido");
                            //TODO poner el id de la ciudad
                            String json = HttpUtils.readUrl("http://demo0064908.mockable.io/api/cine/ciudades/075120b9d524ae8a681fb8fc02ee00d7");

                            Gson gson = new Gson();
                            CineList cinesGson = gson.fromJson(json, CineList.class);

                            return cinesGson;
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
                    protected void onPostExecute(CineList listaCines) {
                        if (mCallback != null) {
                            mCallback.onGetCinesFinished(listaCines);
                        }
                    }
                };

        getCinesTask.execute();
        return true;
    }

    public boolean getCineDetail(String idCine) {
        AsyncTask<Void, Integer, Cine> getCineTask =
                new AsyncTask<Void, Integer, Cine>() {
                    @Override
                    protected Cine doInBackground(Void... unused) {
                        try {
                            System.out.println("Las pido");
                            //TODO poner el id del cine
                            String json = HttpUtils.readUrl("https://demo0064908.mockable.io/api/cine/cines/4a5a5de58b2b50e7ff48dbb669f6be03");

                            Gson gson = new Gson();
                            CineUnit cineGson = gson.fromJson(json, CineUnit.class);
                            Logger.d("", "");
                            return cineGson.getCine();
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
                    protected void onPostExecute(Cine cinecito) {
                        if (mCallback != null) {
                            mCallback.onGetCineDetailFinished(cinecito);
                        }
                    }
                };

        getCineTask.execute();
        return true;
    }

    public void setmCallback(CineUtilsCallback callback) {
        mCallback = callback;
    }

    public interface CineUtilsCallback {
        void onGetCinesFinished(CineList listaCines);

        void onGetCineDetailFinished(Cine cine);
    }

    //Métodos estáticos
    public static int getCineFavouritedResource(boolean status) {
        if (status) {
            return R.drawable.ic_favorite_black_36dp;
        } else {
            return R.drawable.ic_favorite_outline_black_36dp;
        }
    }
}
