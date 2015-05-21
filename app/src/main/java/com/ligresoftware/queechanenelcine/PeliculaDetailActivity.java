package com.ligresoftware.queechanenelcine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ligresoftware.queechanenelcine.models.Sesion;


public class PeliculaDetailActivity extends ActionBarActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula_detail);

        //Cojo los datos de la película que me han pasado
        Intent intent = getIntent();
        Sesion sesion = intent.getParcelableExtra(Constants.EXTRA_SESION_SELECTED);

        //Pongo la toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar_cine_detail);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);
        mToolbar.setTitle(sesion.getTitulo());
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        populateView(sesion);
    }

    private void populateView(Sesion sesion) {
        TextView tv = (TextView) findViewById(R.id.ideito);
        tv.setText(sesion.getSinopsis());

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pelicula_detail, menu);
        return true;
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
}
