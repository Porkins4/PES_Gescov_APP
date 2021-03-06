package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.createclasssession;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gescov.R;

public class CreateClassSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class_session);
        initComponents();
    }

    private void initComponents() {
        initViewComponents();
    }

    private void initViewComponents() {
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.create_class_session_activity_title);
    }
}