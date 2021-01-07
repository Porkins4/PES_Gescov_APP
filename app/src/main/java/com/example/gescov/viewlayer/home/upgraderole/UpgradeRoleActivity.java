package com.example.gescov.viewlayer.home.upgraderole;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import androidx.appcompat.app.AppCompatActivity;

public class UpgradeRoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_role);
        EditText codeEntry = findViewById(R.id.role_promotion_code);
        Button requestButton = findViewById(R.id.role_promotion_button);

        requestButton.setOnClickListener( e-> {
            if (codeEntry.getText() != null && (codeEntry.getText().toString().equals("teacher") || codeEntry.getText().toString().equals("student")) ) {
                PresentationControlFactory.getLoadingProfileController().upgradeRole(codeEntry.getText().toString());
                finish();
            } else {
                PresentationControlFactory.getMessagesManager().toastMessage(R.string.code_not_correct);
            }
        });
    }
}