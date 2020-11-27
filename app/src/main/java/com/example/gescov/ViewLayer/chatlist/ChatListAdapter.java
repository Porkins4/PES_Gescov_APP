package com.example.gescov.ViewLayer.chatlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    private Context context;

    public ChatListAdapter(List<ChatViewerModel> chatViewerModels, Context c) {
        this.chatViewers = chatViewerModels;
        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
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
        initViewComponents(position, v);
        setOptionsListener(position,v);
        return v;
    }

    private void setOptionsListener(int position, View v) {
        v.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle(context.getString(R.string.chat_options))
                                .setItems(R.array.chat_options_menu_items, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        System.out.println("im pressed");
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        System.out.println("long click");
                        return false;
                    }
                }
        );
    }



    private void initViewComponents(int position, View v) {
        TextView name = (TextView) v.findViewById(R.id.user_name);
        name.setText(chatViewers.get(position).getDestiny());
        TextView lastMessage = (TextView) v.findViewById(R.id.last_message);
        lastMessage.setText(chatViewers.get(position).getLastMessage());
        TextView lastMessageHour = (TextView) v.findViewById(R.id.last_message_time);
        lastMessageHour.setText(chatViewers.get(position).getLastHourMessage());
    }
}
