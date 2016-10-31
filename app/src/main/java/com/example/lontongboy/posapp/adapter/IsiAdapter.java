package com.example.lontongboy.posapp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lontongboy.posapp.R;
import com.example.lontongboy.posapp.model.Barang;

import java.util.List;

/**
 * This <PosappSP> project in package <com.example.lontongboy.posapp.adapter> created by :
 * Name         : syafiq
 * Date / Time  : 30 October 2016, 10:58 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public class IsiAdapter extends ArrayAdapter<Barang>
{
    private final Context      context;
    private final List<Barang> values;

    public IsiAdapter(Context context, int resource, List<Barang> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.values = objects;
    }

    @Nullable
    @Override
    public Barang getItem(int position)
    {
        return this.values.get(position);
    }

    @Override
    public int getCount()
    {
        return this.values.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View      rowView   = inflater.inflate(R.layout.isi_pesanan_content, parent, false);
        TextView  namaBarang  = (TextView) rowView.findViewById(R.id.namaBarang1);
        TextView  letakBarang  = (TextView) rowView.findViewById(R.id.letakBarang1);
        TextView  hargaBarang  = (TextView) rowView.findViewById(R.id.hargaBarang1);
        final Barang barang = this.values.get(position);
        namaBarang.setText(barang.getNama_barang());
        letakBarang.setText(barang.getLetak_barang());
        hargaBarang.setText(String.valueOf(barang.getHarga()));

        return rowView;
    }
}
