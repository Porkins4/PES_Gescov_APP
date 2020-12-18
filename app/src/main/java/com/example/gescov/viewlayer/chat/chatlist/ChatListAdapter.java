package com.example.gescov.viewlayer.chat.chatlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.ChatPreviewModel;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.viewlayer.Singletons.GescovApplication;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChatListAdapter extends BaseAdapter {
    private List<ChatPreviewModel> chatViewers;
    private LayoutInflater layoutInflater;

    public ChatListAdapter(List<ChatPreviewModel> chatViewerModels, Context c) {
        this.chatViewers = chatViewerModels;
        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return chatViewers.size();
    }

    @Override
    public ChatPreviewModel getItem(int position) {
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
        return v;
    }

    private void initViewComponents(int position, View v) {
        TextView name = (TextView) v.findViewById(R.id.user_name);
        String[] compoundName = chatViewers.get(position).getTarget().split("\\s+");
        if (compoundName.length > 1) name.setText(compoundName[0] + " " + compoundName[1]);
        else name.setText(compoundName[0]);


        TextView lastMessage = (TextView) v.findViewById(R.id.last_message);
        String msg = chatViewers.get(position).getLastMessage();
        if (msg == null) lastMessage.setText(GescovApplication.getContext().getString(R.string.no_messages_yet_text));
        else {
            if (msg.length() > 25) lastMessage.setText(msg.substring(0,25) + "...");
            else lastMessage.setText(msg);
        }


        TextView lastMessageHour = (TextView) v.findViewById(R.id.last_message_time);
        String hour = chatViewers.get(position).getHour();
        if (hour == null) lastMessageHour.setText("--:--");
        else lastMessageHour.setText(hour.substring(0,5));

        ImageView profilePic = (ImageView) v.findViewById(R.id.profile_image);
        loadImageFromUrl(profilePic,chatViewers.get(position).getTargetPic());

    }

    public String chatID(int position) {
        return getItem(position).getChatID();
    }

    public void delete(int position) {
        chatViewers.remove(position);
    }

    public void loadImageFromUrl(ImageView profilePic, String url) {
        Picasso.with(VolleyServices.getCtx()).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).noFade().into(profilePic, new com.squareup.picasso.Callback() {
            public void onSuccess() {
                //it returns nothing
            }
            @Override
            public void onError() {
                Log.i("loadingImage","error on loading imagge");
            }
        });
    }
}
