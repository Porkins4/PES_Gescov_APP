package com.example.gescov.ViewLayer.ContagionList;



import com.example.gescov.ViewLayer.ViewLayerController;

import org.json.JSONException;


public class ContagionController {
    private ContagionListFragment Cl;
    private ViewLayerController viewLayerController;

    public ContagionController(ContagionListFragment contagionlist ) {
        Cl = contagionlist;
        viewLayerController = new ViewLayerController();
    }
    public void refresh() throws  JSONException {
        Cl.updateData(viewLayerController.getAllContagions());
    }
}
