package com.example.gescov.ViewLayer.SchoolsAdministration;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.R;
import com.example.gescov.ViewLayer.PresentationControlFactory;

import java.util.List;

public class SchoolListViewAdapter extends BaseAdapter {

    private List<School> schoolList;
    private LayoutInflater mInflater;

    public SchoolListViewAdapter(Context c, List<School> l) {
        schoolList = l;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setSchoolList (List<School> schoolList) {
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
        View v = mInflater.inflate(R.layout.school_list_item, null);
        TextView name = (TextView) v.findViewById(R.id.school_name);
        TextView address = (TextView) v.findViewById(R.id.school_address);
        TextView state = (TextView) v.findViewById(R.id.school_state);

        name.setText(schoolList.get(position).getName());
        address.setText(schoolList.get(position).getAddress());
        state.setText(schoolList.get(position).getState());

        v.setOnClickListener(e-> {
            SchoolsCrontroller controller = PresentationControlFactory.getSchoolsCrontroller();
            controller.setCurrentSchool(((TextView) e.findViewById(R.id.school_name)).getText().toString());
            SchoolsAdministrationFagment fragment = controller.getSchoolsAdministrationFragment();
            Intent intent = new Intent(fragment.getContext(), SchoolDetails.class);
            fragment.startActivity(intent);
        });
        return v;
    }
}