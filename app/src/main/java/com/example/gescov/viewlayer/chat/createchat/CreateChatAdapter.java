package com.example.gescov.viewlayer.chat.createchat;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.domainlayer.Services.Volley.VolleyServices;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;
import com.squareup.picasso.Picasso;

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
        ImageView profilePic = (ImageView) v.findViewById(R.id.profile_image);
        loadImageFromUrl(profilePic, u.getPic());
        return v;
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
