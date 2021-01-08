package com.example.gescov.viewlayer.chat.chatlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.ChatPreviewModel;
import com.example.gescov.viewlayer.chat.chatview.ChatViewActivity;
import com.example.gescov.viewlayer.chat.createchat.CreateChatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ChatListFragment extends Fragment {

    private static final int SUCCESS_CREATE_CHAT = 200;
    private ChatListViewModel mViewModel;
    private ListView listView;
    private FloatingActionButton floatingActionButton;
    private View root;
    private boolean chatCreated;
    private String chatCreatedID;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.chat_list_fragment, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        chatCreated = false;
        initViewComponents();
    }

    private void initViewComponents() {
        listView = (ListView) root.findViewById(R.id.chat_fragment_list_view);
        floatingActionButton = (FloatingActionButton) root.findViewById(R.id.create_new_chat_button);
        floatingActionButton.setOnClickListener(
                v -> {
                    Intent i = new Intent(getActivity(), CreateChatActivity.class);
                    startActivityForResult(i,SUCCESS_CREATE_CHAT);
                }
        );
        listView.setOnItemClickListener(
                (parent, view, position, id) -> {
                    Intent i = new Intent(getActivity(), ChatViewActivity.class);
                    i.putExtra("chatID",mViewModel.getChatID(position));
                    i.putExtra("targetName",mViewModel.getTargetName(position));
                    i.putExtra("targetPic",mViewModel.getTargetPic(position));
                    startActivity(i);
                }
        );
    }

    private void initViewModelData() {
        if (mViewModel == null) mViewModel = new ViewModelProvider(this).get(ChatListViewModel.class);
        mViewModel.getChatPreviewModels().observe(getActivity(), error -> {
            if (!error) {
                if (chatCreated) {
                    Intent i = new Intent(getActivity(), ChatViewActivity.class);
                    ChatPreviewModel newChat = mViewModel.getChatByPreviewByID(chatCreatedID);
                    i.putExtra("chatID", newChat.getChatID());
                    i.putExtra("targetName", newChat.getTarget());
                    i.putExtra("targetPic", newChat.getTargetPic());
                    startActivity(i);
                    chatCreated = false;
                } else listView.setAdapter(mViewModel.getAdapter(this.getContext()));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initViewModelData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SUCCESS_CREATE_CHAT && resultCode == Activity.RESULT_OK) {
            chatCreated = true;
            chatCreatedID = data.getStringExtra("chatID");
            initViewModelData();
        }
    }
}