package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.Classroom;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.classroomSchedule.InsertScheduleActivity;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

public class EditClassroomFormActivity extends CreateClassroomFormActivity {


    private Classroom classroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getIntent().getIntExtra("classroom_position", 0 );
        classroom = PresentationControlFactory.getClassroomsCrontroller().getClassroomByListPosition(position);
        deleteClassroomButton.setVisibility(View.VISIBLE);
        deleteClassroomButton.setOnClickListener(e -> {
            SchoolClassroomsCrontroller classroomsCrontroller = PresentationControlFactory.getClassroomsCrontroller();
            classroomsCrontroller.deleteClassroom(classroom.getId());
            finish();
        });

        setScheduleListener(insertSchedule);
        fillFields();



    }

    private void setScheduleListener(Button insertSchedule) {
        insertSchedule.setOnClickListener(v -> {
            Intent intent = new Intent(this, InsertScheduleActivity.class);
            intent.putExtra("schoolID",getIntent().getStringExtra("schoolID"));
            intent.putExtra("classID",classroom.getId());
            startActivity(intent);
        });

    }

    private void fillFields() {
        createClassroomButton.setText(getResources().getText(R.string.update));
        classroomName.setText(classroom.getName());
        classroomRows = (EditText) findViewById(R.id.create_classroom_form_rows);
        classroomColumns = (EditText) findViewById(R.id.create_classroom_form_columns);
    }

    @Override
    protected void setMainButtonActions() {
        SchoolClassroomsCrontroller classroomsCrontroller = PresentationControlFactory.getClassroomsCrontroller();
        classroomsCrontroller.updateClassroom(classroom.getId(), classroomName.getText().toString(), Integer.valueOf(classroomRows.getText().toString()), Integer.valueOf(classroomColumns.getText().toString()));
        finish();
    }
}
