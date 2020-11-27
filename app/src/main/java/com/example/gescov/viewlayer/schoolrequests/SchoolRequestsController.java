package com.example.gescov.viewlayer.schoolrequests;

import android.content.Context;

import com.example.gescov.DomainLayer.Classmodels.SchoolRequest;
import com.example.gescov.DomainLayer.Controllers.SchoolRequestModelController;
import com.example.gescov.DomainLayer.Singletons.DomainControlFactory;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.Templates.GescovModelListedController;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

public class SchoolRequestsController extends GescovModelListedController {
    @Override
    public void setListViewAdapter(Context context) {
        listViewAdapter = new SchoolRequestsListViewAdapter(context, getList());
    }

    @Override
    public ModelListViewAdapter getListViewAdapter() {
        return listViewAdapter;
    }

    @Override
    public void refreshList() {
        DomainControlFactory.getSchoolRequestModelController().getSchoolRequestsBySchoolId(PresentationControlFactory.getSchoolsCrontroller().getCurrentSchool().getId());
    }
}
