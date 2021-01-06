package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.GescovUtils;
import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.PopErrorAddStudentToCenter;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolClassromListActivity;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.ContagionList.ContagionListActivity;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.SubjectActivity;
import com.example.gescov.viewlayer.SchoolsActivities.schooluserslist.SchoolUsersListActivity;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.schoolrequests.SchoolRequestsListActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class SchoolDetailsActivity extends AppCompatActivity {
    private SchoolsCrontroller schoolsCrontroller;
    private School school;
    private SchoolDetailsViewModel schoolDetailsViewModel;
    private User loggedUser;

    private Button graphButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_details);

        initToolbar();
        PresentationControlFactory.getLoadingProfileController().refreshLoggedUser();
        schoolsCrontroller = PresentationControlFactory.getSchoolsCrontroller();
        school = schoolsCrontroller.getCurrentSchool();
        setMap(savedInstanceState);

        loggedUser = PresentationControlFactory.getViewLayerController().getLoggedUserInfo();

        TextView name = (TextView) findViewById(R.id.school_details_name);
        TextView address = (TextView) findViewById(R.id.school_details_address);
        TextView telf = (TextView) findViewById(R.id.school_details_telf);
        TextView webpage = (TextView) findViewById(R.id.school_details_webpage);
        TextView code = (TextView) findViewById(R.id.school_details_code);
        //TextView email = (TextView) findViewById(R.id.school_details_email);
        Button usersListButton = (Button) findViewById(R.id.school_details_student_list_button);
        Button classroomsListButton = (Button) findViewById(R.id.school_details_classroom_button);
        Button joinSchoolButton = (Button) findViewById(R.id.join_school_button);
        Button contagionListButton = findViewById(R.id.contagion_list_button);
        Button subjectButton = findViewById(R.id.subject_button);
        setSubjectListener(subjectButton);
        graphButton = findViewById(R.id.graf_school);


        name.setText(school.getName());

        setVisibilityAndValue(address, school.getAddress());
        setVisibilityAndValue(telf, school.getTelephoneNumber());
        setVisibilityAndValue(webpage, school.getWebpage());
       // setVisibilityAndValue(email, school.getEmail());

        classroomsListButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, SchoolClassromListActivity.class);
            intent.putExtra("schooldID",school.getId());
            intent.putExtra("admin",GescovUtils.isUserSchoolAdmin(loggedUser, school));
            startActivity(intent);
        });

        usersListButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, SchoolUsersListActivity.class);
            startActivity(intent);
        });

        if (!GescovUtils.isUserInSchool(loggedUser, school)) {
            joinSchoolButton.setText(getResources().getText(R.string.school_details_join));
            joinSchoolButton.setOnClickListener(e -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(e.getContext());
                builder.setTitle(e.getResources().getString(R.string.options))
                        .setItems(R.array.join_school_menu_items, (dialog, which) -> {
                            if (which == 0)
                                schoolDetailsViewModel.getAddStudentToCenterResult(school.getId());
                            else if (which == 1) {
                                Intent intent = new Intent(this, AccessSchoolByCodeActivity.class);
                                intent.putExtra("schoolId", school.getId());
                                startActivity(intent);
                                finish();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            });

        } else {
            if (GescovUtils.isUserSchoolAdmin(loggedUser, school)) {
                //admins of the school
                joinSchoolButton.setText(getResources().getText(R.string.school_details_request_list));
                joinSchoolButton.setOnClickListener(e -> {
                    Intent intent = new Intent(this, SchoolRequestsListActivity.class);
                    startActivity(intent);
                });

                code.setText(school.getAccessCode());
            } else {
                code.setVisibility(View.INVISIBLE);
            }
        }
        
        classroomsListButton.setVisibility(GescovUtils.isUserInSchool(loggedUser, school) ? View.VISIBLE : View.GONE);
        joinSchoolButton.setVisibility(!GescovUtils.isUserInSchool(loggedUser, school) || GescovUtils.isUserSchoolAdmin(loggedUser, school) ? View.VISIBLE : View.GONE);
        usersListButton.setVisibility(GescovUtils.isUserInSchool(loggedUser, school) && loggedUser.getProfileType().equals(User.UserProfileType.TEACHER) ? View.VISIBLE : View.GONE);
        contagionListButton.setVisibility(GescovUtils.isUserInSchool(loggedUser, school) && loggedUser.getProfileType().equals(User.UserProfileType.TEACHER) ? View.VISIBLE : View.GONE);

        schoolDetailsViewModel = new ViewModelProvider(this).get(SchoolDetailsViewModel.class);
        schoolDetailsViewModel.getPutResult().observe(this, schoolRequestResult -> {
            Boolean response = schoolRequestResult.getError();
            if (response) openPopup();
            else successAddingStudentToCenter();

        });
        setGraphListener();
        setContagionListListener(contagionListButton);
    }

    private void setSubjectListener(Button subjectButton) {
        subjectButton.setOnClickListener(v -> {
            Intent intentSubject = new Intent(this,SubjectActivity.class);
            intentSubject.putExtra("schoolID",school.getId());
            startActivity(intentSubject);
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_school_details);
    }

    private void setMap(Bundle savedInstanceState) {
        MapView mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(googleMap -> {
            GoogleMap mMap = googleMap;
            try {
                boolean success = mMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                getApplicationContext(), R.raw.mapstyle));
                if (!success) {
                    Log.e("MapFragment", "Style parsing failed.");
                }
            } catch (Resources.NotFoundException e) {
                Log.e("MapFragment", "Can't find style. Error: ", e);
            }
            // habra que poner la latitude y longitude de la school
            LatLng locSchool = new LatLng(school.getLatitude(), school.getLongitude());
            mMap.addMarker(new MarkerOptions().position(locSchool));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locSchool, 13), 2700, null);
            mMap.setMaxZoomPreference(17);
        });
    }

    private void setContagionListListener(Button contagionListButton) {
            contagionListButton.setOnClickListener(v -> {
            Intent contagionListIntent = new Intent(this, ContagionListActivity.class);
            contagionListIntent.putExtra("schoolID",school.getId());
            contagionListIntent.putExtra("schoolName",school.getName());
            startActivity(contagionListIntent);
        });
    }

    private void setGraphListener() {
            graphButton.setOnClickListener(v -> {
            Intent graphIntent = new Intent(this, SchoolGraphActivity.class);
            graphIntent.putExtra("schoolId",school.getId());
            graphIntent.putExtra("nameSchool",school.getName());
            startActivity(graphIntent);
        });
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
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
            view.setText(value);
        }
    }
}