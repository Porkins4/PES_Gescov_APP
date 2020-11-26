package com.example.gescov.ViewLayer.chatlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescov.DomainLayer.Classmodels.ChatViewerModel;
import com.example.gescov.R;

import java.util.List;

public class ChatListAdapter extends BaseAdapter {
    private List<ChatViewerModel> chatViewers;
    private LayoutInflater layoutInflater;

    public ChatListAdapter(List<ChatViewerModel> chatViewerModels, Context c) {
        this.chatViewers = chatViewerModels;
        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return chatViewers.size();
    }

    @Override
    public Object getItem(int position) {
        return chatViewers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.chat_item,null);
        TextView name = (TextView) v.findViewById(R.id.user_name);
        name.setText(chatViewers.get(position).getDestiny());
        TextView lastMessage = (TextView) v.findViewById(R.id.last_message);
        lastMessage.setText(chatViewers.get(position).getLastMessage());
        TextView lastMessageHour = (TextView) v.findViewById(R.id.last_message_time);
        lastMessageHour.setText(chatViewers.get(position).getLastHourMessage());
        return v;
    }
}
