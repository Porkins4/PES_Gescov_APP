package com.example.gescov.ViewLayer.UpdateUserProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gescov.R;
import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;

public class UpdateUserProfileActivity extends AppCompatActivity {

    private Button student, teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile);
        initViewComponents();
    }

    private void initViewComponents() {
        student = (Button) findViewById(R.id.set_student_rol_button);
        teacher = (Button) findViewById(R.id.set_teacher_rol_button);
        setButtonListeners();
    }

    private void setButtonListeners() {
        student.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PresentationControlFactory.getUpdateUserProfileController().changeUserProfile("student"); //Aquí podríamos utilizar un enum
                    }
                }
        );

        teacher.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PresentationControlFactory.getUpdateUserProfileController().changeUserProfile("teacher"); //Aquí podríamos utilizar un enum
                    }
                }
        );
    }
}