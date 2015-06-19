package com.ligresoftware.queechanenelcine.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.ligresoftware.queechanenelcine.AddFavouriteActivity;
import com.ligresoftware.queechanenelcine.CineDetailActivity;
import com.ligresoftware.queechanenelcine.Constants;
import com.ligresoftware.queechanenelcine.R;
import com.ligresoftware.queechanenelcine.adapters.FavoritosAdapter;
import com.ligresoftware.queechanenelcine.adapters.FavoritosAdapterHolder;
import com.ligresoftware.queechanenelcine.utils.Logger;
import com.ligresoftware.queechanenelcine.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavouriteFragment extends Fragment {
    private FavoritosAdapter adaptador;
    private OnFavouriteFragmentInteractionListener mListener;
    private TextView mEmpty;

    public FavouriteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("FAV FRAGMENT", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d("FAV FRAGMENT", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        //Listener de botones
        FloatingActionButton addFavButton = (FloatingActionButton) view.findViewById(R.id.btn_add_favourite);
        addFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicio la actividad de añadir favoritos
                Intent i = new Intent(getActivity(), AddFavouriteActivity.class);
                startActivityForResult(i, Constants.REQUEST_CODE_ADD_FAVOURITES);
            }
        });

        mEmpty = (TextView) view.findViewById(R.id.favoritosEmpty);

        //Listener de eventos en la lista pined
        ListView mFavoritosListView = (ListView) view.findViewById(R.id.favoritosListView);
        mFavoritosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long type) {
                if (type == FavoritosAdapter.TYPE_ITEM) {
                    Logger.d("Lista Favoritos", "He pinchao en item " + type + " posicion " + position);
                    FavoritosAdapterHolder selected = (FavoritosAdapterHolder) adaptador.getItem(position);

                    Logger.d("SELECCIONA", "Cine " + selected.getTitulo() + " / " + selected.getId());
                    //Llamo a la actividad de CineDetail pasándole los datos del Cine (el id y el nombre)
                    Intent intent = new Intent(getActivity(), CineDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_CINE_ID, selected.getId());
                    intent.putExtra(Constants.EXTRA_CINE_NAME, selected.getTitulo());
                    startActivity(intent);
                }
            }
        });

        //Pongo el adaptador a la lista
        adaptador = new FavoritosAdapter(this.getActivity());
        mFavoritosListView.setAdapter(adaptador);
        populateAdapter();

        return view;
    }

    private void populateAdapter() {
        ArrayList<FavoritosAdapterHolder> listDataHolder = new ArrayList<>();

        HashMap matrix = SharedPreferencesUtils.getMatrixCiudadesCines(this.getActivity());
        FavoritosAdapterHolder fah;

        Logger.d("POPULO", "Tengo estos elementos " + matrix.size());

        //Recorro la estructura creando los elementos de mi lista
        if (matrix != null && matrix.size() > 0) {
            for (Object obj : matrix.entrySet()) {
                Map.Entry item = (Map.Entry) obj;
                Pair<String, String> key = (Pair<String, String>) item.getKey();
                List<Pair<String, String>> cines = (List<Pair<String, String>>) item.getValue();

                //Creo la cabecera (Pair<idCiudad, nombreCiudad>)
                fah = new FavoritosAdapterHolder(key.second, key.first, FavoritosAdapter.TYPE_HEADER);
                Logger.d("HEADER -", "" + key.second);
                listDataHolder.add(fah);

                //Creo los elementos de esta ciudad
                for (Pair<String, String> cine : cines) {
                    fah = new FavoritosAdapterHolder(cine.second, cine.first, FavoritosAdapter.TYPE_ITEM);
                    listDataHolder.add(fah);
                }
            }
            mEmpty.setVisibility(View.INVISIBLE);
        } else {
            mEmpty.setVisibility(View.VISIBLE);
        }

        adaptador.updateAdapter(listDataHolder);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFavouriteFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFavouriteFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFavouriteFragmentInteractionListener {
        public void onFragmentInteraction();
    }

    // Espero la respuesta de la Actividad de añadir favoritos
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Al volver recargo los favoritos
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.REQUEST_CODE_ADD_FAVOURITES) {
            populateAdapter();
        }
    }
}
