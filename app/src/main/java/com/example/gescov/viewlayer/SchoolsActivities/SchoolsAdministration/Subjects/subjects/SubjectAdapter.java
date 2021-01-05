package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.subjects;

import android.content.Context;
import android.content.Intent;
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
        TextView enrolledTeachers = v.findViewById(R.id.enrolled_teachers);
        TextView enrolledUsers = v.findViewById(R.id.enrolled_users);
        TextView schoolName = v.findViewById(R.id.school_name);
        Subject subject = (Subject) getItem(position);
        subjectName.setText(subject.getName());
        schoolName.setVisibility(View.GONE);
        setClickListener(v,subject);
        enrolledTeachers.setText(context.getString(R.string.enrolled_professors_text_item) + subject.getTeachers().size());
        enrolledUsers.setText(context.getString(R.string.enrolled_users_text_item) + subject.getStudents().size());
        return v;
    }

    private void setClickListener(View v, Subject subject) {
        v.setOnClickListener(v1 -> {
            Intent intent = new Intent(v.getContext(), SubjectDetailActivity.class);
            intent.putExtra("subjectName",subject.getName());
            intent.putExtra("subjectID",subject.getId());
            intent.putExtra("schoolID",subject.getSchoolID());
            v.getContext().startActivity(intent);
        });
    }
}
