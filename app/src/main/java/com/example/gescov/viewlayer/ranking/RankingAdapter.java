package com.example.gescov.viewlayer.ranking;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.SchoolDetailsActivity;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.SchoolsCrontroller;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class RankingAdapter extends ModelListViewAdapter {

    public RankingAdapter(Context c, List list) {
        super(c, list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.ranking_list_item,null);
        TextView name = v.findViewById(R.id.school);
        ImageView image = v.findViewById(R.id.trophy);
        TextView numContagions = v.findViewById(R.id.num_of_contagion);
        TextView positionRank = v.findViewById(R.id.position_school);
        TextView address = v.findViewById(R.id.school_address_Rank);
        ImageButton share = v.findViewById(R.id.share_button);
        Pair <School,Integer> school =  (Pair<School,Integer>) getItem(position);
        Integer pos = position + 1;

        if (isMySchool(school.first.getId())) share.setVisibility(View.VISIBLE);
        else share.setVisibility(View.INVISIBLE);
        setListenerShare(v, share, pos, school.first.getName());

        name.setText(school.first.getName());
        positionRank.setText((pos).toString() +". ");
        address.setText(school.first.getAddress());

        if (pos == 1 ) {
            image.setImageResource(R.drawable.medical_mask_gold);
        } else if (pos == 2) {
            image.setImageResource(R.drawable.medical_mask_silver);
        } else if ( pos == 3 ) {
            image.setImageResource(R.drawable.medical_mask_bronze);
        }
        if ( school.second == 1 ) numContagions.setText(school.second.toString()+ " "+ v.getContext().getString(R.string.num_contagions_singular));
        else numContagions.setText(school.second.toString() + " "+ v.getContext().getString(R.string.num_contagions_plural));

        v.setOnClickListener(e-> {
            SchoolsCrontroller controller = PresentationControlFactory.getSchoolsCrontroller();
            controller.setCurrentSchool(school.first);
            Intent intent = new Intent(v.getContext(), SchoolDetailsActivity.class);
            v.getContext().startActivity(intent);
        });
        return v;
    }

    private boolean isMySchool(String schoolID) {
        return PresentationControlFactory.getRankingController().isMySchool(schoolID);
    }

    private void setListenerShare(View v, ImageButton share, Integer pos, String name) {
        Context con = v.getContext();
        share.setOnClickListener(view -> {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, con.getString(R.string.share_text) + " (" + name + ") " + con.getString(R.string.share2_text) + " " + pos + " " + con.getString(R.string.share3_text));
            con.startActivity(Intent.createChooser(shareIntent, "Share your ranking"));
        });
    }
}
