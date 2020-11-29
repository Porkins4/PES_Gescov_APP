package com.example.gescov.viewlayer.Templates;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

public abstract  class ModelListViewAdapter extends BaseAdapter {
    protected List modelList;
    protected LayoutInflater inflater;
    protected Context context;

    public ModelListViewAdapter(Context c, List list) {
        context = c;
        this.modelList = list;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setList(List list) {
        this.modelList = list;
        notifyDataSetChanged();
    }
}
