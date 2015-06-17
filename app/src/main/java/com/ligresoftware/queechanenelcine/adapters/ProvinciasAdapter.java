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
import com.ligresoftware.queechanenelcine.models.Provincia;

import java.util.List;

public class ProvinciasAdapter extends ArrayAdapter {
    private Context context;

    public ProvinciasAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Provincia item = (Provincia) getItem(position);
        View viewToUse = null;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        //Obtengo el holder o lo creo si es necesario
        if (convertView == null) {
//            viewToUse = mInflater.inflate(R.layout.provincias_list_row, null);
            viewToUse = mInflater.inflate(R.layout.provincias_list_row, parent, false);

            holder = new ViewHolder();
            // Cojo del layout los elementos
            holder.provinciaName = (TextView) viewToUse.findViewById(R.id.provinciasRowName);
            holder.provinciaFondo = (ImageView) viewToUse.findViewById(R.id.provinciasRowFondo);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        //Relleno los datos
        holder.provinciaName.setText(item.getNombre());
        /*try {
            String imagen = item.getNombre().toLowerCase();
//            Logger.w("Provincia: ", imagen);
            imagen = imagen.replace(' ', '_');
            imagen = imagen.replace('.', '_');
            imagen = imagen.replace('á', 'a');
            imagen = imagen.replace('é', 'e');
            imagen = imagen.replace('í', 'i');
            imagen = imagen.replace('ó', 'o');
            imagen = imagen.replace('ú', 'u');
            imagen = imagen.replace('ñ', 'n');
            holder.provinciaFondo.setImageDrawable(Drawable.createFromStream(getContext().getAssets().open(imagen + ".jpg"), null));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return viewToUse;
    }

    /**
     * Almacena la información en cada elemento de la lista (rowItem)
     */
    private class ViewHolder {
        TextView provinciaName;
        ImageView provinciaFondo;
    }
}
