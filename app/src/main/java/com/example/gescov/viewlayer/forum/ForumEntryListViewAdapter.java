package com.example.gescov.viewlayer.forum;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.WallEntry;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class ForumEntryListViewAdapter extends ModelListViewAdapter {

    public ForumEntryListViewAdapter(Context c, List list) {
        super(c, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.wall_entry_list_item, null);
        TextView titleTextView = v.findViewById(R.id.wall_entry_list_item_title);
        TextView dateTextView = v.findViewById(R.id.wall_item_list_item_date);
        TextView schoolTextView = v.findViewById(R.id.wall_entry_list_item_school);
        TextView contentTextView = v.findViewById(R.id.wall_item_list_item_content);
        WallEntry wallEntry = (WallEntry) modelList.get(position);
        titleTextView.setText(wallEntry.getId());
        dateTextView.setText(wallEntry.getHour());
        
        School school = PresentationControlFactory.getSchoolsCrontroller().getSchoolById(wallEntry.getSchoolId());
        schoolTextView.setText(school == null ? wallEntry.getSchoolId() : school.getName());


        contentTextView.setText(wallEntry.getContent());
        return v;
    }
}
