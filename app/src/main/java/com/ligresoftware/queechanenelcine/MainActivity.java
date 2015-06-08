package com.ligresoftware.queechanenelcine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ligresoftware.queechanenelcine.drawer.NavigationDrawerCallbacks;
import com.ligresoftware.queechanenelcine.drawer.NavigationDrawerFragment;
import com.ligresoftware.queechanenelcine.fragments.FavouriteFragment;
import com.ligresoftware.queechanenelcine.fragments.MoviesFragment;
import com.ligresoftware.queechanenelcine.models.Pelicula;
import com.ligresoftware.queechanenelcine.models.Sesion;
import com.ligresoftware.queechanenelcine.models.helpers.PeliculaList;
import com.ligresoftware.queechanenelcine.models.helpers.PeliculaUnit;
import com.ligresoftware.queechanenelcine.utils.Logger;
import com.ligresoftware.queechanenelcine.utils.PeliculaUtils;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks, FavouriteFragment.OnFavouriteFragmentInteractionListener,
        MoviesFragment.OnPeliculasFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private FavouriteFragment mFavouriteFragment = new FavouriteFragment();
    private MoviesFragment mMoviesFragment = new MoviesFragment();
    private Toolbar mToolbar;
    private Activity actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actividad = this;

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        //Coloco el primero
        if (savedInstanceState == null) {
            //Pongo el inicial
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, mFavouriteFragment, "Favoritos")
                    .commit();
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        //Según la position muestro uno u otro fragmento
        switch (position) {
            case 1:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, mMoviesFragment, "Peliculas")
                        .commit();
                break;
            case 0:
            default:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, mFavouriteFragment, "Favoritos")
                        .commit();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent e) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (!mNavigationDrawerFragment.isDrawerOpen()) {
                mNavigationDrawerFragment.openDrawer();
            } else {
                mNavigationDrawerFragment.closeDrawer();
            }
            return true;
        }
        return super.onKeyDown(keyCode, e);
    }

    @Override
    public void onBackPressed() {
        Logger.d("MAIN", "BACK pressed");
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            } else {
                Toast.makeText(getBaseContext(), getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show();
            }

            mBackPressed = System.currentTimeMillis();
        }
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


    @Override
    public void onFragmentInteraction() {

    }

    @Override
    public void onPeliculaSelectedFragmentInteraction(Pelicula peliculaSelected) {
        //Llamo al webservice para obtener la información
        PeliculaUtils pUtils = new PeliculaUtils();
        pUtils.setmCallback(new PeliculaUtils.PeliculaUtilsCallback() {
            //Cuando obtengo la lista de pelis
            @Override
            public void onGetPeliculasFinished(PeliculaList listaPeliculas) {
            }

            @Override
            public void onGetPeliculaFinished(PeliculaUnit peliculaUnit) {
                if (!peliculaUnit.getError().equals("")) {
                    return;
                }

                //Creo una sesión con los datos
                Pelicula p = peliculaUnit.getPelicula();

                Sesion sesion = new Sesion();
                sesion.set_idPelicula(p.get_id());
                sesion.setPeliculaId(p.getPeliculaId());
                sesion.setTitulo(p.getTitulo());
                sesion.setTituloOriginal(p.getTituloOriginal());
                sesion.setDirector(p.getDirector());
                sesion.setReparto(p.getReparto());
                sesion.setPais(p.getPais());
                sesion.setDuracion(p.getDuracion());
                sesion.setGenero(p.getGenero());
                sesion.setEstreno(p.getEstreno());
                sesion.setSinopsis(p.getSinopsis());
                sesion.setRepartoExtendido(p.getRepartoExtendido());
                sesion.setEstudio(p.getEstudio());
                sesion.setAnno(p.getAnno());
                sesion.setImagen(p.getImagen());

                //Cargo la actividad
                Intent intent = new Intent(actividad, PeliculaDetailActivity.class);
                intent.putExtra(Constants.EXTRA_SESION_SELECTED, sesion);
                startActivity(intent);
            }
        });
        //Lanzo la obtención del listado de peliculas
        pUtils.getPelicula(peliculaSelected.getPeliculaId());
    }
}
