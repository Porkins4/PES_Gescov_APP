package com.example.gescov.viewlayer.subjectsofuser;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class SubjectsFromUserAdapter extends ModelListViewAdapter {

    public SubjectsFromUserAdapter(Context c, List list) {
        super(c, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.subject_list_item, null);
        TextView subjectName = (TextView) v.findViewById(R.id.subject_name);
        TextView subjectSchool = (TextView) v.findViewById(R.id.school_name);
        TextView enrolledUsers = (TextView) v.findViewById(R.id.enrolled_users);
        TextView enrolledTeachers = (TextView) v.findViewById(R.id.enrolled_teachers);
        Subject s = (Subject) modelList.get(position);
        subjectName.setText(s.getName());
        subjectSchool.setText(s.getSchoolID());
        enrolledUsers.setVisibility(View.GONE);
        enrolledTeachers.setVisibility(View.GONE);
        return v;
    }
}
