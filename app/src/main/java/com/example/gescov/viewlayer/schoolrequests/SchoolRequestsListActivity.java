package com.example.gescov.viewlayer.schoolrequests;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.Templates.GescovModelListedActivity;
import com.example.gescov.viewlayer.Templates.GescovModelListedController;

public class SchoolRequestsListActivity extends GescovModelListedActivity {

    @Override
    protected GescovModelListedController setModelController() {
        return PresentationControlFactory.getSchoolRequestsController();
    }
}