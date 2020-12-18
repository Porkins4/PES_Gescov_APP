package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

public class SubjectDetailActivity extends AppCompatActivity {

    private User loggedUser;
    Button addStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);
        loggedUser = PresentationControlFactory.getViewLayerController().getLoggedUserInfo();
        setComponents();
        String subjectID = getIntent().getStringExtra("subjectID");
        setStudentToSubject(subjectID);
    }

    private void setComponents() {
        String subjectName = getIntent().getStringExtra("subjectName");
        TextView subName = findViewById(R.id.subject_text);
        subName.setText(subjectName);
        Button addTeacher = findViewById(R.id.add_teacher_to_subject);
        addStudent = findViewById(R.id.assign_student);
        addTeacher.setVisibility(loggedUser.getProfileType().equals(User.UserProfileType.TEACHER) ? View.VISIBLE : View.GONE);
        addStudent.setVisibility(loggedUser.getProfileType().equals(User.UserProfileType.TEACHER) ? View.GONE : View.VISIBLE);
    }

    private void setStudentToSubject(String subjectID) {
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
}