package com.example.gescov.viewlayer.chat.chatview;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gescov.R;

public class ChatViewActivity extends AppCompatActivity {

    private ChatViewViewModel chatViewViewModel;
    private RecyclerView recyclerView;
    private ChatViewActivity instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);
        initViewModel();
        initViewComponents();
        startChat();
    }


    private void initViewModel() {
        chatViewViewModel = new ViewModelProvider(this).get(ChatViewViewModel.class);
        chatViewViewModel.setUserInfo(getIntent().getExtras().getString("targetName"), getIntent().getExtras().getString("targetPic"), getIntent().getExtras().getString("chatID"));
    }


    private void initViewComponents() {
        instance = this;
        initToolBar();
        initReciclerView();
        initSendMessageComponents();
    }


    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(chatViewViewModel.getUserName());
    }


    private void initReciclerView() {
        recyclerView = findViewById(R.id.messages_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    private void initSendMessageComponents() {
        EditText editText = findViewById(R.id.edit_text);
        ImageButton sendButton = findViewById(R.id.send_message_button);
        sendButton.setOnClickListener(
                v -> {
                    String message = editText.getText().toString();
                    if (!message.equals("")) {
                        chatViewViewModel.sendMessage(message);
                        editText.getText().clear();
                    }
                }
        );
    }

    private void startChat() {
        chatViewViewModel.getMessages().observe(this,
                error -> {
                    if(!error) {
                        MessageAdapter messageAdapter = chatViewViewModel.getAdapter(instance);
                        recyclerView.setAdapter(messageAdapter);
                        startPolling();
                    }
                });
    }

    private void startPolling() {
        chatViewViewModel.startPolling().observe(this,
                aBoolean -> {
                    MessageAdapter messageAdapter = chatViewViewModel.getAdapter(instance);
                    recyclerView.setAdapter(messageAdapter);
                });
    }


    @Override
    public void onBackPressed() {
        chatViewViewModel.deactivatePolling();
        super.onBackPressed();
    }
}