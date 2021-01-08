package com.example.gescov.viewlayer.SchoolsActivities.schooluserslist;

import android.os.Bundle;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.Templates.GescovModelListedActivity;
import com.example.gescov.viewlayer.Templates.GescovModelListedController;

public class SchoolUsersListActivity extends GescovModelListedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFABVisible(false);
    }

    @Override
    protected GescovModelListedController setModelController() {
        return PresentationControlFactory.getSchoolUsersController();
    }
}
