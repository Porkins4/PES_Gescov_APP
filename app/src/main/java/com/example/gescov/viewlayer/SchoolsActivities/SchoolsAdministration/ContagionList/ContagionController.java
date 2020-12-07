package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.ContagionList;



import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.ContagionList.ContagionListActivity;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import org.json.JSONException;


public class ContagionController {
    private ContagionListActivity contagionListActivity;
    public void refresh(String schoolID) throws  JSONException {
        contagionListActivity.updateData(PresentationControlFactory.getViewLayerController().getAllContagions(schoolID));
    }

    public void setContagionListFragment(ContagionListActivity activity) {
        this.contagionListActivity = activity;
    }
}
