package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.createsubject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gescov.R;

public class CreateSubjectActivity extends AppCompatActivity {

    private Button createSubjectButton;
    private CreateSubjectViewModel viewModel;
    private TextView schoolName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subject);
        initComponents();
    }

    private void initComponents() {
        initViewComponents();
        initLogicalComponents();
    }

    private void initLogicalComponents() {
        viewModel = new ViewModelProvider(this).get(CreateSubjectViewModel.class);
        viewModel.setSchoolID(getIntent().getStringExtra("schoolID"));
    }

    private void initViewComponents() {
        initToolbar();
        initCreateSubjectButton();
        schoolName = (TextView) findViewById(R.id.subject_title);
    }

    private void initCreateSubjectButton() {
        createSubjectButton = (Button) findViewById(R.id.create_subject_butt);
        createSubjectButton.setOnClickListener(
                v -> {
                   createSubjectButton.setEnabled(false);
                   viewModel.createSchool(schoolName.getText().toString()).observe(this,
                           error -> {
                               if (error) Toast.makeText(this,R.string.general_error_message,Toast.LENGTH_SHORT).show();
                               else {
                                   Toast.makeText(this,R.string.succesfull_subject_creation,Toast.LENGTH_SHORT).show();
                                   finish();
                               }
                           });
                }
        );
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.create_subject_title);
    }
}