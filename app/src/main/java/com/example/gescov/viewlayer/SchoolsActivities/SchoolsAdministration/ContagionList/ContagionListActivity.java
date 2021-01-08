package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.ContagionList;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContagionListActivity extends AppCompatActivity {

    private ListView contagionList;
    private List<String> names;
    private List<String> contagionDate;
    private ContagionListAdapter adapter;
    private String schoolName;
    private String schoolID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contagion_list);
        names = new ArrayList<>();
        contagionDate = new ArrayList<>();
        PresentationControlFactory.getContagionController().setContagionListFragment(this);
        contagionList = findViewById(R.id.contagion_list);
        adapter = new ContagionListAdapter(contagionList.getContext(), names, contagionDate);
        contagionList.setAdapter(adapter);
        schoolName = getIntent().getStringExtra("schoolName");
        schoolID = getIntent().getStringExtra("schoolID");
        getContagionsOfCenter();
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_contagion_list);
    }

    private void getContagionsOfCenter() {
        try {
            PresentationControlFactory.getContagionController().refresh(schoolID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateData(String info) throws JSONException {
        JSONArray response = new JSONArray(info);
        for (int i = 0; i < response.length(); ++i) {
            JSONObject aux = response.getJSONObject(i);
            String nameInfected = aux.getString("first");
            String date = aux.getString("second");
            names.add(nameInfected);
            contagionDate.add(schoolName+"-Positiu des de " + date);
        }
        adapter.notifyDataSetChanged();
    }
}