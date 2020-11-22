package com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassRecord;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescov.R;

import java.util.List;

public class StudentsInClassRecordAdapter extends BaseAdapter {
    private List<Pair<String,String>> students;
    LayoutInflater layoutInflater;

    public StudentsInClassRecordAdapter(Context c, List<Pair<String,String>> l) {
        students = l;
        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.students_in_classroom_admin_item,null);
        TextView name = (TextView) v.findViewById(R.id.students_in_classroom_admin_student_Name);
        name.setText(students.get(position).first);
        TextView pos = (TextView) v.findViewById(R.id.students_in_classroom_admin_student_position);
        pos.setText(students.get(position).second);
        return v;
    }
}
