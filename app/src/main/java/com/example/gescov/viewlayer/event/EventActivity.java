package com.example.gescov.viewlayer.event;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.User;

import java.util.List;

public class EventActivity extends AppCompatActivity {
    TextView title;
    TextView description;
    Button addEvent;
    List<User> guests;
    private EventViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initComponents();
        getEmails();
        eventSetListener();

    }

    private void getEmails() {
        viewModel =  new ViewModelProvider(this).get(EventViewModel.class);
        viewModel.getGuests(getIntent().getStringExtra("subjectID")).observe(this, received -> {
            if ( received ) {
                guests = viewModel.getListGuests();
            }
        });
    }

    private void eventSetListener() {
        addEvent.setOnClickListener(v -> {

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
        viewModel.createEvent();
    }

    private void initComponents() {
        title = findViewById(R.id.event_title);
        description = findViewById(R.id.event_description);
        addEvent = findViewById(R.id.add_event);
        initToolBar();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.event_form);
    }
}