package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ListView;

import com.example.gescov.R;

public class SubjectActivity extends AppCompatActivity {

     ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        String schooldID = getIntent().getStringExtra("schoolID");
        listView = (ListView) findViewById(R.id.subject_list_view);
        setObeserver(schooldID);
        initViewComponents();
    }

    private void initViewComponents() {
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.subjects_activity_title);
    }

    private void setObeserver(String schooldID) {
        SubjectViewModel subjectViewModel = new ViewModelProvider(this).get(SubjectViewModel.class);
        subjectViewModel.getSubjects(schooldID).observe(this, received -> {
            if ( Boolean.TRUE.equals(received) ) {
                listView.setAdapter(subjectViewModel.getAdapter(this));
            }
        });
    }
}