package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.checkclasssession;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.gescov.R;

public class CheckClassSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_class_session);
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
        getSupportActionBar().setTitle(R.string.check_class_session_activity_title);
    }
}