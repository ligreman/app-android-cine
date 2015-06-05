package com.ligresoftware.queechanenelcine;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.ligresoftware.queechanenelcine.drawer.NavigationDrawerCallbacks;
import com.ligresoftware.queechanenelcine.drawer.NavigationDrawerFragment;
import com.ligresoftware.queechanenelcine.fragments.FavouriteFragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks, FavouriteFragment.OnFavouriteFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private FavouriteFragment mFavouriteFragment = new FavouriteFragment();
    private Fragment mMoviesFragment = new Fragment();
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
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

}
