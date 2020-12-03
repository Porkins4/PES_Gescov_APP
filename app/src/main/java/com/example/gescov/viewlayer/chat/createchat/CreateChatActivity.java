package com.example.gescov.viewlayer.chat.createchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gescov.R;

public class CreateChatActivity extends AppCompatActivity {

    private CreateChatViewModel createChatViewModel;
    private Spinner spinner;
    private LinearLayout selectSchoolBar;
    private LinearLayout loadingComponents;
    private ListView listView;
    private CreateChatActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat_acitvity);
        initViewComponents();
        initGetSchoolsListener();
    }

    private void initGetSchoolsListener() {
        TextView tv = (TextView) loadingComponents.findViewById(R.id.loading_text);
        tv.setText(getString(R.string.loading_user_schools_text));
        createChatViewModel = new ViewModelProvider(instance).get(CreateChatViewModel.class);
        createChatViewModel.getSchoolsRequest().observe(instance,
                received -> {
                    if (received) setSchoolSpinner();
                }
        );
    }

    private void setSchoolSpinner() {
        ArrayAdapter<String> schoolNamesAdapter = new ArrayAdapter<>(instance, R.layout.spinner_item_create_chat, createChatViewModel.getSchoolNames());
        spinner.setAdapter(schoolNamesAdapter);
        selectSchoolBar.setVisibility(View.VISIBLE);
        initSpinnerListener();
    }

    private void initSpinnerListener() {
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        listView.setAdapter(createChatViewModel.getCleanAdapter(instance));
                        loadingComponents.setVisibility(View.VISIBLE);
                        TextView tv = (TextView) loadingComponents.findViewById(R.id.loading_text);
                        tv.setText(getString(R.string.loading_afiliates_from_selected_center)+ " " + createChatViewModel.getIthSchoolName(position));
                        setContactsListener(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        //nothing to do here
                    }
                }
        );
    }

    private void setContactsListener(int position) {
        createChatViewModel.getContactsFromIthCenter(position).observe(instance,
                received -> {
                    if (received) setLVAdapter();
                });
    }

    private void setLVAdapter() {
        listView.setAdapter(createChatViewModel.getContactsAdapter(instance));
        loadingComponents.setVisibility(View.GONE);
    }

    private void setListViewListener() {
        listView.setOnItemClickListener(
                (parent, view, position, id) -> {//startChatWithTheUser
                    Toast.makeText(getApplicationContext(), "¿iniciar chat con " + createChatViewModel.getUserName(position) + "?", Toast.LENGTH_SHORT).show();
                }
        );
    }

    private void initViewComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_create_chat);
        spinner = (Spinner) findViewById(R.id.spinner);
        selectSchoolBar = (LinearLayout) findViewById(R.id.select_school_bar);
        loadingComponents = (LinearLayout) findViewById(R.id.loading_component);
        listView = (ListView) findViewById(R.id.list_view);
        setListViewListener();
        instance = this;
    }
}