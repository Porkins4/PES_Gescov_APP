package com.example.gescov.ViewLayer.SchoolClassroomList;

import android.content.Intent;
import android.os.Bundle;

import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ListView;

import com.example.gescov.R;

public class SchoolClassromListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_school_classrom_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(e -> {
            Intent intent = new Intent(this, CreateClassroomFormActivity.class);
            startActivity(intent);
        });

        ListView list = findViewById(R.id.classrooms_list);
        SchoolClassroomsCrontroller controller = PresentationControlFactory.getClassroomsCrontroller();
        controller.setSchoolListViewAdapter(this);
        ClassroomListViewAdapter adapter = controller.getClassroomListViewSchoolAdapter();
        list.setAdapter(adapter);

        controller.refreshList();

    }
}