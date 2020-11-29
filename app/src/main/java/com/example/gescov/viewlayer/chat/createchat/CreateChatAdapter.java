package com.example.gescov.viewlayer.chat.createchat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.R;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class CreateChatAdapter extends ModelListViewAdapter {

    public CreateChatAdapter(Context c, List<User> l) {
        super(c, l);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.create_chat_list_item, null);
        TextView contactName = (TextView) v.findViewById(R.id.contact_name);
        User u = (User) modelList.get(position);
        contactName.setText(u.getName());
        return v;
    }
}
