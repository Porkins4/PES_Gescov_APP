package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.classroomSchedule;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.SubjectViewModel;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.ArrayList;
import java.util.List;

public class InsertScheduleActivity extends AppCompatActivity {

    List<Subject> l1, l2, l3, l4, l5;
    List <Subject> schoolSubjects;
    Button monday, tuesday, wednesday, thursday, friday, saveSchedule;
    ListView listView;
    ScheduleAdapter adapter;
    int current;
    String classID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_schedule);
        initComponents();
        for (int i = 0; i < 12; ++i){
            Subject aux = new Subject("EMPTY");
            l1.add(aux);
            l2.add(aux);
            l3.add(aux);
            l4.add(aux);
            l5.add(aux);
        }
        getSchoolSubjects();
        setListenerMonday();
        setListenerTuesday();
        setListenerWednesday();
        setListenerThursday();
        setListenerFriday();
        setListenerSave();

    }

    private void setListenerSave() {
        saveSchedule.setOnClickListener(v -> {
            setPreviousList(current);
            PresentationControlFactory.getScheduleController().setSchedule(classID,l1,l2,l3,l4,l5);


        });
    }

    private void setListenerFriday() {
        monday.setOnClickListener(v -> {
            setPreviousList(current);
            current = 5;
            adapter = new ScheduleAdapter(this,l5);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });
    }

    private void setListenerThursday() {
        tuesday.setOnClickListener(v -> {
            setPreviousList(current);
            current = 4;
            adapter = new ScheduleAdapter(this,l4);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });

    }



    private void setListenerWednesday() {
        wednesday.setOnClickListener(v -> {
            setPreviousList(current);
            current = 3;
            adapter = new ScheduleAdapter(this,l3);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });

    }

    private void setListenerTuesday() {
        thursday.setOnClickListener(v -> {
            setPreviousList(current);
            current = 2;
            adapter = new ScheduleAdapter(this,l2);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });
    }

    private void setListenerMonday() {
        friday.setOnClickListener(v -> {
            setPreviousList(current);
            current = 1;
            adapter = new ScheduleAdapter(this,l1);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });

    }

    private void getSchoolSubjects() {
        SubjectViewModel subjectViewModel = new ViewModelProvider(this).get(SubjectViewModel.class);
        String schoolID = getIntent().getStringExtra("schoolID");
        subjectViewModel.getSubjects(schoolID).observe(this, received -> {
            if ( received) {
                schoolSubjects = subjectViewModel.getListOfSubjects();
                adapter = new ScheduleAdapter(this,l1);
                adapter.setSubjects(schoolSubjects);
                listView.setAdapter(adapter);
            }
        });
    }



    private void initComponents() {
        l1 = new ArrayList<>();
        l2 = new ArrayList<>();
        l3 = new ArrayList<>();
        l4 = new ArrayList<>();
        l5 = new ArrayList<>();
        current = 1;

        monday = findViewById(R.id.monday);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wedensday);
        thursday = findViewById(R.id.thursday);
        friday = findViewById(R.id.friday);
        listView = findViewById(R.id.schedule_list);
        saveSchedule = findViewById(R.id.save_schedule);
        classID = getIntent().getStringExtra("classID");
    }

    private void setPreviousList(int current) {
        switch (current){
            case 1:  l1 = adapter.getSubjectsOfDay();
                break;
            case 2:  l2 = adapter.getSubjectsOfDay();
                break;
            case 3:  l3 = adapter.getSubjectsOfDay();
                break;
            case 4:  l4 = adapter.getSubjectsOfDay();
                break;
            case 5:  l5 = adapter.getSubjectsOfDay();
                break;
            default:
                System.out.println("not in range");
                break;
        }
    }
}