package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.createsubject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

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
                           error ->
                           {
                               if (error) {
                                   if (viewModel.getResponseCode() == 500) {
                                       PresentationControlFactory.getMessagesManager().toastMessage(R.string.error_subject_already_exists);
                                   } else PresentationControlFactory.getMessagesManager().toastMessage(R.string.general_error_message);
                                   createSubjectButton.setEnabled(true);
                               } else {
                                   PresentationControlFactory.getMessagesManager().toastMessage(R.string.succesfull_subject_creation);
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