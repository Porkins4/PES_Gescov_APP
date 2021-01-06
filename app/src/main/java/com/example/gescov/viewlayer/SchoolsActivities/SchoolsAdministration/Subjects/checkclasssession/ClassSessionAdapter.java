package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.checkclasssession;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.ClassSessionModel;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class ClassSessionAdapter extends ModelListViewAdapter {

    public ClassSessionAdapter(Context c, List list) {
        super(c, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.class_session_item, null);
        TextView concept = (TextView) v.findViewById(R.id.concept_name);
        TextView dateTime = (TextView) v.findViewById(R.id.date_data);
        TextView teacherName = (TextView) v.findViewById(R.id.teacher_name);
        ClassSessionModel cs = (ClassSessionModel) modelList.get(position);
        concept.setText(cs.getConcept() + " (" + cs.getClassroomName() + ")");
        dateTime.setText(cs.getDate() + " [" + cs.getHour().substring(0,2) + ":" + cs.getHour().substring(3,5) + " - " + cs.getFinishHour().substring(0,2) + ":" + cs.getFinishHour().substring(3,5) + "]");
        teacherName.setText(cs.getTeacherName());
        return v;
    }
}
