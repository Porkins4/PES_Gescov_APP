package com.example.gescov.viewlayer.SchoolsActivities.studentschools.allSchools;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.R;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.SchoolDetailsActivity;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.SchoolsAdministrationFagment;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.SchoolsCrontroller;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class SchoolListViewAdapter extends BaseAdapter {

    private List<School> schoolList;
    private LayoutInflater inflater;

    public SchoolListViewAdapter(Context c, List<School> l) {
        schoolList = l;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList (List<School> schoolList) {
        this.schoolList = schoolList;
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
        View v = inflater.inflate(R.layout.school_list_item, null);
        TextView name = (TextView) v.findViewById(R.id.school_name);
        TextView address = (TextView) v.findViewById(R.id.school_address);
        TextView state = (TextView) v.findViewById(R.id.school_state);

        name.setText(schoolList.get(position).getName());
        address.setText(schoolList.get(position).getAddress());
        state.setText(schoolList.get(position).getState());

        v.setOnClickListener(e-> {
            SchoolsCrontroller controller = PresentationControlFactory.getSchoolsCrontroller();
            controller.setCurrentSchool(schoolList.get(position));
            SchoolsAdministrationFagment fragment = controller.getSchoolsAdministrationFragment();
            Intent intent = new Intent(fragment.getContext(), SchoolDetailsActivity.class);
            fragment.startActivity(intent);
        });
        return v;
    }
}