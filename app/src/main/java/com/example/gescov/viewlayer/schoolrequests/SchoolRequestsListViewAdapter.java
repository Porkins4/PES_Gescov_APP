package com.example.gescov.viewlayer.schoolrequests;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gescov.domainlayer.Classmodels.SchoolRequest;
import com.example.gescov.R;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
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
        acceptButton.setOnClickListener( e-> {
            PresentationControlFactory.getSchoolRequestsController().updateSchoolRequestStatus(SchoolRequest.SchoolRequestStatus.ACCEPTED.getValue(), ((SchoolRequest) modelList.get(position)).getId());
        });

        rejectButton.setOnClickListener( e-> {
            PresentationControlFactory.getSchoolRequestsController().updateSchoolRequestStatus(SchoolRequest.SchoolRequestStatus.REJECTED.getValue(), ((SchoolRequest) modelList.get(position)).getId());
        });

        if (((SchoolRequest) modelList.get(position)).getStatus().equals(SchoolRequest.SchoolRequestStatus.ACCEPTED)) {
            v.setBackgroundColor( v.getResources().getColor(R.color.accepted_green));
            acceptButton.setEnabled(false);
            rejectButton.setEnabled(false);
        } else if (((SchoolRequest) modelList.get(position)).getStatus().equals(SchoolRequest.SchoolRequestStatus.REJECTED)) {
            v.setBackgroundColor( v.getResources().getColor(R.color.rejected_red));
            rejectButton.setEnabled(false);
            acceptButton.setEnabled(false);
        }
        userNameText.setText(((SchoolRequest) modelList.get(position)).getUserName());
        return v;
    }

    @Override
    public void setList(List list) {
        this.modelList = list;
    }
}
