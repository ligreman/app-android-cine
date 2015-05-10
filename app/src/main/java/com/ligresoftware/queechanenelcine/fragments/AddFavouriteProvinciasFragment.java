package com.ligresoftware.queechanenelcine.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ligresoftware.queechanenelcine.R;
import com.ligresoftware.queechanenelcine.models.helpers.ProvinciaList;
import com.ligresoftware.queechanenelcine.utils.ProvinciaUtils;


/**
 * A placeholder fragment containing a simple view.
 */
public class AddFavouriteProvinciasFragment extends Fragment {

    public AddFavouriteProvinciasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_favourite_provincias, container, false);

        System.out.println("Cojo las provincias");
        ProvinciaUtils pUtils = new ProvinciaUtils();
        pUtils.setmCallback(new ProvinciaUtils.ProvinciaUtilsCallback() {

            @Override
            public void onGetProvinciasFinished(ProvinciaList listaProvincias) {
                System.out.println("  TOTAL  " + listaProvincias.getProvincias().size());
            }
        });
        pUtils.getProvincias();

        return view;
    }

}
