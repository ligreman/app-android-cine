package com.ligresoftware.queechanenelcine.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ligresoftware.queechanenelcine.Constants;
import com.ligresoftware.queechanenelcine.R;
import com.ligresoftware.queechanenelcine.adapters.CiudadesAdapter;
import com.ligresoftware.queechanenelcine.models.Ciudad;
import com.ligresoftware.queechanenelcine.models.Provincia;

import java.util.List;


public class AddFavouriteCiudadesFragment extends ListFragment {
    private List ciudades;
    private OnAddFavouriteCiudadesFragmentInteractionListener mListener;

    public AddFavouriteCiudadesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Cojo la provincia que me han pasado
        Provincia provinciaSelected = getArguments().getParcelable(Constants.PARCELABLE_PROVINCIA);

        System.out.println("Ciudades de la provincia " + provinciaSelected.getCiudades().size());
        ciudades = provinciaSelected.getCiudades();

        //La pongo en la lista
        setListAdapter(new CiudadesAdapter(getActivity(), ciudades));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_favourite_ciudades, container, false);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnAddFavouriteCiudadesFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnAddFavouriteCiudadesFragmentInteractionListener");
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
            mListener.onCiudadesFragmentInteraction((Ciudad) this.ciudades.get(position));
        }
    }

    //Para comunicarse con la actividad
    public interface OnAddFavouriteCiudadesFragmentInteractionListener {
        public void onCiudadesFragmentInteraction(Ciudad ciudadSelected);
    }
}
