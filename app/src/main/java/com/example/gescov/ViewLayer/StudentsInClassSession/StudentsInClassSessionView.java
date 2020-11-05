package com.example.gescov.ViewLayer.StudentsInClassSession;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.gescov.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class StudentsInClassSessionView extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton updateButton;
    private List<String> studentsList;
    private Context context;
    private StudentsInClassSessionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_in_class_session_view);
        initView();
        initUpdateButton();
    }

    private void initView() {
        studentsList = new ArrayList<>();
        context = this;
        listView = (ListView) findViewById(R.id.list_view_students_in_class_session);
        adapter = new StudentsInClassSessionAdapter(context,studentsList);
        studentsList.add("jose");
        listView.setAdapter(adapter);
    }

    private void initUpdateButton() {
        updateButton = (FloatingActionButton) findViewById(R.id.update_button_students_in_class_session);
        updateButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateList();
                    }
                }
        );
    }

    private void updateList() {
        studentsList.add("antonio");
        studentsList.add("Ponc");
        adapter.notifyDataSetChanged();
    }
}