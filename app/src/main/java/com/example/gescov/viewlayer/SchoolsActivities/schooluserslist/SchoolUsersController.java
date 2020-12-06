package com.example.gescov.viewlayer.SchoolsActivities.schooluserslist;

import android.content.Context;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.Templates.GescovModelListedController;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

public class SchoolUsersController extends GescovModelListedController {

    @Override
    public void setListViewAdapter(Context context) {
        listViewAdapter = new SchoolUsersListViewAdapter(context, getList());
    }

    @Override
    public ModelListViewAdapter getListViewAdapter() {
        return listViewAdapter;
    }

    @Override
    public void refreshList() {
        PresentationControlFactory.getViewLayerController().refreshUsersListBySchoolId();
    }

    public void currentSchoolRefreshed() {
        if (listViewAdapter != null) {
            listViewAdapter.notifyDataSetChanged();
        }
    }
}
