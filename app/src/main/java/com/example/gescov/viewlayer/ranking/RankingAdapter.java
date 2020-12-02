package com.example.gescov.viewlayer.ranking;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.R;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class RankingAdapter extends ModelListViewAdapter {

    public RankingAdapter(Context c, List list) {
        super(c, list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.ranking_list_item,null);
        TextView name = (TextView) v.findViewById(R.id.school);
        TextView positionRank = (TextView) v.findViewById(R.id.position_school);
        Pair <School,Integer> aux =  (Pair<School,Integer>) getItem(position);
        name.setText(aux.first.getName());
        positionRank.setText(position);
        return v;
    }
}
