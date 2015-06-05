package com.ligresoftware.queechanenelcine.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ligresoftware.queechanenelcine.R;
import com.ligresoftware.queechanenelcine.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class FavoritosAdapter extends BaseAdapter {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_HEADER = 1;

    private static final int TYPE_COUNT = 2;
    private LayoutInflater mInflater;
    private List<FavoritosAdapterHolder> mData = new ArrayList<>();

    public FavoritosAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Se pasa como "type" en el evento on click de un item de la lista
        return mData.get(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FavoritosAdapterHolder item = mData.get(position);
        ViewHolder holder = new ViewHolder();

        if (convertView == null) {
            switch (item.getType()) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.favoritos_list_row_item, null);
                    holder.cineNombre = (TextView) convertView.findViewById(R.id.favoritosRowItemCineName);
                    break;
                case TYPE_HEADER:
                    convertView = mInflater.inflate(R.layout.favoritos_list_row_header, null);
                    holder.ciudadNombre = (TextView) convertView.findViewById(R.id.favoritosRowHeaderCiudadName);
                    break;
            }

            if (convertView != null) {
                convertView.setTag(holder);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (holder.cineNombre != null && item.getTitulo() != null)
            holder.cineNombre.setText(item.getTitulo());

        if (holder.ciudadNombre != null && item.getTitulo() != null)
            holder.ciudadNombre.setText(String.valueOf(item.getTitulo()));

        return convertView;
    }

    public void updateAdapter(ArrayList<FavoritosAdapterHolder> entries) {
        mData = entries;
        Logger.d("ADAPTER", "Actualizo adaptador");
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView cineNombre = null;
        public TextView ciudadNombre = null;
    }
}
