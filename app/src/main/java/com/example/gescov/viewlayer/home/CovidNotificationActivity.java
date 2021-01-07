package com.example.gescov.viewlayer.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;


public class CovidNotificationActivity extends AppCompatActivity {
    private CovidNotificationViewModel covidNotificationViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid_notification);
        init();
        covidNotificationViewModel = new ViewModelProvider(this).get(CovidNotificationViewModel.class);
        covidNotificationViewModel.getPostResult().observe(this, contagionRequestResult -> {
            Pair<String,Boolean> response = contagionRequestResult.getError();
            if ( ! response.second) sucessNotification();
            else openPopup(response.first);
        });
    }

    private void sucessNotification() {
        Toast.makeText(this,R.string.success_Notification,Toast.LENGTH_SHORT).show();
    }

    private void initButtonNotifyPositive() {
        Button notifyPositive = findViewById(R.id.notifypositive);
        notifyPositive.setOnClickListener(v -> covidNotificationViewModel.getNotifyInfectedResult());
    }

    private void init() {
        initRecoverButton();
        initButtonNotifyPositive();
        initPossiblePositive();
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.covid_notification_title);
    }

    private void initPossiblePositive() {
        Button notifypossible = findViewById(R.id.notifypossible);
        notifypossible.setOnClickListener(v -> covidNotificationViewModel.notifyPossibleContagion());
    }

    private void initRecoverButton() {
        Button notifyRecover = findViewById(R.id.notifyrecover);
        notifyRecover.setOnClickListener(v -> covidNotificationViewModel.notifyRecoveryResult());
    }

    private void openPopup(String notification) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error!")
                .setMessage(notification)
                .setPositiveButton("Ok", (dialog, which) -> {
                });
        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent resultIntent = new Intent();
        setResult(0,resultIntent);
        finish();
    }
}