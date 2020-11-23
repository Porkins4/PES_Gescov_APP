package com.example.gescov.ViewLayer.ContagionList;



import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;

import org.json.JSONException;


public class ContagionController {
    private ContagionListFragment contagionListFragment;
    public void refresh() throws  JSONException {
        contagionListFragment.updateData(PresentationControlFactory.getViewLayerController().getAllContagions());
    }

    public void setContagionListFragment(ContagionListFragment fragment) {
        this.contagionListFragment = fragment;
    }
}
