package com.example.gescov.ViewLayer.SchoolClassroomList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gescov.R;
import com.example.gescov.ViewLayer.Exceptions.AdapterNotSetException;
import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolsCrontroller;

public class CreateClassroomFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_classroom_form);
        EditText classroomName = (EditText) findViewById(R.id.create_classroom_form_name_input);
        EditText classroomCapacity = (EditText) findViewById(R.id.create_classroom_form_capacity_input);
        EditText classroomRows = (EditText) findViewById(R.id.create_classroom_form_rows);
        EditText classroomColumns = (EditText) findViewById(R.id.create_classroom_form_columns);
        Button createClassroomButton = (Button) findViewById(R.id.create_school_form_create_button);

        createClassroomButton.setOnClickListener(e -> {
            SchoolClassroomsCrontroller classroomsCrontroller = PresentationControlFactory.getClassroomsCrontroller();
            classroomsCrontroller.createClassroom(classroomName.getText().toString(), classroomCapacity.getText().toString(), classroomRows.getText().toString(), classroomColumns.getText().toString());

            classroomsCrontroller.getClassroomListViewSchoolAdapter().notifyDataSetChanged();

            finish();
        });
    }
}