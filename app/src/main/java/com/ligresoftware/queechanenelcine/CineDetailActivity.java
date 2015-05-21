package com.ligresoftware.queechanenelcine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ligresoftware.queechanenelcine.adapters.SesionesAdapter;
import com.ligresoftware.queechanenelcine.models.Cine;
import com.ligresoftware.queechanenelcine.models.Sesion;
import com.ligresoftware.queechanenelcine.models.helpers.CineList;
import com.ligresoftware.queechanenelcine.utils.CineUtils;
import com.ligresoftware.queechanenelcine.utils.Logger;

import java.util.List;


public class CineDetailActivity extends ActionBarActivity {
    private Toolbar mToolbar;
    private String _idCine;
    private String cineName;
    private ListView lista;
    private List sesiones;
    private Activity actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cine_detail);

        actividad = this;

        //Cojo los datos que me pasan
        Intent intent = getIntent();
        _idCine = intent.getStringExtra(Constants.EXTRA_CINE_ID);
        cineName = intent.getStringExtra(Constants.EXTRA_CINE_NAME);

        Logger.d("CINE-DETAIL", "Cine " + cineName + " id " + _idCine);

        //Pongo la toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar_cine_detail);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);
        mToolbar.setTitle(cineName);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Eventos onclick
        lista = (ListView) findViewById(R.id.listaCineDetail);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sesion sesionSelected = (Sesion) sesiones.get(position);
                Logger.d("SESION SELECTED", "" + sesionSelected.get_idPelicula());
                //Llamo a la actividad de detalle de la película

                Intent intent = new Intent(actividad, PeliculaDetailActivity.class);
                intent.putExtra(Constants.EXTRA_SESION_SELECTED, sesionSelected);
                startActivity(intent);
            }
        });

        //Consulto al webservice para que me de la información del cine
        CineUtils cUtils = new CineUtils();
        cUtils.setmCallback(new CineUtils.CineUtilsCallback() {
            @Override
            public void onGetCinesFinished(CineList listaCines) {
            }

            @Override
            public void onGetCineDetailFinished(Cine cine) {
                //Pongo la información del cine en la vista
                populateView(cine);
            }
        });
        cUtils.getCineDetail(_idCine);
    }

    private void populateView(Cine cine) {
        TextView tt = (TextView) findViewById(R.id.blablabla);
        tt.setText(cine.getNombreCiudad());

        sesiones = cine.getSesiones();

        //El adapter
        lista.setAdapter(new SesionesAdapter(this, sesiones));
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cine_detail, menu);
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
