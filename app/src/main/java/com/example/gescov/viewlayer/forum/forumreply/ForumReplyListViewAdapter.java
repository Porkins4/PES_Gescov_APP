package com.example.gescov.viewlayer.forum.forumreply;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.domainlayer.Classmodels.WallEntryReply;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class ForumReplyListViewAdapter extends ModelListViewAdapter {

    public ForumReplyListViewAdapter(Context context, List list) {
        super(context, list);
    }
    private User loggedUser;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.wall_entry_reply_list_item, null);
        TextView weAuthor = v.findViewById(R.id.wall_entry_reply_list_item_author);
        TextView weDate = v.findViewById(R.id.wall_item_reply_list_item_date);
        TextView weContent = v.findViewById(R.id.wall_item_reply_list_item_content);


        WallEntryReply wallEntryReply = (WallEntryReply) modelList.get(position);

        weAuthor.setText(wallEntryReply.getUsername());
        weDate.setText(wallEntryReply.getHour());;
        weContent.setText(wallEntryReply.getText());

        return v;
    }
}
