package com.example.gescov.viewlayer.forum.forumreply;

import android.content.Context;

import com.example.gescov.domainlayer.Classmodels.WallEntry;
import com.example.gescov.viewlayer.Templates.GescovModelListedController;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

public class ForumRepliesController extends GescovModelListedController {
    private WallEntry wallEntry;

    @Override
    public void setListViewAdapter(Context context) {
        listViewAdapter = new ForumReplyListViewAdapter(context, getList());
    }

    @Override
    public ModelListViewAdapter getListViewAdapter() {
        return listViewAdapter;
    }

    @Override
    public void refreshList() {
        this.modelList = wallEntry.getReplies();
    }

    public void setWallEntry(WallEntry we) {
        this.wallEntry = we;
    }

    public void currentSchoolRefreshed() {
        if (listViewAdapter != null) {
            listViewAdapter.notifyDataSetChanged();
        }
    }

    public void refreshWallEntryReplies(WallEntry wallEntry) {
        listViewAdapter.setList(wallEntry.getReplies());
        listViewAdapter.notifyDataSetChanged();
    }
}
