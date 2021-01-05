package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.addteachertosubject.AddTeacherToSubjectActivity;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.checkclasssession.CheckClassSessionActivity;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.event.EventActivity;

public class SubjectDetailActivity extends AppCompatActivity {

    private User loggedUser;
    private Button addStudent;
    private Button addTeacher;
    private Button createClassSession;
    private Button checkClassSessions;
    private String subjectID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);
        loggedUser = PresentationControlFactory.getViewLayerController().getLoggedUserInfo();
        setComponents();
        subjectID = getIntent().getStringExtra("subjectID");

    }

    private void setComponents() {
        initToolbar();
        setSubjectName();
        initButtons();
    }

    private void initButtons() {
        addTeacher = findViewById(R.id.add_teacher_to_subject);
        addStudent = findViewById(R.id.assign_student);
        checkClassSessions = findViewById(R.id.check_class_session_button);
        createClassSession = findViewById(R.id.create_class_session_button);
        listenerStudentToSubject();
        listenerTeacherToSubject();
        listenerCheckClassSessions();
        listenerCreateClassSession();

        if (loggedUser.getProfileType().equals(User.UserProfileType.TEACHER)) {
            addStudent.setVisibility(View.GONE);
        } else {
            createClassSession.setVisibility(View.GONE);
            addTeacher.setVisibility(View.GONE);
        }
    }

    private void listenerCreateClassSession() {
        createClassSession.setOnClickListener(
                v -> {
                    Intent i = new Intent(this, EventActivity.class);
                    i.putExtra("subjectID",subjectID);
                    startActivity(i);
                }
        );
    }

    private void listenerCheckClassSessions() {
        checkClassSessions.setOnClickListener(
                v -> {
                    Intent i = new Intent(this, CheckClassSessionActivity.class);
                    i.putExtra("subjectID",subjectID);
                    startActivity(i);
                }
        );
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.subjects_details_activity_title);
    }

    private void setSubjectName() {
        String subjectName = getIntent().getStringExtra("subjectName");
        TextView subName = findViewById(R.id.subject_text);
        subName.setText(subjectName);
    }

    private void listenerStudentToSubject() {
        addStudent.setOnClickListener(v -> {
            SubjectViewModel subjectViewModel = new ViewModelProvider(this).get(SubjectViewModel.class);
            subjectViewModel.assignStudent(subjectID).observe(this, error -> {
                if ( ! error ) {
                    addStudent.setEnabled(false);
                    Toast.makeText(getApplicationContext(),R.string.assigned_successfuly,Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void listenerTeacherToSubject() {
        addTeacher.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddTeacherToSubjectActivity.class);
            intent.putExtra("subjectID", subjectID);
            intent.putExtra("schoolID", getIntent().getExtras().getString("schoolID"));
            startActivity(intent);
        });
    }
}