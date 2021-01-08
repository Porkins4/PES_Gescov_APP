package com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentsInClassSessionActivity extends AppCompatActivity {

    private ListView listView;
    private Context context;
    private StudentsInClassSessionAdapter adapter;
    private ProgressBar progressBar;
    private TextView loadingTextView;
    private StudentsInClassSessionViewModel viewModel;
    private LinearLayout error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_in_class_session_view);
        initView();
    }

    private void initView() {
        initPrivateAttribs();
        setResponseListener();
        initUpdateButton();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_assignments_class_session);
    }

    private void initPrivateAttribs() {
        context = this;
        listView = (ListView) findViewById(R.id.list_view_students_in_class_session);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_students_in_class_session);
        loadingTextView = (TextView) findViewById(R.id.loading_text_view_students_in_class_session);
        viewModel = new ViewModelProvider(this).get(StudentsInClassSessionViewModel.class);
        viewModel.init(getIntent().getStringExtra("classSession"));
        loadingTextView.setText("cargando...");
        error = findViewById(R.id.error);
    }

    private void setResponseListener() {
        viewModel.getStudents().observe(this,
                studentNames -> {
                    adapter = new StudentsInClassSessionAdapter(context,studentNames.getStudentNames());
                    listView.setAdapter(adapter);
                    if (studentNames.getStudentNames().isEmpty()) showErrorUI();
                    else error.setVisibility(View.INVISIBLE);
                    loadingTextView.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                });
    }

    private void showErrorUI() {
        error.setVisibility(View.VISIBLE);
        TextView errorMessage = findViewById(R.id.error_message);
        errorMessage.setText(R.string.error_no_assistants_to_class_session);
    }

    private void initUpdateButton() {
        FloatingActionButton updateButton = findViewById(R.id.update_button_students_in_class_session);
        updateButton.setOnClickListener(
                v -> {
                    error.setVisibility(View.INVISIBLE);
                    viewModel.clearDataset();
                    adapter.notifyDataSetChanged();
                    loadingTextView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    viewModel.update();
                }
        );
    }
}