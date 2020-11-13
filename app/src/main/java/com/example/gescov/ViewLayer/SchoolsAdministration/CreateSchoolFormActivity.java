package com.example.gescov.ViewLayer.SchoolsAdministration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gescov.ViewLayer.Exceptions.AdapterNotSetException;
import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.R;


public class CreateSchoolFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_school_form);
        EditText schoolName = (EditText) findViewById(R.id.create_school_form_name_input);
        EditText schoolAddress = (EditText) findViewById(R.id.create_school_form_address_input);
        EditText schoolPhone = (EditText) findViewById(R.id.create_school_form_phone_input);
        EditText schoolWeb = (EditText) findViewById(R.id.create_school_form_web_input);
        Button createSchoolButton = (Button) findViewById(R.id.create_school_form_create_button);

        createSchoolButton.setOnClickListener(e -> {
            SchoolsCrontroller schoolsCrontroller = PresentationControlFactory.getSchoolsCrontroller();
            schoolsCrontroller.createSchool(schoolName.getText().toString(), schoolAddress.getText().toString(), schoolPhone.getText().toString(), schoolWeb.getText().toString());
            try {
                schoolsCrontroller.getSchoolListViewAdapter().notifyDataSetChanged();
            } catch (AdapterNotSetException adapterNotSetException) {
                adapterNotSetException.printStackTrace();
            }
            finish();
        });
    }
}