package com.example.andree.minisega;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class MateriasFaltasAdapter extends BaseAdapter {

    private static final String LOG_TAG = MateriasFaltasAdapter.class.getSimpleName();

    private Context context;
    private ArrayList<ArrayList<String>> materiasFaltasItems;

    public MateriasFaltasAdapter(Context context, ArrayList<ArrayList<String>> materiasFaltasItems) {
        this.context = context;
        this.materiasFaltasItems = materiasFaltasItems;
    }

    @Override
    public int getCount() {
        return materiasFaltasItems.size();
    }

    @Override
    public Object getItem(int position) {
        return materiasFaltasItems.get(position).get(0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
