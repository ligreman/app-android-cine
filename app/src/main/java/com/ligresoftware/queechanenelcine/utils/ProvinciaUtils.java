package com.ligresoftware.queechanenelcine.utils;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.ligresoftware.queechanenelcine.Constants;
import com.ligresoftware.queechanenelcine.models.helpers.ProvinciaList;

public class ProvinciaUtils {
    private static ProvinciaUtilsCallback mCallback;

    public boolean getProvincias() {
        AsyncTask<Void, Integer, ProvinciaList> getProvinciasTask =
                new AsyncTask<Void, Integer, ProvinciaList>() {
                    @Override
                    protected ProvinciaList doInBackground(Void... unused) {
                        try {
//                            String json = HttpUtils.readUrl("http://localhost/api/cine/ciudades");
                            String json = HttpUtils.readUrl(Constants.WEBSERVICE_URL + "/api/cine/ciudades");
                            Gson gson = new Gson();
                            ProvinciaList provinciasGson = gson.fromJson(json, ProvinciaList.class);

//                            for (Provincia provincia : provinciasGson.getProvincias()) {
//                                System.out.println("  -  " + provincia.getNombre());
//                            }

                            return provinciasGson;
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
                    protected void onPostExecute(ProvinciaList listaProvincias) {
                        if (mCallback != null) {
                            mCallback.onGetProvinciasFinished(listaProvincias);
                        }
                    }
                };

        getProvinciasTask.execute();
        return true;
    }

    public void setmCallback(ProvinciaUtilsCallback callback) {
        mCallback = callback;
    }

    public interface ProvinciaUtilsCallback {
        void onGetProvinciasFinished(ProvinciaList listaProvincias);
    }
}
