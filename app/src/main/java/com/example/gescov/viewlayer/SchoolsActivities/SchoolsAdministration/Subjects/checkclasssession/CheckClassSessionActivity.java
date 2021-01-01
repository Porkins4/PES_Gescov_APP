package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.checkclasssession;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;

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
                        showClassSessions();
                    } else {
                        showErrorUI();
                    }
                });
    }

    private void showClassSessions() {
        listView.setAdapter(viewModel.getAdapter(this));
    }

    private void showErrorUI() {
        errorMessage.setText(getString(R.string.general_error_message));
        error.setVisibility(View.VISIBLE);
    }

    private void initViewComponents() {
        initToolbar();
        this.listView = (ListView) findViewById(R.id.list_view);
        this.error = (LinearLayout) findViewById(R.id.error);
        this.errorMessage = (TextView) findViewById(R.id.error_message);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.check_class_session_activity_title);
    }
}