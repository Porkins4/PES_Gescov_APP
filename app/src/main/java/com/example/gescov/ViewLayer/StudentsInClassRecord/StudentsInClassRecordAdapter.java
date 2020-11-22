package com.example.gescov.ViewLayer.StudentsInClassRecord;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.R;

import java.util.List;

public class StudentsInClassRecordAdapter extends BaseAdapter {
    private List<Pair<User, Pair<Integer,Integer>>> students;
    private LayoutInflater layoutInflater;
    private StudentsInClassRecordActivity studentsInClassRecordActivity;

    public StudentsInClassRecordAdapter(Context c, List<Pair<User, Pair<Integer,Integer>>> l, StudentsInClassRecordActivity context) {
        students = l;
        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        studentsInClassRecordActivity = context;
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
        name.setText(students.get(position).first.getName());
        TextView pos = (TextView) v.findViewById(R.id.students_in_classroom_admin_student_position);
        StringBuilder t = new StringBuilder(studentsInClassRecordActivity.getString(R.string.alummne_assegut_a));
        t.append(students.get(position).second.first);
        t.append(studentsInClassRecordActivity.getString(R.string.alumne_assegut_a_columna));
        t.append(students.get(position).second.second);
        pos.setText(t.toString());
        return v;
    }
}
