package com.example.gescov.viewlayer.Templates;

import android.content.Context;

import com.example.gescov.DomainLayer.Classmodels.Classroom;

import java.util.ArrayList;
import java.util.List;

public abstract class GescovModelListedController {

    protected ModelListViewAdapter listViewAdapter;
    protected List modelList;

    public abstract void setListViewAdapter(Context context);

    public abstract ModelListViewAdapter getListViewAdapter();

    public abstract void refreshList();

    public List getList() {
        if (modelList != null)
            return modelList;
        modelList = new ArrayList<>();
        return modelList;
    }

    public void refreshList(List modelList) {
        this.modelList = modelList;
        getListViewAdapter().setList(modelList);
        getListViewAdapter().notifyDataSetChanged();
    }
}
