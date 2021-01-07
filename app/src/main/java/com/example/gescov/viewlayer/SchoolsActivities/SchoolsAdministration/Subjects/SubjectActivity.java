package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.createsubject.CreateSubjectActivity;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SubjectActivity extends AppCompatActivity {

    private String schooldID;
    ListView listView;
    private FloatingActionButton createSubjectButton;
    private SubjectViewModel subjectViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        schooldID = getIntent().getStringExtra("schoolID");
        listView = findViewById(R.id.subject_list_view);
        subjectViewModel = new ViewModelProvider(this).get(SubjectViewModel.class);
        initViewComponents();
        setObeserver(schooldID);
    }

    private boolean isUserAdmin(User user) {
        School school = PresentationControlFactory.getSchoolsCrontroller().getCurrentSchool();
        return school.getAdministratorsList().contains(user.getId());
    }

    private void initViewComponents() {
        initToolbar();
        initCreateSubjectButton();
    }

    private void initCreateSubjectButton() {
        createSubjectButton = findViewById(R.id.create_subject_button);
        if (isUserAdmin(subjectViewModel.getLoggedInUser())) {
            createSubjectButton.setOnClickListener(
                    v -> {
                        Intent i = new Intent(this, CreateSubjectActivity.class);
                        i.putExtra("schoolID",getIntent().getStringExtra("schoolID"));
                        startActivity(i);
                    }
            );
        } else createSubjectButton.setVisibility(View.GONE);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.subjects_activity_title);
    }

    private void setObeserver(String schooldID) {
        subjectViewModel.getSubjects(schooldID).observe(this, received -> {
            if ( Boolean.TRUE.equals(received) ) {
                listView.setAdapter(subjectViewModel.getAdapter(this));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setObeserver(schooldID);
    }
}