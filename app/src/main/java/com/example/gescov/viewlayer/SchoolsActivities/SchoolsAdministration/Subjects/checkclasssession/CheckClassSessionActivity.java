package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.checkclasssession;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.ClassroomActivities.ClassroomDistribution.ClassroomDistributionActivity;
import com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession.StudentsInClassSessionActivity;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

public class CheckClassSessionActivity extends AppCompatActivity {

    private CheckClassSessionViewModel viewModel;
    private TextView errorMessage;
    private LinearLayout error;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_class_session);
        initComponents();
    }

    private void initComponents() {
        initViewComponents();
        initModel();
        getClassSessions();
    }

    private void initModel() {
        viewModel = new ViewModelProvider(this).get(CheckClassSessionViewModel.class);
        String subjectID = getIntent().getStringExtra("subjectID");
        viewModel.init(subjectID);
    }

    private void getClassSessions() {
        viewModel.getClassSessions().observe(this,
                error -> {
                    if (!error) {
                        if (viewModel.classSessionIsEmpty()) showErrorUI(getString(R.string.error_subject_has_no_class_session));
                        else showClassSessions();
                    }
                    else showErrorUI(getString(R.string.general_error_message));
                });
    }

    private void showClassSessions() {
        listView.setAdapter(viewModel.getAdapter(this));
    }

    private void showErrorUI(String errorMessage) {
        this.errorMessage.setText(errorMessage);
        error.setVisibility(View.VISIBLE);
    }

    private void initViewComponents() {
        initToolbar();
        initListView();
        this.error = (LinearLayout) findViewById(R.id.error);
        this.errorMessage = (TextView) findViewById(R.id.error_message);
    }

    private void initListView() {
        this.listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(
                (parent, view, position, id) -> {
                    if (PresentationControlFactory.getUpdateUserProfileController().getUserType() == User.UserProfileType.TEACHER) {
                        Intent intent = new Intent(this, StudentsInClassSessionActivity.class);
                        intent.putExtra("classSession", viewModel.getClassroomSessionID(position));
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(this, ClassroomDistributionActivity.class);
                        intent.putExtra("classroom", viewModel.getClassroomID(position));
                        intent.putExtra("classSession", viewModel.getClassroomSessionID(position));
                        startActivity(intent);
                    }
                }
        );
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.check_class_session_activity_title);
    }
}