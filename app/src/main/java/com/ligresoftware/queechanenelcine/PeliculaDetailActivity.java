package com.ligresoftware.queechanenelcine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ligresoftware.queechanenelcine.models.Sesion;
import com.ligresoftware.queechanenelcine.utils.MyUtils;


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
        ((TextView) findViewById(R.id.peliculaRowTitulo)).setText(sesion.getTitulo());
        ((TextView) findViewById(R.id.peliculaRowTituloOriginal)).setText(sesion.getTituloOriginal());
        ((TextView) findViewById(R.id.peliculaRowDirector)).setText(getString(R.string.director) + " " + MyUtils.implode(sesion.getDirector(), ", "));
        ((TextView) findViewById(R.id.peliculaRowReparto)).setText(MyUtils.implode(sesion.getReparto(), ", "));
        ((TextView) findViewById(R.id.peliculaRowDuracion)).setText(MyUtils.implode(sesion.getPais(), ", ") + ". " + getDuracion(sesion.getDuracion()));
        ((TextView) findViewById(R.id.peliculaRowGenero)).setText(MyUtils.implode(sesion.getGenero(), ", "));
        ((TextView) findViewById(R.id.peliculaRowEstreno)).setText(getString(R.string.estreno) + " " + sesion.getEstreno());
        ((TextView) findViewById(R.id.peliculaRowSinopsis)).setText(sesion.getSinopsis());
        ((TextView) findViewById(R.id.peliculaRowRepartoExtendido)).setText(MyUtils.implode(sesion.getRepartoExtendido(), "\n"));
        ((TextView) findViewById(R.id.peliculaRowEstudio)).setText(getString(R.string.estudio) + " " + MyUtils.implode(sesion.getEstudio(), ", "));
        ((TextView) findViewById(R.id.peliculaRowAnno)).setText(getString(R.string.anno) + " " + sesion.getAnno());

        String[] partes = sesion.getImagen().split(",");
        if (partes.length == 2) {
            byte[] decodedString = Base64.decode(partes[1].getBytes(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ((ImageView) findViewById(R.id.peliculaRowPortada)).setImageBitmap(bitmap);
        }
    }

    private String getDuracion(Integer duracion) {
        if (duracion != null) {
            return duracion.toString() + " " + getString(R.string.minutos);
        } else {
            return "";
        }
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
