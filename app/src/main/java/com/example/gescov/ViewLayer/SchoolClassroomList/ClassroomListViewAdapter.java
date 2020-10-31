package com.example.gescov.ViewLayer.SchoolClassroomList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolsAdministrationFagment;
import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolsCrontroller;

import java.util.ArrayList;
import java.util.List;

public class ClassroomListViewAdapter extends BaseAdapter {

    private List<String> classroom;
    private LayoutInflater mInflater;

    public ClassroomListViewAdapter(Context c, List<String> l) {
        classroom = l;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<String> getNameList() {
        if (classroom != null)
        return classroom;
        classroom = new ArrayList<>();
        return classroom;
    }

    public void addItem(String name) {
        //replace to a call to SchoolsController to manage the new school.
        classroom.add(name);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return classroom.size();
    }

    @Override
    public Object getItem(int position) {
        return classroom.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.school_list_item, null);
        TextView name = (TextView) v.findViewById(R.id.school_name);
        name.setOnClickListener(e-> {
            SchoolClassroomsCrontroller controller = PresentationControlFactory.getClassroomsCrontroller();
            SchoolClassromListActivity activity = (SchoolClassromListActivity) controller.getSchoolsAdministrationActivity();
            //TODO Intent intent = new Intent(activity, .class);
            //activity.startActivity(intent);
        });
        String n = classroom.get(position);
        name.setText(n);
        return v;
    }
}
