package com.example.gescov.viewlayer.chat.chatview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gescov.domainlayer.Classmodels.MessageModel;
import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.GescovApplication;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context context;
    private String loggedUserID;
    private List<MessageModel> messages;
    private String pic;

    public MessageAdapter(Context context, List<MessageModel> messages, String loggedUserID, String targetPic) {
        this.messages = messages;
        this.context = context;
        this.loggedUserID = loggedUserID;
        this.pic = targetPic;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if (viewType == MSG_TYPE_LEFT) {
            v = LayoutInflater.from(context).inflate(R.layout.chat_item_left,parent,false);
            return new MessageAdapter.ViewHolder(v);
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.chat_item_right,parent,false);
            return new MessageAdapter.ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        MessageModel message = messages.get(position);
        holder.message_text.setText(message.getText());
        holder.message_hour.setText(message.getHour().substring(0,5));
        if (holder.profile_image != null) loadImageFromUrl(holder.profile_image,pic);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void loadImageFromUrl(ImageView profilePic, String url) {
        Picasso.with(GescovApplication.getContext()).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).noFade().into(profilePic, new com.squareup.picasso.Callback() {
            public void onSuccess() {
                //it returns nothing
            }
            @Override
            public void onError() {
                Log.i("loadingImage","error on loading imagge");
            }
        });
    }

    public int size() {
        return messages.size();
    }

    public MessageModel getIth(int i) {
        return messages.get(i);
    }

    public boolean empty() {
        return messages.size() == 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView message_text;
        public TextView message_hour;
        public ImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            message_text = itemView.findViewById(R.id.message_text);
            profile_image = itemView.findViewById(R.id.profile_image);
            message_hour = itemView.findViewById(R.id.message_hour);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).getCreatorID().equals(loggedUserID)) return MSG_TYPE_RIGHT;
        return MSG_TYPE_LEFT;
    }
}
