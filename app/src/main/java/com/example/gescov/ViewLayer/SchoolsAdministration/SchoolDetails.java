package com.example.gescov.ViewLayer.SchoolsAdministration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.R;
import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolClassroomList.SchoolClassromListActivity;

public class SchoolDetails extends AppCompatActivity {
    private SchoolsCrontroller controller;
    private School school;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_details);
        controller = PresentationControlFactory.getSchoolsCrontroller();
        school = controller.getCurrentSchool();

        TextView name = (TextView) findViewById(R.id.school_details_name);
        TextView address = (TextView) findViewById(R.id.school_details_address);
        TextView telf = (TextView) findViewById(R.id.school_details_telf);
        TextView webpage = (TextView) findViewById(R.id.school_details_webpage);
        TextView email = (TextView) findViewById(R.id.school__details_email);
        Button protocolsButton = (Button) findViewById(R.id.school_details_protocol_button);
        Button classroomsListButton = (Button) findViewById(R.id.school_details_classroom_button);
        Button deleteButton = (Button) findViewById(R.id.school_details_delete);

        name.setText(school.getName());

        setVisibilityAndValue(address, school.getAddress());
        setVisibilityAndValue(telf, school.getTelephoneNumber());
        setVisibilityAndValue(webpage, school.getWebpage());
        setVisibilityAndValue(email, school.getEmail());

        deleteButton.setOnClickListener(e -> {
            controller.deleteSchool(school);
            finish();
        });

        classroomsListButton.setOnClickListener(e-> {
            Intent intent = new Intent(this, SchoolClassromListActivity.class);
            startActivity(intent);
        });

    }

    private void setVisibilityAndValue(TextView view, String value) {
        if (value == null) {
            view.setVisibility(View.INVISIBLE);
        } else {
            view.setVisibility(View.VISIBLE);
            view.setText(value);
        }
    }
}