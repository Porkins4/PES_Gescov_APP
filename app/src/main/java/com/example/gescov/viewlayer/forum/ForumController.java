package com.example.gescov.viewlayer.forum;

import android.content.Context;

import com.example.gescov.domainlayer.Classmodels.WallEntry;
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

    public void createForumEntry(String titleEntry, String textEntry, String schoolId) {
        PresentationControlFactory.getViewLayerController().createForumEntry(titleEntry, textEntry, schoolId);
    }

    public WallEntry getModelItemById(String wallEntryId) {
        for (Object we : modelList) {
            if (((WallEntry) we).getId().equals(wallEntryId))
                return (WallEntry) we;
        }
        return null;
    }

    public void createForumEntryReply(String wallEntryId, String content) {
        PresentationControlFactory.getViewLayerController().createForumEntryReply(wallEntryId, content);
    }

    public void deleteWallEntry(String wallEntryId) {
        PresentationControlFactory.getViewLayerController().deleteWallEntry(wallEntryId);
    }
}
