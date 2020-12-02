package com.example.gescov.viewlayer.SchoolsActivities.schooluserslist;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.Templates.GescovModelListedActivity;
import com.example.gescov.viewlayer.Templates.GescovModelListedController;

public class SchoolUsersListActivity extends GescovModelListedActivity {
    @Override
    protected GescovModelListedController setModelController() {
        return PresentationControlFactory.getSchoolUsersController();
    }
}
