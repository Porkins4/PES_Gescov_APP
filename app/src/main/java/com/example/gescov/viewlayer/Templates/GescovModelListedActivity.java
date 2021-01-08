package com.example.gescov.viewlayer.Templates;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gescov.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public abstract class GescovModelListedActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_school_classrom_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        setSupportActionBar(toolbar);

        ListView list = findViewById(R.id.classrooms_list);
        GescovModelListedController modelController = setModelController();
        modelController.setListViewAdapter(this);
        ModelListViewAdapter adapter = modelController.getListViewAdapter();
        list.setAdapter(adapter);

        modelController.refreshList();

    }

    protected abstract GescovModelListedController setModelController();
    protected void setFABVisible(boolean visible) {
        fab.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }
}
