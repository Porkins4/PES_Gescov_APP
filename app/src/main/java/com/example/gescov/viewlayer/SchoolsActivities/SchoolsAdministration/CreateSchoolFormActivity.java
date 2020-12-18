package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Singletons.ServicesFactory;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;


public class CreateSchoolFormActivity extends AppCompatActivity {

    SchoolCreateFormViewModel viewModel;
    TextView latitudeTextView;
    TextView longitudeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_school_form);
        EditText schoolName = (EditText) findViewById(R.id.create_school_form_name_input);
        EditText schoolAddress = (EditText) findViewById(R.id.create_school_form_address_input);
        EditText schoolPhone = (EditText) findViewById(R.id.create_school_form_phone_input);
        EditText schoolWeb = (EditText) findViewById(R.id.create_school_form_web_input);
        Button createSchoolButton = (Button) findViewById(R.id.create_school_form_create_button);

        schoolAddress.setOnFocusChangeListener((v, isFocused) -> {
            if (!isFocused && schoolAddress.getText() != null)
                ServicesFactory.getRefreshCoordinatesFromAddressResponseController().refreshCoordinates(schoolAddress.getText().toString());
        });

        latitudeTextView = findViewById(R.id.create_school_form_address_latitude);
        longitudeTextView = findViewById(R.id.create_school_form_address_longitude);

        viewModel = PresentationControlFactory.getSchoolsCrontroller().getSchoolCreateFormViewModel();

        createSchoolButton.setOnClickListener(e -> {
            SchoolsCrontroller schoolsCrontroller = PresentationControlFactory.getSchoolsCrontroller();
             if (validateSchoolName(schoolName.getText()) && validateAddress(schoolAddress.getText()) && validateTelephone(schoolPhone.getText()) && validateWeb(schoolWeb.getText())) {
                 schoolsCrontroller.createSchool(schoolName.getText().toString(), schoolAddress.getText().toString(), schoolPhone.getText().toString(), schoolWeb.getText().toString(), viewModel.getLatitude().getValue(), viewModel.getLongitude().getValue());
                 finish();
             } else {
                 Toast.makeText(this, getResources().getText(R.string.create_school_validate_fields), Toast.LENGTH_SHORT).show();
             }
        });
        initViewModelListeners();
    }

    private void initViewModelListeners() {
        viewModel.getLatitude().observe((LifecycleOwner) this, e -> {
                    latitudeTextView.setText(getResources().getText(R.string.create_school_form_latitude) + " " + viewModel.getLatitude().getValue());
                }
        );

        viewModel.getLongitude().observe((LifecycleOwner) this, e -> {
                    longitudeTextView.setText(getResources().getText(R.string.create_school_form_longitude) + " " + viewModel.getLongitude().getValue());
                }
        );
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