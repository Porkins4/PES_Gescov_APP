package com.example.gescov.ViewLayer.ContagionList;



import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolsAdministrationFagment;
import com.example.gescov.ViewLayer.ViewLayerController;

import org.json.JSONException;


public class ContagionController {
    private ContagionListFragment contagionListFragment;
    private ViewLayerController viewLayerController;

    public void refresh() throws  JSONException {
        contagionListFragment.updateData(PresentationControlFactory.getViewLayerController().getAllContagions());
    }

    public void setContagionListFragment(ContagionListFragment fragment) {
        this.contagionListFragment = fragment;
    }
}
