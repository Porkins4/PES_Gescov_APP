package com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassRecord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.example.gescov.R;
import com.example.gescov.ViewLayer.ViewLayerSingletons.PresentationControlFactory;

public class StudentsInClassRecordActivity extends AppCompatActivity {

    private ListView listView;
    private LinearLayout loadingComponents;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.class_record_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.class_record_activity_title);
        instance = this;
        initResponseListener();
    }

    private void initResponseListener() {
        studentsInClassRecordViewModel = new ViewModelProvider(this).get(StudentsInClassRecordViewModel.class);
        studentsInClassRecordViewModel.getStudents("5fa9d2d4e59d4c4c5d57151a", "03-02-2020").observe(this, new Observer<StudentsInClassRecordResult>() {
                    @Override
                    public void onChanged(StudentsInClassRecordResult studentsInClassRecordResult) {
                        if (!studentsInClassRecordResult.getError()) {
                            System.out.println("solo falta enseñar información");
                            loadingComponents.setVisibility(View.GONE);
                            adapter = new StudentsInClassRecordAdapter(instance, studentsInClassRecordResult.getStudentNamesAndPos());
                            listView.setAdapter(adapter);
                        }
                    }
                }
        );
    }
}