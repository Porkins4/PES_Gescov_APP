package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

public class AccessSchoolByCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_school_by_code);
        EditText codeEntry = findViewById(R.id.access_school_by_code_entry);
        Button requestButton = findViewById(R.id.access_school_by_code_button);
        String schoolId = getIntent().getExtras().getString("schoolId");

        requestButton.setOnClickListener( e-> {
            User user = PresentationControlFactory.getViewLayerController().getLoggedUserInfo();
            PresentationControlFactory.getSchoolsCrontroller().requestAcessSchoolByCode(user.getId(), schoolId, codeEntry.getText().toString());
            finish();
        });
    }
}