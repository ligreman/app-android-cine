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
import com.ligresoftware.queechanenelcine.adapters.CinesAdapter;
import com.ligresoftware.queechanenelcine.models.Cine;
import com.ligresoftware.queechanenelcine.models.Ciudad;
import com.ligresoftware.queechanenelcine.models.helpers.CineList;
import com.ligresoftware.queechanenelcine.utils.CineUtils;

import java.util.List;


public class AddFavouriteCinesFragment extends ListFragment {
    private List cines;
    private OnAddFavouriteCinesFragmentInteractionListener mListener;

    public AddFavouriteCinesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Cojo la ciudad que me han pasado
        Ciudad ciudadSelected = getArguments().getParcelable(Constants.PARCELABLE_CIUDAD);

        CineUtils cUtils = new CineUtils();
        cUtils.setmCallback(new CineUtils.CineUtilsCallback() {

            @Override
            public void onGetCinesFinished(CineList listaCines) {
                System.out.println("  TOTAL  " + listaCines.getCines().size());

                cines = listaCines.getCines();

//                Provincia dd = (Provincia) provincias.get(0);
//                Logger.d("PROVINCIA", dd.getNombre() + " " + dd.get_id());

                //La pongo en la lista
                setListAdapter(new CinesAdapter(getActivity(), cines));
            }
        });

        //Lanzo la obtención del listado de provincias
        cUtils.getCines(ciudadSelected);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_favourite_cines, container, false);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnAddFavouriteCinesFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnAddFavouriteCinesFragmentInteractionListener");
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
            mListener.onCinesFragmentInteractionSelectCine((Cine) this.cines.get(position));
        }
    }

    //Para comunicarse con la actividad
    public interface OnAddFavouriteCinesFragmentInteractionListener {
        public void onCinesFragmentInteractionSelectCine(Cine cineSelected);

        public void onCinesFragmentInteractionFinish();
    }
}
