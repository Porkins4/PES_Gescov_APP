package com.example.gescov.ViewLayer.SchoolsAdministration;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.ViewLayer.SchoolClassroomList.SchoolClassromListActivity;
import com.example.gescov.ViewLayer.navigation.NavigationMenu;

import java.util.ArrayList;
import java.util.List;

public class SchoolListViewAdapter extends BaseAdapter {

    private List<String> schoolList;
    private LayoutInflater mInflater;

    public SchoolListViewAdapter(Context c, List<String> l) {
        schoolList = l;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<String> getNameList() {
        if (schoolList != null)
        return schoolList;
        schoolList = new ArrayList<>();
        return schoolList;
    }

    public void addItem(String name) {
        //replace to a call to SchoolsController to manage the new school.
        schoolList.add(name);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return schoolList.size();
    }

    @Override
    public Object getItem(int position) {
        return schoolList.get(position);
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
            SchoolsCrontroller controller = PresentationControlFactory.getSchoolsCrontroller();
            SchoolsAdministrationFagment fragment = controller.getSchoolsAdministrationFragment();
            Intent intent = new Intent(fragment.getContext(), SchoolClassromListActivity.class);
            fragment.startActivity(intent);
        });
        String n = schoolList.get(position);
        name.setText(n);
        return v;
    }
}
