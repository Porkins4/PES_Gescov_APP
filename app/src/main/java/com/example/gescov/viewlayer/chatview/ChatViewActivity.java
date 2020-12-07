package com.example.gescov.viewlayer.chatview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.gescov.R;

public class ChatViewActivity extends AppCompatActivity {

    private ChatViewViewModel chatViewViewModel;
    private RecyclerView recyclerView;
    private ChatViewActivity instance;
    private EditText editText;
    private ImageButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);
        initViewComponents();
        updateMessagesFromChat();
    }

    private void updateMessagesFromChat() {
        chatViewViewModel.updateChat().observe(this,
                new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean error) {
                        if (!error) {
                            MessageAdapter messageAdapter = chatViewViewModel.getAdapter(instance);
                            recyclerView.setAdapter(messageAdapter);
                            if (!messageAdapter.empty()) recyclerView.smoothScrollToPosition(chatViewViewModel.getLastElemPos());
                        }
                    }
                });
    }

    private void initViewComponents() {
        instance = this;
        initViewModel();
        initToolBar();
        initReciclerView();
        initSendMessageComponents();
    }

    private void initSendMessageComponents() {
        editText = (EditText) findViewById(R.id.edit_text);
        sendButton = (ImageButton) findViewById(R.id.send_message_button);
        initButtonListener();
    }

    private void initButtonListener() {
        sendButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message = editText.getText().toString();
                        if (!message.equals("")) {
                            chatViewViewModel.sendMessage(message);
                            editText.getText().clear();
                        }
                    }
                }
        );
    }

    private void initReciclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.messages_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initViewModel() {
        chatViewViewModel = new ViewModelProvider(this).get(ChatViewViewModel.class);
        chatViewViewModel.setChatID(getIntent().getExtras().getString("chatID"));
        chatViewViewModel.setUserInfo(getIntent().getExtras().getString("targetName"),getIntent().getExtras().getString("targetPic"));
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(chatViewViewModel.getUserName());
    }
}