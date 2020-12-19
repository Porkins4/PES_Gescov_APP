package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.addteachertosubject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gescov.R;
import com.example.gescov.viewlayer.chat.chatview.ChatViewActivity;

public class AddTeacherToSubjectActivity extends AppCompatActivity {

    private ListView listView;
    private AddTeacherToSubjectViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_to_subject);
        initComponents();
        loadTeacherList();
    }

    private void loadTeacherList() {
        viewModel.getTeacherList(getIntent().getStringExtra("schoolID")).observe(this,
                received -> {
                    if (received) {
                        listView.setAdapter(viewModel.getAdapter(this));
                    }
                });
    }

    private void initComponents() {
        viewModel = new ViewModelProvider(this).get(AddTeacherToSubjectViewModel.class);
        initListView();
        initToolbar();
    }

    private void initListView() {
        listView = findViewById(R.id.teacher_list);
        listView.setOnItemClickListener(
                (parent, view, position, id) -> {
                    viewModel.assignTeacherToSubject(position,getIntent().getStringExtra("subjectID")).observe(this,
                            error -> {
                                if (error) Toast.makeText(this,R.string.teacher_not_added,Toast.LENGTH_SHORT).show();
                                else {
                                    Toast.makeText(this,R.string.teacher_added_succesfully,Toast.LENGTH_SHORT).show();
                                    //viewModel.deleteIthTeacher(position);s
                                }
                            });
                }
        );
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_add_teacher_subject);
    }
}