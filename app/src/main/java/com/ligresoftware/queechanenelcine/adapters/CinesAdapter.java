package com.ligresoftware.queechanenelcine.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ligresoftware.queechanenelcine.R;
import com.ligresoftware.queechanenelcine.models.Cine;
import com.ligresoftware.queechanenelcine.models.Favorito;
import com.ligresoftware.queechanenelcine.models.FavoritoList;
import com.ligresoftware.queechanenelcine.utils.CineUtils;

import java.util.List;

public class CinesAdapter extends ArrayAdapter {
    private Context context;
    private FavoritoList listaFavoritos;

    public CinesAdapter(Context context, List items, FavoritoList lista) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
        this.listaFavoritos = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Cine item = (Cine) getItem(position);
        View viewToUse = null;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        //Obtengo el holder o lo creo si es necesario
        if (convertView == null) {
//            viewToUse = mInflater.inflate(R.layout.provincias_list_row, null);
            viewToUse = mInflater.inflate(R.layout.cines_list_row, parent, false);

            holder = new ViewHolder();
            // Cojo del layout los elementos
            holder.cineName = (TextView) viewToUse.findViewById(R.id.cinesRowName);
            holder.cineFavorito = (ImageView) viewToUse.findViewById(R.id.cinesFavouritedCheck);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        //Relleno los datos
        holder.cineName.setText(item.getNombre());

        boolean existe = false;
        if (listaFavoritos != null) {
            //Compruebo si este cine ya lo tengo en favoritos
            for (Favorito fav : listaFavoritos.getListaFavoritos()) {
                if (fav.getIdCine().equals(item.get_id())) {
                    existe = true;
                }
            }
        }
        holder.cineFavorito.setTag(R.string.tag_favorito, existe);
        holder.cineFavorito.setImageResource(CineUtils.getCineFavouritedResource(existe));

        return viewToUse;
    }

    /**
     * Almacena la información en cada elemento de la lista (rowItem)
     */
    private class ViewHolder {
        TextView cineName;
        ImageView cineFavorito;
    }
}
