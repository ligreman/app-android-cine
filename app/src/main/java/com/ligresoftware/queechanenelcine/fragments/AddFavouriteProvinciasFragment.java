package com.ligresoftware.queechanenelcine.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ligresoftware.queechanenelcine.R;
import com.ligresoftware.queechanenelcine.adapters.ProvinciasAdapter;
import com.ligresoftware.queechanenelcine.models.Provincia;
import com.ligresoftware.queechanenelcine.models.helpers.ProvinciaList;
import com.ligresoftware.queechanenelcine.utils.Logger;
import com.ligresoftware.queechanenelcine.utils.ProvinciaUtils;

import java.util.List;


public class AddFavouriteProvinciasFragment extends ListFragment {
    private List provincias;
    private OnAddFavouriteProvinciasFragmentInteractionListener mListener;

    public AddFavouriteProvinciasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProvinciaUtils pUtils = new ProvinciaUtils();
        pUtils.setmCallback(new ProvinciaUtils.ProvinciaUtilsCallback() {

            @Override
            public void onGetProvinciasFinished(ProvinciaList listaProvincias) {
                Logger.d("PROV", "  TOTAL  " + listaProvincias.getProvincias().size());

                provincias = listaProvincias.getProvincias();
                setListAdapter(new ProvinciasAdapter(getActivity(), provincias));
            }
        });
        //Lanzo la obtención del listado de provincias
        pUtils.getProvincias();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_favourite_provincias, container, false);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        Logger.d("FAV PROV", "Attach");
        super.onAttach(activity);
        try {
            mListener = (OnAddFavouriteProvinciasFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnAddFavouriteProvinciasFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (mListener != null) {
            mListener.onProvinciasFragmentInteraction((Provincia) this.provincias.get(position));
        }
    }

    //Para comunicarse con la actividad
    public interface OnAddFavouriteProvinciasFragmentInteractionListener {
        public void onProvinciasFragmentInteraction(Provincia provinciaSelected);
    }
}
