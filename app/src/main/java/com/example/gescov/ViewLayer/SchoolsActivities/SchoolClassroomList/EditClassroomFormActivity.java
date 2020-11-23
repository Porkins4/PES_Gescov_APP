package com.example.gescov.ViewLayer.SchoolsActivities.SchoolClassroomList;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gescov.DomainLayer.Classmodels.Classroom;
import com.example.gescov.R;
import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;

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
        fillFields();

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
