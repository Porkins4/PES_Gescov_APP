package com.example.gescov.ViewLayer.SchoolsAdministration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gescov.Singletons.ActualContext;
import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.R;


public class CreateSchoolFormActivity extends AppCompatActivity {
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_school_form);
        context = this;
        EditText schoolName = (EditText) findViewById(R.id.create_school_form_name_input);
        EditText schoolCode = (EditText) findViewById(R.id.create_school_form_code_input);
        Button createSchoolButton = (Button) findViewById(R.id.create_school_form_create_button);

        createSchoolButton.setOnClickListener(e -> {
            ISchoolsCrontroller schoolsCrontroller = PresentationControlFactory.getSchoolsCrontroller();
            ActualContext.setContext(context);
            schoolsCrontroller.createSchool1(schoolName.getText().toString(),schoolCode.getText().toString());
            SchoolListViewAdapter adapter = schoolsCrontroller.getSchoolListViewSchoolAdapter();
            adapter.addItem(schoolName.getText().toString());
            finish();
        });

    }
}