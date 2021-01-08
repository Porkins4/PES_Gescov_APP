package com.example.gescov.viewlayer.forum;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.WallEntry;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;
import com.example.gescov.viewlayer.forum.forumreply.ForumReplyListActivity;

import java.util.List;

public class ForumEntryListViewAdapter extends ModelListViewAdapter {

    public ForumEntryListViewAdapter(Context c, List list) {
        super(c, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.wall_entry_list_item, null);
        TextView titleTextView = v.findViewById(R.id.wall_entry_list_item_title);
        TextView dateTextView = v.findViewById(R.id.wall_item_reply_list_item_date);
        TextView schoolTextView = v.findViewById(R.id.wall_entry_reply_list_item_author);
        TextView contentTextView = v.findViewById(R.id.wall_item_reply_list_item_content);
        WallEntry wallEntry = (WallEntry) modelList.get(position);
        if (wallEntry.getTitle() != null)
            titleTextView.setText(wallEntry.getTitle());
        else
            titleTextView.setText(wallEntry.getId());
        dateTextView.setText(wallEntry.getTime());
        
        School school = PresentationControlFactory.getSchoolsCrontroller().getSchoolById(wallEntry.getSchoolId());
        schoolTextView.setText(school == null ? wallEntry.getSchoolId() : school.getName());

        //if content is too large, we reduce it and add ...
        String content = wallEntry.getContent();
        if (content.length() >= 200)
            content = content.substring(0, 197) + "...";
        contentTextView.setText(content);

        WallEntry we = ((WallEntry) modelList.get(position));

        v.setOnClickListener(e-> {
            Intent intent = new Intent(context, ForumReplyListActivity.class);
            intent.putExtra("wallEntry", we.getId());
            context.startActivity(intent);
        });

        v.setOnLongClickListener(e -> {
            if (isLoggedUserAdmin(school)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(e.getContext());
                builder.setTitle(e.getResources().getString(R.string.options))
                        .setItems(R.array.school_administration_menu_items, (dialog, which) -> {
                            if (which == 0) confirmDeleteSchoolPrompt(v, school, we.getId());
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                }
            return false;
        });

        return v;
    }

    private void confirmDeleteSchoolPrompt(View v, School school, String wallEntryId) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                PresentationControlFactory.getForumController().deleteWallEntry(wallEntryId);
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage(v.getContext().getString(R.string.forum_entry_list_delete_message)).setPositiveButton(v.getContext().getString(R.string.delete), dialogClickListener)
                .setNegativeButton(v.getContext().getString(R.string.cancel), dialogClickListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean isLoggedUserAdmin(School school) {
        return school.getAdministratorsList().contains(PresentationControlFactory.getViewLayerController().getLoggedUserInfo().getId());
    }
}
