package com.ligresoftware.queechanenelcine.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
            holder.portada = (ImageView) viewToUse.findViewById(R.id.sesionesRowPortada);
            holder.titulo = (TextView) viewToUse.findViewById(R.id.sesionesRowTitulo);
            holder.duracion = (TextView) viewToUse.findViewById(R.id.sesionesRowDuracion);
            holder.genero = (TextView) viewToUse.findViewById(R.id.sesionesRowGenero);
            holder.horarios = (TextView) viewToUse.findViewById(R.id.sesionesRowHorarios);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        //La imagen
        String[] partes = item.getImagen().split(",");
        if (partes.length == 2) {
            byte[] decodedString = Base64.decode(partes[1].getBytes(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.portada.setImageBitmap(bitmap);
        } else {
            //TODO pongo imagen por defecto
        }

        //Relleno los datos
        holder.titulo.setText(item.getTitulo());
        holder.duracion.setText(context.getString(R.string.duracion) + " " + item.getDuracion() + " " + context.getString(R.string.minutos));
        holder.genero.setText(implode(item.getGenero(), ", "));
        holder.horarios.setText(implode(item.getHorarios(), "  "));

        return viewToUse;
    }

    /**
     * Almacena la información en cada elemento de la lista (rowItem)
     */
    private class ViewHolder {
        ImageView portada;
        TextView titulo;
        TextView duracion;
        TextView genero;
        TextView horarios;
    }

    private String implode(List<String> lista, String concatenador) {
        StringBuilder sb = new StringBuilder();
        int cuantos = lista.size();
        int contador = 1;

        for (String s : lista) {
            sb.append(s);

            if (contador < cuantos) {
                sb.append(concatenador);
            }
            contador++;
        }

        return sb.toString();
    }
}
