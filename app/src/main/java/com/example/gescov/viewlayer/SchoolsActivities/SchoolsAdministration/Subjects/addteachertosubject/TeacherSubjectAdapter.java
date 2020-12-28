package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.addteachertosubject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class TeacherSubjectAdapter extends ModelListViewAdapter {
    public TeacherSubjectAdapter(Context c, List teachers) {
        super(c, teachers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.add_teacher_to_subject_item, null);
        TextView teacherName = (TextView) v.findViewById(R.id.teacher_name);
        User u = (User) modelList.get(position);
        teacherName.setText(u.getName());
        return v;
    }

    public void deleteIth(int position) {
        modelList.remove(position);
    }
}
