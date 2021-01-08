package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gescov.GescovUtils;
import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SchoolClassromListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_school_classrom_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String schoolId = getIntent().getStringExtra("schooldID");

        FloatingActionButton fab = findViewById(R.id.fab);
        if (GescovUtils.isUserSchoolAdmin(PresentationControlFactory.getLoadingProfileController().getLoggedInUser(), PresentationControlFactory.getSchoolsCrontroller().getSchoolById(schoolId)))
            fab.setOnClickListener(e -> {
                Intent intent = new Intent(this, CreateClassroomFormActivity.class);
                startActivity(intent);
            });
        else
            fab.setVisibility(View.INVISIBLE);

        ListView list = findViewById(R.id.classrooms_list);
        SchoolClassroomsCrontroller controller = PresentationControlFactory.getClassroomsCrontroller();
        controller.setListViewAdapter(this);
        ClassroomListViewAdapter adapter = controller.getListViewAdapter();
        adapter.setSchoolID(schoolId);
        adapter.setIfAdmin(getIntent().getExtras().getBoolean("admin"));
        list.setAdapter(adapter);
        controller.refreshList();
    }
}