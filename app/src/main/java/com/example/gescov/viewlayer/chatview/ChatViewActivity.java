package com.example.gescov.viewlayer.chatview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gescov.R;
import com.example.gescov.viewlayer.chat.createchat.CreateChatViewModel;

public class ChatViewActivity extends AppCompatActivity {

    private ChatViewViewModel chatViewViewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);
        initViewComponents();
    }

    private void initViewComponents() {
        chatViewViewModel = new ViewModelProvider(this).get(ChatViewViewModel.class);
        chatViewViewModel.setUserInfo(getIntent().getExtras().getString("targetName"),getIntent().getExtras().getString("targetID"));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(chatViewViewModel.getUserName());
        recyclerView = (RecyclerView) findViewById(R.id.messages_recycler);

    }
}