package com.example.gescov.SchoolsAdministration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gescov.PresentationControlFactory;
import com.example.gescov.R;

import java.util.List;


public class CreateSchoolFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_school_form);
        EditText schoolName = (EditText) findViewById(R.id.create_school_form_name_input);
        EditText schoolCode = (EditText) findViewById(R.id.create_school_form_code_input);
        Button createSchoolButton = (Button) findViewById(R.id.create_school_form_create_button);

        createSchoolButton.setOnClickListener(e -> {
            ISchoolsCrontroller schoolsCrontroller = PresentationControlFactory.getSchoolsCrontroller();
            SchoolListViewAdapter adapter = schoolsCrontroller.getSchoolListViewSchoolAdapter();
            adapter.addItem(schoolName.getText().toString());
            finish();
        });

    }
}