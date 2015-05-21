package com.ligresoftware.queechanenelcine.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ligresoftware.queechanenelcine.R;
import com.ligresoftware.queechanenelcine.models.Sesion;

import java.util.List;

public class SesionesAdapter extends ArrayAdapter {
    private Context context;

    public SesionesAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Sesion item = (Sesion) getItem(position);
        View viewToUse = null;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        //Obtengo el holder o lo creo si es necesario
        if (convertView == null) {
//            viewToUse = mInflater.inflate(R.layout.provincias_list_row, null);
            viewToUse = mInflater.inflate(R.layout.sesiones_list_row, parent, false);

            holder = new ViewHolder();
            // Cojo del layout los elementos
            holder.titulo = (TextView) viewToUse.findViewById(R.id.sesionesRowTitulo);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        //Relleno los datos
        holder.titulo.setText(item.getTitulo());

        return viewToUse;
    }

    /**
     * Almacena la información en cada elemento de la lista (rowItem)
     */
    private class ViewHolder {
        TextView titulo;
    }
}
