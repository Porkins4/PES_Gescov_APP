package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.classroomSchedule;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    Boolean admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_schedule);
        admin  = true;
        if (getIntent().hasExtra("admin")) admin = getIntent().getExtras().getBoolean("admin");
        System.out.println(admin +" el admin");
        initComponents();
        getSchedule();
        setListenerMonday();
        setListenerTuesday();
        setListenerWednesday();
        setListenerThursday();
        setListenerFriday();
        setListenerSave();

    }

    private void getSchedule() {
        ScheduleViewModel scheduleViewModel = new  ViewModelProvider(this).get(ScheduleViewModel.class);
        scheduleViewModel.getSchedule(classID).observe(this, received -> {
            if ( received) {
                l1 = scheduleViewModel.getL1();
                l2 = scheduleViewModel.getL2();
                l3 = scheduleViewModel.getL3();
                l4 = scheduleViewModel.getL4();
                l5 = scheduleViewModel.getL5();
                adapter = new ScheduleAdapter(this,l1);
                adapter.sendTypeUser(admin);
                listView.setAdapter(adapter);
                getSchoolSubjects();

            }

        });

    }

    private void setListenerSave() {
        saveSchedule.setOnClickListener(v -> {
            setPreviousList(current);
            PresentationControlFactory.getScheduleController().setSchedule(classID,l1,l2,l3,l4,l5);
            Toast.makeText(this,R.string.sucessModifySchedule,Toast.LENGTH_SHORT).show();

        });
    }

    private void setListenerFriday() {
        friday.setOnClickListener(v -> {
            setPreviousList(current);
            current = 5;
            friday.setTextColor(getColor(R.color.normalText));
            adapter = new ScheduleAdapter(this,l5);
            adapter.sendTypeUser(admin);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });
    }

    private void setListenerMonday() {
        monday.setOnClickListener(v -> {
            setPreviousList(current);
            current = 1;
            monday.setTextColor(getColor(R.color.normalText));
            adapter = new ScheduleAdapter(this,l1);
            adapter.sendTypeUser(admin);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });

    }

    private void setListenerThursday() {
        thursday.setOnClickListener(v -> {
            setPreviousList(current);
            current = 4;
            thursday.setTextColor(getColor(R.color.normalText));
            adapter = new ScheduleAdapter(this,l4);
            adapter.sendTypeUser(admin);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });

    }



    private void setListenerWednesday() {
        wednesday.setOnClickListener(v -> {
            setPreviousList(current);
            current = 3;
            wednesday.setTextColor(getColor(R.color.normalText));
            adapter = new ScheduleAdapter(this,l3);
            adapter.sendTypeUser(admin);
            adapter.setSubjects(schoolSubjects);
            listView.setAdapter(adapter);
        });

    }

    private void setListenerTuesday() {
        tuesday.setOnClickListener(v -> {
            setPreviousList(current);
            current = 2;
            tuesday.setTextColor(getColor(R.color.normalText));
            adapter = new ScheduleAdapter(this,l2);
            adapter.sendTypeUser(admin);
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
                adapter.setSubjects(schoolSubjects);

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
        if ( admin) saveSchedule.setVisibility(View.VISIBLE);
        else saveSchedule.setVisibility(View.GONE);
        classID = getIntent().getStringExtra("classID");
        monday.setTextColor(getColor(R.color.normalText));
        initToolBar();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.schedule_title_activity);
    }

    private void setPreviousList(int current) {
        switch (current){
            case 1:  l1 = adapter.getSubjectsOfDay();
                     monday.setTextColor(getColor(R.color.dark_green));
                break;
            case 2:  l2 = adapter.getSubjectsOfDay();
                     tuesday.setTextColor(getColor(R.color.dark_green));
                break;
            case 3:  l3 = adapter.getSubjectsOfDay();
                     wednesday.setTextColor(getColor(R.color.dark_green));
                break;
            case 4:  l4 = adapter.getSubjectsOfDay();
                     thursday.setTextColor(getColor(R.color.dark_green));
                break;
            case 5:  l5 = adapter.getSubjectsOfDay();
                     friday.setTextColor(getColor(R.color.dark_green));
                break;
            default:
                System.out.println("not in range");
                break;
        }
    }
}