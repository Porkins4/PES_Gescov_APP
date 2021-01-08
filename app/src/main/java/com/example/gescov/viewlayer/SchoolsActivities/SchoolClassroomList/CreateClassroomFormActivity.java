package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import androidx.appcompat.app.AppCompatActivity;

public class CreateClassroomFormActivity extends AppCompatActivity {
    protected EditText classroomName;
    protected EditText classroomRows;
    protected EditText classroomColumns;
    protected Button createClassroomButton;
    protected Button deleteClassroomButton;
    protected  Button insertSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_classroom_form);
        classroomName = (EditText) findViewById(R.id.create_classroom_form_name_input);
        classroomRows = (EditText) findViewById(R.id.create_classroom_form_rows);
        classroomColumns = (EditText) findViewById(R.id.create_classroom_form_columns);
        createClassroomButton = (Button) findViewById(R.id.create_classroom_form_create_button);
        deleteClassroomButton = (Button) findViewById(R.id.create_classroom_form_delete);
        insertSchedule = (Button) findViewById(R.id.insert_schedule);

        createClassroomButton.setOnClickListener(e -> {
            setMainButtonActions();
        });
    }

    protected void setMainButtonActions() {
        SchoolClassroomsCrontroller classroomsCrontroller = PresentationControlFactory.getClassroomsCrontroller();
        if (classroomRows.getText() == null || Integer.valueOf(classroomRows.getText().toString()) < 1) {
            PresentationControlFactory.getMessagesManager().toastMessage(R.string.classroom_rows_check);
        } else if (classroomColumns.getText() == null || Integer.valueOf(classroomColumns.getText().toString()) < 1) {
            PresentationControlFactory.getMessagesManager().toastMessage(R.string.classroom_columns_check);
        } else if (classroomName.getText() == null || Integer.valueOf(classroomName.getText().length()) < 1) {
            PresentationControlFactory.getMessagesManager().toastMessage(R.string.classroom_name_check);
        }
        classroomsCrontroller.createClassroom(classroomName.getText().toString(), Integer.valueOf(classroomRows.getText().toString()), Integer.valueOf(classroomColumns.getText().toString()));

        classroomsCrontroller.getListViewAdapter().notifyDataSetChanged();

        finish();
    }
}