package com.example.gescov.viewlayer.event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Button;
import android.widget.TextView;

import com.example.gescov.R;

public class EventActivity extends AppCompatActivity {
    TextView title;
    TextView description;
    Button addEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initComponents();
        eventSetListener();

    }

    private void eventSetListener() {
        addEvent.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setData(CalendarContract.Events.CONTENT_URI);
            intent.putExtra(CalendarContract.Events.TITLE,title.getText().toString());
            intent.putExtra(CalendarContract.Events.DESCRIPTION,description.getText().toString());
            intent.putExtra(CalendarContract.Events.ALL_DAY,"true");
            //intent.putExtra(CalendarContract.Events.CALENDAR_ID,1);
            intent.putExtra(Intent.EXTRA_EMAIL,"anas.infad@estudiantat.upc.edu,pablo.cebollada.hernandez@estudiantat.upc.edu,isaac.marcelo.munoz@estudiantat.upc.edu" +
                    ",ponc.parramon@estudiantat.upc.edu,victormasterventus@gmail.com,albert.borrellas@estudiantat.upc.edu");

            // checking if there is any app that can handel this type of intent which is calendar.
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
    }

    private void initComponents() {
        title = findViewById(R.id.event_title);
        description = findViewById(R.id.event_description);
        addEvent = findViewById(R.id.add_event);

    }
}