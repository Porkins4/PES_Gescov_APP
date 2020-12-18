package com.example.gescov.viewlayer.chat.createchat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;

public class CreatingChatActivity extends AppCompatActivity {

    private CreateChatViewModel createChatViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_chat);
        initViewComponents();
        initListener();
    }

    private void initListener() {
        createChatViewModel = new ViewModelProvider(this).get(CreateChatViewModel.class);
        createChatViewModel.setTarget(getIntent().getExtras().getString("targetName"),getIntent().getExtras().getString("targetID"));
        createChatViewModel.sendChatRequest().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean error) {
                Intent resultIntent = new Intent();
                if (!error) {
                    resultIntent.putExtra("chatID",createChatViewModel.getChatID());
                    setResult(RESULT_OK,resultIntent);
                } else setResult(RESULT_CANCELED, resultIntent);
                finish();
            }
        });
    }

    private void initViewComponents() {
        TextView tv = (TextView) findViewById(R.id.loading_text);
        tv.setText(getString(R.string.creating_chat_text));
    }
}