package com.ligresoftware.queechanenelcine;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ligresoftware.queechanenelcine.fragments.AddFavouriteProvinciasFragment;


public class AddFavouriteActivity extends ActionBarActivity {
    private Toolbar mToolbar;
    private AddFavouriteProvinciasFragment mAddFavouriteProvinciasFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favourite);

        //Pongo la toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar_add_favourite);
        mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mToolbar.setTitle("Pepito");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Fragmento inicial
        mAddFavouriteProvinciasFragment = new AddFavouriteProvinciasFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.container_add_favourite, mAddFavouriteProvinciasFragment, "AddFavoritos")
                .commit();
    }

    @Override
    public void onBackPressed() {
        //Aviso de que he terminado
        setResult(RESULT_OK);
        this.finish();

        super.onBackPressed();
    }
}
