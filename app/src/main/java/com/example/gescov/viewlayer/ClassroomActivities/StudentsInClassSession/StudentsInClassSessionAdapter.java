package com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.Assignment;

import java.util.List;

public class StudentsInClassSessionAdapter extends BaseAdapter {

    private List<Assignment> studentsNames;
    LayoutInflater layoutInflater;

    public StudentsInClassSessionAdapter(Context c, List<Assignment> l) {
        studentsNames = l;
        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return studentsNames.size();
    }

    @Override
    public Object getItem(int position) {
        return studentsNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.student_in_class_session_item,null);
        TextView tvStudents = (TextView) v.findViewById(R.id.text_view_student_in_class_session_item);
        tvStudents.setText(studentsNames.get(position).getStudentName());
        return v;
    }
}
