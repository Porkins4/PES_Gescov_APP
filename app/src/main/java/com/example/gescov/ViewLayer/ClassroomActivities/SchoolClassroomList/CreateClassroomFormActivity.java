package com.example.gescov.ViewLayer.ClassroomActivities.SchoolClassroomList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gescov.R;
import com.example.gescov.ViewLayer.ViewLayerSingletons.PresentationControlFactory;

public class CreateClassroomFormActivity extends AppCompatActivity {
    protected EditText classroomName;
    protected EditText classroomRows;
    protected EditText classroomColumns;
    protected Button createClassroomButton;
    protected Button deleteClassroomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_classroom_form);
        classroomName = (EditText) findViewById(R.id.create_classroom_form_name_input);
        classroomRows = (EditText) findViewById(R.id.create_classroom_form_rows);
        classroomColumns = (EditText) findViewById(R.id.create_classroom_form_columns);
        createClassroomButton = (Button) findViewById(R.id.create_classroom_form_create_button);
        deleteClassroomButton = (Button) findViewById(R.id.create_classroom_form_delete);

        createClassroomButton.setOnClickListener(e -> {
            setMainButtonActions();
        });
    }

    protected void setMainButtonActions() {
        SchoolClassroomsCrontroller classroomsCrontroller = PresentationControlFactory.getClassroomsCrontroller();
        classroomsCrontroller.createClassroom(classroomName.getText().toString(), Integer.valueOf(classroomRows.getText().toString()), Integer.valueOf(classroomColumns.getText().toString()));

        classroomsCrontroller.getClassroomListViewSchoolAdapter().notifyDataSetChanged();

        finish();
    }
}