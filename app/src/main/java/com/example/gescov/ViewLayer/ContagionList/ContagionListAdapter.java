package com.example.gescov.ViewLayer.ContagionList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescov.R;

import java.util.List;

public class ContagionListAdapter extends BaseAdapter {
    List<String> names;
    List<String> descriptions;
    LayoutInflater mInflater;

    public ContagionListAdapter(Context c, List<String> l , List<String> d) {
        names = l;
        descriptions = d;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.mylistofcontagion,null);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView description = (TextView) v.findViewById(R.id.description);
        String NameAtposition = names.get(position);
        String descriptionAtposition = descriptions.get(position);
        name.setText(NameAtposition);
        description.setText(descriptionAtposition);
        return v;
    }
}
