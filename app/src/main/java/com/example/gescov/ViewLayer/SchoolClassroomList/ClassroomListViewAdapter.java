package com.example.gescov.ViewLayer.SchoolClassroomList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescov.DomainLayer.Classmodels.Classroom;
import com.example.gescov.R;
import com.example.gescov.ViewLayer.PresentationControlFactory;

import java.util.ArrayList;
import java.util.List;

public class ClassroomListViewAdapter extends BaseAdapter {

    private List<Classroom> classroomList;
    private LayoutInflater mInflater;

    public ClassroomListViewAdapter(Context c, List<Classroom> l) {
        classroomList = l;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public List<Classroom> getNameList() {
        if (classroomList != null)
            return classroomList;
        classroomList = new ArrayList<>();
        return classroomList;
    }


    @Override
    public int getCount() {
        return classroomList.size();
    }

    @Override
    public Object getItem(int position) {
        return classroomList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.classroom_list_item, null);
        TextView name = (TextView) v.findViewById(R.id.classroom_name);
        TextView capacity = (TextView) v.findViewById(R.id.classroom_capacity);
        TextView rows = (TextView) v.findViewById(R.id.classroom_rows);
        TextView columns = (TextView) v.findViewById(R.id.classroom_columns);
        name.setOnClickListener(e-> {
            SchoolClassroomsCrontroller controller = PresentationControlFactory.getClassroomsCrontroller();
            SchoolClassromListActivity activity = (SchoolClassromListActivity) controller.getSchoolsAdministrationActivity();
            //TODO Intent intent = new Intent(activity, .class);
            //activity.startActivity(intent);
        });
        name.setText(classroomList.get(position).getName());
        capacity.setText(String.valueOf(classroomList.get(position).getCapacity() + " " + v.getResources().getText(R.string.classroom_capacity)));
        rows.setText(String.valueOf(classroomList.get(position).getRows() + " " + v.getResources().getText(R.string.classroom_rows)));
        columns.setText(String.valueOf(classroomList.get(position).getColumns() + " " + v.getResources().getText(R.string.classroom_cols)));
        return v;
    }
}