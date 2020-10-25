package com.example.gescov.PresentationLayer.ContagionList.Controllers;



import com.example.gescov.DomainLayer.Controllers.ModelController;
import com.example.gescov.PresentationLayer.ContagionList.ContagionListFragment;

import org.json.JSONException;


public class ContagionController {
    private ContagionListFragment Cl;
    private  ViewLayerController viewLayerController;

    public ContagionController(ContagionListFragment contagionlist ) {
        Cl = contagionlist;
        viewLayerController = new ViewLayerController();
    }
    public void refresh() throws  JSONException {
        Cl.updateData(viewLayerController.getAllContagions());
    }
}
