package com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassRecord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.example.gescov.R;

public class StudentsInClassRecordActivity extends AppCompatActivity {

    private ListView listView;
    private LinearLayout loadingComponents, errorComponents;
    private StudentsInClassRecordViewModel studentsInClassRecordViewModel;
    private StudentsInClassRecordAdapter adapter;
    private StudentsInClassRecordActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_in_class_record);
        initViewComponents();
    }

    private void initViewComponents() {
        listView = (ListView) findViewById(R.id.students_in_class_record_list_view);
        loadingComponents = (LinearLayout) findViewById(R.id.students_in_class_record_loading_components);
        errorComponents = (LinearLayout) findViewById(R.id.students_in_class_record_error);
        errorComponents.setVisibility(View.GONE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.class_record_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.class_record_activity_title);
        instance = this;
        initResponseListener();
    }

    private void initResponseListener() {
        studentsInClassRecordViewModel = new ViewModelProvider(this).get(StudentsInClassRecordViewModel.class);
        studentsInClassRecordViewModel.getStudents(getIntent().getStringExtra("classroom"), "03-02-2020").observe(this, new Observer<StudentsInClassRecordResult>() { //de momento fecha hardcoded
                    @Override
                    public void onChanged(StudentsInClassRecordResult studentsInClassRecordResult) {
                        System.out.println("entro");
                        if (!studentsInClassRecordResult.getError()) {
                            System.out.println("solo falta enseñar información");
                            loadingComponents.setVisibility(View.GONE);
                            adapter = new StudentsInClassRecordAdapter(instance, studentsInClassRecordResult.getStudentNamesAndPos(),instance);
                            listView.setAdapter(adapter);
                        } else if(studentsInClassRecordResult.getError()) {
                            loadingComponents.setVisibility(View.GONE);
                            errorComponents.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );
    }
}