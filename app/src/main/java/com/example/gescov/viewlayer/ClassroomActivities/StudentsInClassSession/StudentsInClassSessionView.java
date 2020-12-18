package com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassSession;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentsInClassSessionView extends AppCompatActivity {

    private ListView listView;
    private Context context;
    private StudentsInClassSessionAdapter adapter;
    private ProgressBar progressBar;
    private TextView loadingTextView;
    private StudentsInClassSessionViewModel studentsInClassSessionViewModel;

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
    }

    private void initPrivateAttribs() {
        context = this;
        listView = (ListView) findViewById(R.id.list_view_students_in_class_session);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_students_in_class_session);
        loadingTextView = (TextView) findViewById(R.id.loading_text_view_students_in_class_session);

        loadingTextView.setText("cargando...");
    }

    private void setResponseListener() {
        studentsInClassSessionViewModel = new ViewModelProvider(this).get(StudentsInClassSessionViewModel.class);
        studentsInClassSessionViewModel.getStudents().observe(this, studentNames -> {
            adapter = new StudentsInClassSessionAdapter(context,studentNames.getStudentNames());
            listView.setAdapter(adapter);
            loadingTextView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        });
    }

    private void initUpdateButton() {
        FloatingActionButton updateButton = (FloatingActionButton) findViewById(R.id.update_button_students_in_class_session);
        updateButton.setOnClickListener(
                v -> {
                    //------------------------
                    // for the ui update
                    studentsInClassSessionViewModel.clearDataset();
                    adapter.notifyDataSetChanged();
                    loadingTextView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    //------------------------

                    studentsInClassSessionViewModel.update();
                }
        );
    }


}