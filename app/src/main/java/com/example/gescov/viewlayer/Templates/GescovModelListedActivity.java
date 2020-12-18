package com.example.gescov.viewlayer.Templates;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gescov.R;

public abstract class GescovModelListedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_school_classrom_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView list = findViewById(R.id.classrooms_list);
        GescovModelListedController modelController = setModelController();
        modelController.setListViewAdapter(this);
        ModelListViewAdapter adapter = modelController.getListViewAdapter();
        list.setAdapter(adapter);

        modelController.refreshList();

    }

    protected abstract GescovModelListedController setModelController();
}
