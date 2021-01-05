package com.example.gescov.viewlayer.forum;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.viewlayer.Exceptions.AdapterNotSetException;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class CreateForumEntryFormActivity extends AppCompatActivity {

    private CreateForumEntryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_forum_entry_form);
        EditText noticeText = findViewById(R.id.create_forum_entry_text_entry);
        EditText titleText = findViewById(R.id.create_forum_entry_text_title);
        Button button = findViewById(R.id.create_forum_entry_button);
        Spinner schoolSpinner = findViewById(R.id.create_forum_entry_school_spinner);
        viewModel = PresentationControlFactory.getSchoolsCrontroller().getCreateForumEntryViewModel();
        viewModel.getSchoolList().observe(this, list -> {
            refreshSpinnerValues(schoolSpinner);
        });

        refreshSpinnerValues(schoolSpinner);

        try {
            PresentationControlFactory.getSchoolsCrontroller().refreshAllSchoolsList();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (AdapterNotSetException e) {
            e.printStackTrace();
        }



        button.setOnClickListener(e -> {
            if (noticeText.getText() != null && !noticeText.getText().toString().equals("") && !schoolSpinner.getSelectedItem().toString().equals("") && titleText.getText() != null && !titleText.getText().toString().equals("")) {
                String schoolId = getSchoolIdByName(schoolSpinner.getSelectedItem().toString());
                if (schoolId != null) {
                    PresentationControlFactory.getForumController().createForumEntry(titleText.getText().toString(), noticeText.getText().toString(), schoolId);
                    finish();
                }
;            } else if (noticeText.getText() == null || noticeText.getText().toString().equals(""))
                Toast.makeText(this, getResources().getText(R.string.create_forum_check_content), Toast.LENGTH_SHORT).show();
            else if (titleText.getText() == null || titleText.getText().toString().equals(""))
                Toast.makeText(this, getResources().getText(R.string.create_forum_check_title), Toast.LENGTH_SHORT).show();
        });
    }

    private String getSchoolIdByName(String schoolName) {
        for (School school : viewModel.getSchoolList().getValue()) {
            if (school.getName().equals(schoolName))
                return school.getId();
        }
        return null;
    }

    private void refreshSpinnerValues(Spinner schoolSpinner) {
        List<School> list = viewModel.getSchoolList().getValue();
        List<String> namesList = new ArrayList<>();
        for (School school : list) {
            namesList.add(school.getName());
        }
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, namesList.toArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolSpinner.setAdapter(adapter);
    }
}