package com.example.gescov.ViewLayer.SchoolsActivities.SchoolsAdministration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;
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
             if (validateSchoolName(schoolName.getText()) && validateAddress(schoolAddress.getText()) && validateTelephone(schoolPhone.getText()) && validateWeb(schoolWeb.getText())) {
                 schoolsCrontroller.createSchool(schoolName.getText().toString(), schoolAddress.getText().toString(), schoolPhone.getText().toString(), schoolWeb.getText().toString());
                 finish();
             } else {
                 Toast.makeText(this, getResources().getText(R.string.create_school_validate_fields), Toast.LENGTH_SHORT).show();
             }
        });
    }

    private boolean validateWeb(Editable text) {
        boolean b = (text == null || (text.toString().length() > 8 && (text.toString().substring(0,7).equals("http://") || text.toString().substring(0,8).equals("https://"))));
        return b;
    }

    private boolean validateSchoolName(Editable text) {
        return (text != null);
    }

    private boolean validateTelephone(Editable text) {
        return (text == null || text.toString().length() == 9);
    }

    private boolean validateAddress(Editable text) {
        return (text == null || text.toString().length() > 0);
    }


}