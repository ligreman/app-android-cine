package com.ligresoftware.queechanenelcine;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ligresoftware.queechanenelcine.fragments.AddFavouriteCinesFragment;
import com.ligresoftware.queechanenelcine.fragments.AddFavouriteCiudadesFragment;
import com.ligresoftware.queechanenelcine.fragments.AddFavouriteProvinciasFragment;
import com.ligresoftware.queechanenelcine.models.Cine;
import com.ligresoftware.queechanenelcine.models.Ciudad;
import com.ligresoftware.queechanenelcine.models.FavoritoList;
import com.ligresoftware.queechanenelcine.models.Provincia;
import com.ligresoftware.queechanenelcine.utils.CineUtils;
import com.ligresoftware.queechanenelcine.utils.Logger;
import com.ligresoftware.queechanenelcine.utils.SharedPreferencesUtils;


public class AddFavouriteActivity extends ActionBarActivity
        implements AddFavouriteProvinciasFragment.OnAddFavouriteProvinciasFragmentInteractionListener,
        AddFavouriteCiudadesFragment.OnAddFavouriteCiudadesFragmentInteractionListener,
        AddFavouriteCinesFragment.OnAddFavouriteCinesFragmentInteractionListener {

    private Toolbar mToolbar;
    private String mCurrentFragment;
    private AddFavouriteProvinciasFragment mAddFavouriteProvinciasFragment;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favourite);

        mContext = this;

        //Pongo la toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar_add_favourite);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);
        mToolbar.setTitle(getString(R.string.provincias));
        mToolbar.inflateMenu(R.menu.menu_add_favourite);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                //A ver si he pinchao el elemento que espero
                if (id == R.id.action_finish) {
                    Logger.d("Pincho", "A terminar");
                    return true;
                }
                return false;
            }
        });

        if (savedInstanceState == null) {
            //Fragmento inicial
            mAddFavouriteProvinciasFragment = new AddFavouriteProvinciasFragment();
            mCurrentFragment = "AddFavoritosProvincias";
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_add_favourite, mAddFavouriteProvinciasFragment, "AddFavoritosProvincias")
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        //Si el fragmento es el de Provincias aviso de que he terminado a la actividad anterior
        switch (mCurrentFragment) {
            case "AddFavoritosProvincias":
                setResult(RESULT_OK);
                this.finish();
                break;

            case "AddFavoritosCiudades":
                //Si vengo del fragmento de ciudades pongo el titulo de Provincias
                mToolbar.setTitle(getString(R.string.provincias));
                mCurrentFragment = "AddFavoritosProvincias";
                break;

            case "AddFavoritosCines":
                mToolbar.setTitle(getString(R.string.ciudades));
                mCurrentFragment = "AddFavoritosCiudades";
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
        mCurrentFragment = "AddFavoritosCiudades";
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
        mCurrentFragment = "AddFavoritosCines";
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_add_favourite, cinesFragment, "AddFavoritosCines")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCinesFragmentInteractionSelectCine(Cine cineSelected, ImageView imageView) {
        FavoritoList lista = SharedPreferencesUtils.getListaFavoritos(mContext);

        //Si la lista es null es que pasó algo
        if (lista == null) {
            Logger.d("FAGMENT INTERACTION CINE", "Es null");
            Toast.makeText(mContext, getString(R.string.favourite_error_saving), Toast.LENGTH_LONG).show();
            return;
        }

        //Cojo el estado del icono y lo invierto (he hecho click para cambiarlo)
        boolean esFavorito = (boolean) imageView.getTag(R.string.tag_favorito);
        esFavorito = !esFavorito;

        //Según sea el nuevo estado quiero añadir o quitar de favoritos
        if (esFavorito) {
            //Quiero añadirlo
            lista.addFavorito(cineSelected.get_idCiudad(), cineSelected.getNombreCiudad(), cineSelected.get_id(), cineSelected.getNombre());
        } else {
            //Quiero quitarlo
            lista.removeFavorito(cineSelected.get_id());
        }

        //Cambio el icono y su estado
        imageView.setImageResource(CineUtils.getCineFavouritedResource(esFavorito));
        imageView.setTag(R.string.tag_favorito, esFavorito);

        //Guardo los nuevos favoritos
        SharedPreferencesUtils.setListaFavoritos(mContext, lista);
    }

    @Override
    public void onCinesFragmentInteractionFinish() {
        //Termino de añadir favoritos, y simulo un Atras desde Provincias
        mCurrentFragment = "AddFavoritosProvincias";
        onBackPressed();
    }
}
