package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class SubjectAdapter extends ModelListViewAdapter {
    public SubjectAdapter(Context c, List list) {
        super(c, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.subject_list_item,null);
        TextView subjectName = v.findViewById(R.id.subject_name);
        Subject subject = (Subject) getItem(position);
        subjectName.setText(subject.getName());
        return v;
    }
}
