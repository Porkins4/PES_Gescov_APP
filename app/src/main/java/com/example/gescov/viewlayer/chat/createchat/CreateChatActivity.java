package com.example.gescov.viewlayer.chat.createchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.gescov.R;
import com.example.gescov.viewlayer.ClassroomActivities.ClassroomDistribution.ClassroomDsitributionViewModel;

public class CreateChatActivity extends AppCompatActivity {

    private CreateChatViewModel createChatViewModel;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat_acitvity);
        initViewComponents();
        initGetSchoolsListener();
    }

    private void initGetSchoolsListener() {
        createChatViewModel = new ViewModelProvider(this).get(CreateChatViewModel.class);
        createChatViewModel.getSchoolsRequest().observe(this,
                received -> {
                    if (received) setSchoolSpinner();
                }
        );
    }

    private void setSchoolSpinner() {
        ArrayAdapter<String> schoolNamesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, createChatViewModel.getSchoolNames());
        spinner.setAdapter(schoolNamesAdapter);
    }

    private void initViewComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_create_chat);
        spinner = (Spinner) findViewById(R.id.spinner);
    }
}