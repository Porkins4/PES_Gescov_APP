package com.example.gescov.viewlayer.SchoolsActivities.schooluserslist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.R;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class SchoolUsersListViewAdapter extends ModelListViewAdapter {

    public SchoolUsersListViewAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = inflater.inflate(R.layout.user_list_item, null);
        TextView userName = v.findViewById(R.id.user_list_item_name);
        TextView userProfile = v.findViewById(R.id.user_list_item_profile);
        User user = (User) modelList.get(position);
        userName.setText(user.getName());
        userProfile.setText(user.getProfileType() != null ? user.getProfileType().getValue() : "");
        return v;
    }
}
