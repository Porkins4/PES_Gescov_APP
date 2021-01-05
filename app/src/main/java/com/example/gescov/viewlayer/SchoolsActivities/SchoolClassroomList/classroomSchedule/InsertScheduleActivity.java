package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.classroomSchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.SubjectViewModel;
import com.example.gescov.viewlayer.ranking.RankingViewModel;

import java.util.ArrayList;
import java.util.List;

public class InsertScheduleActivity extends AppCompatActivity {

    List<String> l1;
    List<String> l2;
    List<String> l3;
    List<String> l4;
    List<String> l5;
    List <Subject> schoolSubjects;
    Button monday;
    Button tuesday;
    Button wednesday;
    Button thursday;
    Button friday;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_schedule);
        initComponents();
        for (int i = 0; i < 12; ++i) l1.add("EMPTY");
        getSchoolSubjects();
        ScheduleAdapter adapter = new ScheduleAdapter(this,l1);
        adapter.setSubjects(schoolSubjects);
        listView.setAdapter(adapter);
        setListenerMonday();
        setListenerTuesday();
        setListenerWednesday();
        setListenerThursday();
        setListenerFriday();

    }

    private void setListenerFriday() {
        monday.setOnClickListener(v -> {
            ScheduleAdapter adapter = new ScheduleAdapter(this,l5);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });
    }

    private void setListenerThursday() {
        tuesday.setOnClickListener(v -> {
            ScheduleAdapter adapter = new ScheduleAdapter(this,l4);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });

    }

    private void setListenerWednesday() {
        wednesday.setOnClickListener(v -> {
            ScheduleAdapter adapter = new ScheduleAdapter(this,l3);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });

    }

    private void setListenerTuesday() {
        thursday.setOnClickListener(v -> {
            ScheduleAdapter adapter = new ScheduleAdapter(this,l2);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });
    }

    private void setListenerMonday() {
        friday.setOnClickListener(v -> {
            ScheduleAdapter adapter = new ScheduleAdapter(this,l1);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });

    }

    private void getSchoolSubjects() {
        SubjectViewModel subjectViewModel = new ViewModelProvider(this).get(SubjectViewModel.class);
        String schoolID = getIntent().getStringExtra("schoolID");
        System.out.println(schoolID + ":)))))))))");
        subjectViewModel.getSubjects(schoolID).observe(this, received -> {
            if ( received) {

                schoolSubjects = subjectViewModel.getListOfSubjects();
                System.out.println(schoolSubjects.size() + " -----------");
            }
        });
    }



    private void initComponents() {
        l1 = l2 = l3 = l4 = l5 = new ArrayList<>();
        monday = findViewById(R.id.monday);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wedensday);
        thursday = findViewById(R.id.thursday);
        friday = findViewById(R.id.friday);
        listView = findViewById(R.id.schedule_list);
    }
}