package com.example.gescov.viewlayer.schoolrequests;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gescov.DomainLayer.Classmodels.SchoolRequest;
import com.example.gescov.R;

import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class SchoolRequestsListViewAdapter extends ModelListViewAdapter {

    public SchoolRequestsListViewAdapter(Context c, List<SchoolRequest> list) {
        super(c, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View v = inflater.inflate(R.layout.school_request_list_item, null);
        TextView userNameText = v.findViewById(R.id.school_request_list_item_username);
        Button acceptButton = v.findViewById(R.id.school_request_list_item_accept);
        Button rejectButton = v.findViewById(R.id.school_request_list_item_reject);
        userNameText.setText(((SchoolRequest) modelList.get(position)).getId());
        return v;
    }
}
