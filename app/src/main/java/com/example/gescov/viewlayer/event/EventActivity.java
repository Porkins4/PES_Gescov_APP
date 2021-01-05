package com.example.gescov.viewlayer.event;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class EventActivity extends AppCompatActivity {
    private TextView title;
    private TextView description;
    private TextView startHour, startMin;
    private TextView endHour, endMin;
    private Button addEvent;
    private CalendarView calendar;
    private String currentDate;

    List<User> guests;
    private EventViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initComponents();
        eventSetListener();
    }


    private void eventSetListener() {
        addEvent.setOnClickListener(v -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(new Date(calendar.getDate()));
            System.out.println(currentDate);
            System.out.println(title.getText().toString());
            System.out.println(description.getText().toString());
            System.out.println(startHour.getText().toString() + ":" + startMin.getText().toString());
            System.out.println(endHour.getText().toString() + ":" + endMin.getText().toString());

            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setData(CalendarContract.Events.CONTENT_URI);
            intent.putExtra(CalendarContract.Events.TITLE,title.getText().toString());
            intent.putExtra(CalendarContract.Events.DESCRIPTION,description.getText().toString());
            //intent.putExtra(CalendarContract.Events.CALENDAR_ID,1);
            String emails = "";
            for (int i = 0; i < guests.size(); ++i ) {
                emails += guests.get(i).getEmail();
                if ( i != guests.size() -1 ) emails += ',';
            }
            System.out.println(emails);
            intent.putExtra(Intent.EXTRA_EMAIL,emails);
            // checking if there is any app that can handel this type of intent which is calendar.
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                createEvent();
            }
        });
    }

    private void createEvent() {
        viewModel.createEvent(title.getText().toString(),startHour.getText().toString() + ":" + startMin.getText().toString(),endHour.getText().toString() + ":" + endMin.getText().toString(),currentDate,"","",getIntent().getStringExtra("subjectID")).observe(
                this, error -> {
                    if (error) Toast.makeText(this,R.string.general_error_message,Toast.LENGTH_SHORT).show();
                    else {
                        eventSetListener();
                    }
                }
        );
    }

    private void initComponents() {
        initActivityInfo();
        initViewComponets();
    }

    private void initActivityInfo() {
        viewModel =  new ViewModelProvider(this).get(EventViewModel.class);
        viewModel.init(getIntent().getStringExtra("schoolID"), getIntent().getStringExtra("subjectID"));
        getEmails();
    }

    private void getEmails() {
        viewModel.getGuests().observe(this, received -> {
            if ( received ) {
                guests = viewModel.getListGuests();
                getClassroomsOfTheSchool();
            }
        });
    }

    private void getClassroomsOfTheSchool() {
        viewModel.getClassrooms().observe(this,
                error -> {
                    if (!error) {
                        getTeachersOfTheSchool();
                    }
                });
    }

    private void getTeachersOfTheSchool() {
        viewModel.getTeachersOfTheSchool().observe(this,
                received -> {
                    initSpinners();
                });
    }

    private void initSpinners() {
        Spinner teachers = findViewById(R.id.teacher_spinner);
        Spinner classrooms = findViewById(R.id.classroom_spinner);
        ArrayAdapter<String> teacherNames = new ArrayAdapter<>(this, R.layout.spinner_item_create_event, viewModel.getTeacherNames());
        ArrayAdapter<String> classroomNames = new ArrayAdapter<>(this, R.layout.spinner_item_create_event, viewModel.getClassroomNames());
        teachers.setAdapter(teacherNames);
        classrooms.setAdapter(classroomNames);
    }

    private void initViewComponets() {
        initToolBar();
        initAttributes();
        initCalendarListener();
    }

    private void initCalendarListener() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        currentDate = sdf.format(calendar.getDate());
        calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Date temp = new GregorianCalendar(year,month,dayOfMonth).getTime();
            currentDate = sdf.format(temp);
        });
    }

    private void initAttributes() {
        title = findViewById(R.id.event_title);
        description = findViewById(R.id.event_description);
        addEvent = findViewById(R.id.add_event);
        calendar = findViewById(R.id.calendar);
        startHour = findViewById(R.id.event_start_hour);
        startMin = findViewById(R.id.event_estart_minutes);
        endHour = findViewById(R.id.event_end_hour);
        endMin = findViewById(R.id.event_end_minutes);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.event_form);
    }
}