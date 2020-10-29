package com.example.gescov.ViewLayer.SchoolClassroomList;

import android.os.Bundle;

import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolListViewAdapter;
import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolsCrontroller;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListView list = (ListView) findViewById(R.id.classrooms_list);
        SchoolClassroomsCrontroller controller = PresentationControlFactory.getClassroomsCrontroller();
        controller.setSchoolListViewAdapter(list.getContext());
        ClassroomListViewAdapter adapter = controller.getClassroomListViewSchoolAdapter();
        list.setAdapter(adapter);
    }
}