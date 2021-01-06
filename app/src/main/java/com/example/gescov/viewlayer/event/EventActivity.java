package com.example.gescov.viewlayer.event;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.User;

import java.text.ParseException;
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
    private int indexClassroom;
    private int indexTeacher;

    List<User> guests;
    private EventViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initComponents();
    }


    private void createEventListener() {
        addEvent.setOnClickListener(v -> {
            Pair<Boolean,String> result = validateFields();
            if (result.first) Toast.makeText(this,result.second,Toast.LENGTH_SHORT).show();
            else createEvent();
            //addEvent.setEnabled(false);
            //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            //String date = sdf.format(new Date(calendar.getDate()));
            //System.out.println(currentDate);
            //System.out.println();
            //System.out.println(description.getText().toString());
            //System.out.println(startHour.getText().toString() + ":" + startMin.getText().toString());
            //System.out.println(endHour.getText().toString() + ":" + endMin.getText().toString());
        });
    }

    private boolean validHours() {
        if ((startMin.getText().toString().length() < 2 || startHour.getText().toString().length() < 2 || endHour.getText().toString().length() < 2 || endMin.getText().toString().length() < 2)) return true;
        int startH = Integer.parseInt(startHour.getText().toString());
        int startM = Integer.parseInt(startMin.getText().toString());
        int endH = Integer.parseInt(endHour.getText().toString());
        int endM = Integer.parseInt(endMin.getText().toString());
        return (startH > 23 || startM > 59 || endH > 23 || endM > 59);
    }

    private Pair<Boolean,String> validateFields() {
        boolean previousDate = false;
        try {
            previousDate = (new SimpleDateFormat("yyyy/MM/dd").parse(currentDate).before(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (title.getText().toString().isEmpty()) return new Pair<>(true,getString(R.string.empty_title_error));
        else if (description.getText().toString().isEmpty()) return new Pair<>(true,getString(R.string.empty_description_error));
        else if (validHours()) return new Pair<>(true,getString(R.string.bad_time_format));
        else if (previousDate) return new Pair<>(true,getString(R.string.past_date_error));
        else return new Pair<>(false,"");
    }

    private void startGoogleCalendar() {
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
    }

    private void createEvent() {
        System.out.println(currentDate);
        viewModel.createEvent(title.getText().toString(),startHour.getText().toString() + ":" + startMin.getText().toString(),endHour.getText().toString() + ":" + endMin.getText().toString(),currentDate,viewModel.getTeacherID(indexTeacher),viewModel.getClassroomID(indexClassroom),getIntent().getStringExtra("subjectID")).observe(
                this, error -> {
                    if (error) Toast.makeText(this,R.string.general_error_message,Toast.LENGTH_SHORT).show();
                    else {

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
        indexClassroom = 0;
        indexTeacher = 0;
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
                        if (viewModel.emptyClassrooms()) popUpError(getString(R.string.error_no_classrooms_in_school));
                        else getTeachersOfTheSchool();

                    }
                });
    }

    private void popUpError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton(R.string.okey_text, (dialog, id) -> finish());
        AlertDialog dialog = builder.create();
        dialog.show();
        finish();
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

        teachers.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        indexTeacher = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        //nothing to do here
                    }
                }
        );

        classrooms.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        indexClassroom = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        //nothing to do here
                    }
                }
        );
    }

    private void initViewComponets() {
        initToolBar();
        initAttributes();
        initCalendarListener();
        createEventListener();
    }

    private void initCalendarListener() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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