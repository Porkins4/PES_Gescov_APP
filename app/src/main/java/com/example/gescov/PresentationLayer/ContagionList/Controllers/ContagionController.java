package com.example.gescov.PresentationLayer.ContagionList.Controllers;

import com.example.gescov.DomainLayer.ContagionModelController;
import com.example.gescov.DomainLayer.IContagionModelControles;
import com.example.gescov.PresentationLayer.ContagionList.ContagionListFragment;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class ContagionController {
    private ContagionListFragment Cl;
    private IContagionModelControles CM;

    public ContagionController(ContagionListFragment contagionlist ) {
        Cl = contagionlist;
        CM = new ContagionModelController();
    }
    public void refresh() throws ExecutionException, InterruptedException, JSONException {
        Cl.updateData(CM.getAllContagions());
    }
}
