package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import androidx.appcompat.app.AppCompatActivity;

public class AccessSchoolByCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_school_by_code);
        EditText codeEntry = findViewById(R.id.access_school_by_code_entry);
        Button requestButton = findViewById(R.id.access_school_by_code_button);

        requestButton.setOnClickListener( e-> {
            User user = PresentationControlFactory.getViewLayerController().getLoggedUserInfo();
            School school = PresentationControlFactory.getSchoolsCrontroller().getCurrentSchool();
            PresentationControlFactory.getSchoolsCrontroller().requestAcessSchoolByCode(user.getId(), school.getId(), codeEntry.getText().toString());
            finish();
        });
    }
}