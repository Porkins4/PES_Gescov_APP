package com.example.gescov.viewlayer.forum;

import android.content.Context;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.Templates.GescovModelListedController;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

public class ForumController extends GescovModelListedController {

    @Override
    public void setListViewAdapter(Context context) {
        listViewAdapter = new ForumEntryListViewAdapter(context, getList());
    }

    @Override
    public ModelListViewAdapter getListViewAdapter() {
        return listViewAdapter;
    }

    @Override
    public void refreshList() {
        PresentationControlFactory.getViewLayerController().refreshLoggedUserSchoolsWallEntries();
    }

    public void createForumEntry(String textEntry, String schoolId) {
        PresentationControlFactory.getViewLayerController().createForumEntry(textEntry, schoolId);
    }
}
