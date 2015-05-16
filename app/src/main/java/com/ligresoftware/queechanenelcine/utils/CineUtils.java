package com.ligresoftware.queechanenelcine.utils;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.ligresoftware.queechanenelcine.models.Ciudad;
import com.ligresoftware.queechanenelcine.models.helpers.CineList;

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

    public void setmCallback(CineUtilsCallback callback) {
        mCallback = callback;
    }

    public interface CineUtilsCallback {
        void onGetCinesFinished(CineList listaCines);
    }
}
