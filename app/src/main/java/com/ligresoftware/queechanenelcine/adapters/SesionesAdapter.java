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
import com.ligresoftware.queechanenelcine.models.Pelicula;
import com.ligresoftware.queechanenelcine.models.Sesion;
import com.ligresoftware.queechanenelcine.utils.MyUtils;

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
        Pelicula peli = item.getPelicula();
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
            holder.horariostitle = (TextView) viewToUse.findViewById(R.id.sesionesRowHorariosTitle);
            holder.horarios = (TextView) viewToUse.findViewById(R.id.sesionesRowHorarios);
            holder.horarios3Dtitle = (TextView) viewToUse.findViewById(R.id.sesionesRowHorarios3dTitle);
            holder.horarios3D = (TextView) viewToUse.findViewById(R.id.sesionesRowHorarios3d);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        //La imagen
        if (peli.getImagen() != null) {
            String[] partes = peli.getImagen().split(",");

            if (partes.length == 2) {
                byte[] decodedString = Base64.decode(partes[1].getBytes(), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                holder.portada.setImageBitmap(bitmap);
            }
        }

        //Relleno los datos
        holder.titulo.setText(peli.getTitulo());

        if (peli.getDuracion() != 0) {
            holder.duracion.setText(peli.getDuracion() + " " + context.getString(R.string.minutos));
        } else {
            holder.duracion.setText("");
        }

//        holder.duracion.setText(context.getString(R.string.duracion) + " " + peli.getDuracion() + " " + context.getString(R.string.minutos));
        holder.genero.setText(MyUtils.implode(peli.getGenero(), ", ").trim());

        if (!item.getHorarios().isEmpty()) {
            holder.horariostitle.setText(context.getString(R.string.horarios));
            holder.horarios.setText(MyUtils.implode(item.getHorarios(), "  "));
            holder.horariostitle.setVisibility(View.VISIBLE);
            holder.horarios.setVisibility(View.VISIBLE);
        } else {
            holder.horariostitle.setText("");
            holder.horarios.setText("");
            holder.horariostitle.setVisibility(View.GONE);
            holder.horarios.setVisibility(View.GONE);
        }

        if (!item.getHorarios3D().isEmpty()) {
            holder.horarios3Dtitle.setText(context.getString(R.string.horarios3d));
            holder.horarios3D.setText(MyUtils.implode(item.getHorarios3D(), "  "));
            holder.horarios3Dtitle.setVisibility(View.VISIBLE);
            holder.horarios3D.setVisibility(View.VISIBLE);
        } else {
            holder.horarios3Dtitle.setText("");
            holder.horarios3D.setText("");
            holder.horarios3Dtitle.setVisibility(View.GONE);
            holder.horarios3D.setVisibility(View.GONE);
        }

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
        TextView horariostitle;
        TextView horarios3D;
        TextView horarios3Dtitle;
    }
}
