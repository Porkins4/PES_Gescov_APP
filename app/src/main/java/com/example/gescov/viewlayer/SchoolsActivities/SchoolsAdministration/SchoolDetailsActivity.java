package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.R;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.PopErrorAddStudentToCenter;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolClassromListActivity;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolDetailsViewModel;
import com.example.gescov.viewlayer.SchoolsActivities.schooluserslist.SchoolUsersListActivity;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.schoolrequests.SchoolRequestsListActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class SchoolDetailsActivity extends AppCompatActivity {
    private  SchoolsCrontroller schoolsCrontroller;
    private School school;
    private SchoolDetailsViewModel schoolDetailsViewModel;
    private User loggedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_details);
        schoolsCrontroller = PresentationControlFactory.getSchoolsCrontroller();
        school = schoolsCrontroller.getCurrentSchool();
        loggedUser = PresentationControlFactory.getViewLayerController().getLoggedUserInfo();

        TextView name = (TextView) findViewById(R.id.school_details_name);
        TextView address = (TextView) findViewById(R.id.school_details_address);
        TextView telf = (TextView) findViewById(R.id.school_details_telf);
        TextView webpage = (TextView) findViewById(R.id.school_details_webpage);
        TextView email = (TextView) findViewById(R.id.school__details_email);
        Button usersListButton = (Button) findViewById(R.id.school_details_student_list_button);
        Button classroomsListButton = (Button) findViewById(R.id.school_details_classroom_button);
        Button deleteButton = (Button) findViewById(R.id.school_details_delete);
        Button joinSchoolButton = (Button) findViewById(R.id.join_school_button);



        name.setText(school.getName());

        setVisibilityAndValue(address, school.getAddress());
        setVisibilityAndValue(telf, school.getTelephoneNumber());
        setVisibilityAndValue(webpage, school.getWebpage());
        setVisibilityAndValue(email, school.getEmail());


        deleteButton.setOnClickListener(e -> {
            confirmDeleteSchoolPrompt();
        });

        classroomsListButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, SchoolClassromListActivity.class);
            startActivity(intent);
        });

        usersListButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, SchoolUsersListActivity.class);
            startActivity(intent);
        });

        if (!loggedUser.getSchoolsID().contains(school.getId())) {
            joinSchoolButton.setText(getResources().getText(R.string.school_details_join));
            joinSchoolButton.setOnClickListener(e -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(e.getContext());
                builder.setTitle(e.getResources().getString(R.string.options))
                        .setItems(R.array.join_school_menu_items, (dialog, which) -> {
                            if (which == 0)
                                schoolDetailsViewModel.getAddStudentToCenterResult(name.getText().toString());
                            else if (which == 1) {
                                Intent intent = new Intent(this, AccessSchoolByCodeActivity.class);
                                startActivity(intent);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            });

            usersListButton.setVisibility(View.INVISIBLE);
        } else if (school.getAdministratorsList().contains(loggedUser.getId())) {
            joinSchoolButton.setText(getResources().getText(R.string.school_details_request_list));
            joinSchoolButton.setOnClickListener(e -> {
                Intent intent = new Intent(this, SchoolRequestsListActivity.class);
                startActivity(intent);
            });
            usersListButton.setVisibility(View.VISIBLE);
        } else {
            joinSchoolButton.setVisibility(View.INVISIBLE);
            usersListButton.setVisibility(View.INVISIBLE);
        }

        if (!school.getCreator().equals(loggedUser.getId())) {
            deleteButton.setVisibility(View.INVISIBLE);
        } else {
            deleteButton.setVisibility(View.VISIBLE);
        }


        schoolDetailsViewModel = new ViewModelProvider(this).get(SchoolDetailsViewModel.class);
        schoolDetailsViewModel.getPutResult().observe(this, schoolRequestResult -> {
            Boolean response = schoolRequestResult.getError();
            if (response) openPopup();
            else successAddingStudentToCenter();

        });
        setGraphListener();
    }

    private void setGraphListener() {
        Button graphButton = findViewById(R.id.graf_school);
        graphButton.setOnClickListener(v -> {
            Intent graphIntent = new Intent(this, SchoolGraphActivity.class);
            graphIntent.putExtra("schoolId",school.getId());
            graphIntent.putExtra("nameSchool",school.getName());
            startActivity(graphIntent);
        });
    }

    private void confirmDeleteSchoolPrompt() {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                schoolsCrontroller.deleteSchool(school);
                finish();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.school_details_confirm_delete_message)).setPositiveButton(getString(R.string.delete), dialogClickListener)
                .setNegativeButton(getString(R.string.cancel), dialogClickListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void successAddingStudentToCenter() {
        Toast.makeText(this,"Afegit amb èxit",Toast.LENGTH_LONG).show();
    }

    private void openPopup() {
        PopErrorAddStudentToCenter error = new PopErrorAddStudentToCenter();
        error.show(getSupportFragmentManager(),"Tag");
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