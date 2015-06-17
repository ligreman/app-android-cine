package com.ligresoftware.queechanenelcine.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ligresoftware.queechanenelcine.R;
import com.ligresoftware.queechanenelcine.adapters.PeliculasAdapter;
import com.ligresoftware.queechanenelcine.models.Pelicula;
import com.ligresoftware.queechanenelcine.models.helpers.PeliculaList;
import com.ligresoftware.queechanenelcine.models.helpers.PeliculaUnit;
import com.ligresoftware.queechanenelcine.utils.PeliculaUtils;

import java.util.List;


public class MoviesFragment extends ListFragment {
    private List peliculas;
    private OnPeliculasFragmentInteractionListener mListener;

    public MoviesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Pido la lista de películas
        PeliculaUtils pUtils = new PeliculaUtils();
        pUtils.setmCallback(new PeliculaUtils.PeliculaUtilsCallback() {
            //Cuando obtengo la lista de pelis
            @Override
            public void onGetPeliculasFinished(PeliculaList listaPeliculas) {
                peliculas = listaPeliculas.getPeliculas();
                setListAdapter(new PeliculasAdapter(getActivity(), peliculas));
            }

            @Override
            public void onGetPeliculaFinished(PeliculaUnit peliculaUnit) {
            }
        });
        //Lanzo la obtención del listado de peliculas
        pUtils.getPeliculas();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnPeliculasFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnPeliculasFragmentInteractionListener");
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
            mListener.onPeliculaSelectedFragmentInteraction((Pelicula) this.peliculas.get(position));
        }
    }

    //Para comunicarse con la actividad
    public interface OnPeliculasFragmentInteractionListener {
        public void onPeliculaSelectedFragmentInteraction(Pelicula peliculaSelected);
    }
}
