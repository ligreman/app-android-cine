package com.ligresoftware.queechanenelcine;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ligresoftware.queechanenelcine.fragments.AddFavouriteCinesFragment;
import com.ligresoftware.queechanenelcine.fragments.AddFavouriteCiudadesFragment;
import com.ligresoftware.queechanenelcine.fragments.AddFavouriteProvinciasFragment;
import com.ligresoftware.queechanenelcine.models.Cine;
import com.ligresoftware.queechanenelcine.models.Ciudad;
import com.ligresoftware.queechanenelcine.models.Provincia;
import com.ligresoftware.queechanenelcine.utils.Logger;


public class AddFavouriteActivity extends ActionBarActivity
        implements AddFavouriteProvinciasFragment.OnAddFavouriteProvinciasFragmentInteractionListener,
        AddFavouriteCiudadesFragment.OnAddFavouriteCiudadesFragmentInteractionListener,
        AddFavouriteCinesFragment.OnAddFavouriteCinesFragmentInteractionListener {

    private Toolbar mToolbar;
    private String currentFragment;
    private AddFavouriteProvinciasFragment mAddFavouriteProvinciasFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favourite);

        //Pongo la toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar_add_favourite);
        mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mToolbar.setTitle(getString(R.string.provincias));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (savedInstanceState == null) {
            //Fragmento inicial
            mAddFavouriteProvinciasFragment = new AddFavouriteProvinciasFragment();
            currentFragment = "AddFavoritosProvincias";
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_add_favourite, mAddFavouriteProvinciasFragment, "AddFavoritosProvincias")
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        //Si el fragmento es el de Provincias aviso de que he terminado a la actividad anterior
        switch (currentFragment) {
            case "AddFavoritosProvincias":
                setResult(RESULT_OK);
                this.finish();
                break;

            case "AddFavoritosCiudades":
                //Si vengo del fragmento de ciudades pongo el titulo de Provincias
                mToolbar.setTitle(getString(R.string.provincias));
                break;

            case "AddFavoritosCines":
                mToolbar.setTitle(getString(R.string.fav_cine));
                break;
        }

        super.onBackPressed();
    }

    //Recibo la provincia seleccionada cuando elijo una en el fragmento de Provincias
    @Override
    public void onProvinciasFragmentInteraction(Provincia provinciaSelected) {
        //Mostraré el fragmento de Ciudades pasándole la provincia seleccionada
        AddFavouriteCiudadesFragment ciudadesFragment = new AddFavouriteCiudadesFragment();

        //Le paso al fragmento la provincia
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.PARCELABLE_PROVINCIA, provinciaSelected);
        ciudadesFragment.setArguments(bundle);

        mToolbar.setTitle(getString(R.string.ciudades));
        currentFragment = "AddFavoritosCiudades";
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_add_favourite, ciudadesFragment, "AddFavoritosCiudades")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCiudadesFragmentInteraction(Ciudad ciudadSelected) {
        Logger.d("Activity AddFavourite", "Ciudad seleccionada: " + ciudadSelected.getNombre());
        //Mostraré el fragmento de Cines  pasándole la ciudad seleccionada
        AddFavouriteCinesFragment cinesFragment = new AddFavouriteCinesFragment();

        //Le paso al fragmento la ciudad
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.PARCELABLE_CIUDAD, ciudadSelected);
        cinesFragment.setArguments(bundle);

        mToolbar.setTitle(getString(R.string.fav_cine));
        currentFragment = "AddFavoritosCines";
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_add_favourite, cinesFragment, "AddFavoritosCines")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCinesFragmentInteractionSelectCine(Cine cineSelected) {
        //TODO guardo en preferences un favorito
    }

    @Override
    public void onCinesFragmentInteractionFinish() {
        //Termino de añadir favoritos, y simulo un Atras desde Provincias
        currentFragment = "AddFavoritosProvincias";
        onBackPressed();
    }
}
